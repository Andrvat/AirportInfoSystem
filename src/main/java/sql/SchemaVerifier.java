package sql;

import controller.ControllerManager;
import dbConnection.OracleDbProvider;
import oracle.net.jdbc.nl.InvalidSyntaxException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SchemaVerifier {

    private enum ObjectTypes {
        ALTER,
        CREATE,
        INSERT,
        SEQUENCE,
        TRIGGER
    }

    private final List<String> createdTableNames = new ArrayList<>();
    private final List<String> createdSequenceNames = new ArrayList<>();
    private final List<String> createdTriggerNames = new ArrayList<>();
    private final Map<String, String> createdConstrainsNames = new HashMap<>();

    private final ControllerManager controllerManager;

    private final String accountLogin;
    private final String accountPassword;

    public SchemaVerifier(ControllerManager controllerManager, String accountLogin, String accountPassword) {
        this.controllerManager = controllerManager;
        this.accountLogin = accountLogin;
        this.accountPassword = accountPassword;
    }

    public void verifyAccount() throws SQLException, InvalidSyntaxException {
        OracleDbProvider provider = this.controllerManager.getProvider();
        ResultSet resultSet = provider.getStringsQueryResultSet(
                "SELECT COUNT(*) " +
                        "FROM APPLICATION_ACCOUNT " +
                        "WHERE LOGIN = '" + this.accountLogin + "' " +
                        "AND PASSWORD = '" + this.accountPassword + "' " +
                        "AND ROLE = '" + provider.getRole() + "'", Collections.emptyList());
        while (resultSet.next()) {
            int counter = resultSet.getInt(1);
            if (counter == 0) {
                throw new InvalidSyntaxException(this.getClass().getName() + ": invalid role login or password");
            }
        }
    }

    public void verifySchema() throws SQLException, IOException {
        OracleDbProvider provider = this.controllerManager.getProvider();
        this.verifyTables(provider);
        provider.commitChanges();
        this.verifyConstraints(provider);
        provider.commitChanges();
        this.verifySequences(provider);
        provider.commitChanges();
        this.verifyTriggers(provider);
        provider.commitChanges();
    }

    private void verifyTables(OracleDbProvider provider) throws SQLException, IOException {
        this.verifyDatabaseObjects(provider,
                "SELECT TABLE_NAME FROM USER_TABLES",
                this.createdTableNames,
                "src/main/resources/sql/tables/creates",
                ObjectTypes.CREATE);
    }

    private void verifySequences(OracleDbProvider provider) throws SQLException, IOException {
        this.verifyDatabaseObjects(provider,
                "SELECT SEQUENCE_NAME FROM USER_SEQUENCES",
                this.createdSequenceNames,
                "src/main/resources/sql/tables/sequences",
                ObjectTypes.SEQUENCE);
    }

    private void verifyTriggers(OracleDbProvider provider) throws SQLException, IOException {
        this.verifyDatabaseObjects(provider,
                "SELECT TRIGGER_NAME FROM USER_TRIGGERS",
                this.createdTriggerNames,
                "src/main/resources/sql/tables/triggers",
                ObjectTypes.TRIGGER);
    }

    private void verifyConstraints(OracleDbProvider provider) throws SQLException, IOException {
        for (String name : this.createdTableNames) {
            ResultSet resultSet = provider.getStringsQueryResultSet(
                    "SELECT CONSTRAINT_NAME " +
                            "FROM USER_CONS_COLUMNS " +
                            "WHERE TABLE_NAME = " + "'" + name + "'",
                    Collections.emptyList());
            while (resultSet.next()) {
                String constraint = resultSet.getString(1);
                this.createdConstrainsNames.put(constraint, name);
            }
        }

        Map<String, String> requiredConstraintsNames = new HashMap<>();
        String sourcePath = "src/main/resources/sql/tables/alters";
        List<String> schemaQueriesFilenames = this.getSchemaQueriesFilenames(sourcePath);
        for (var queryFilename : schemaQueriesFilenames) {
            String queriesPath = sourcePath + "/";
            String sqlQuery = Files.readString(Paths.get(queriesPath + queryFilename))
                    .replaceAll("\n", " ")
                    .trim().replaceAll(" +", " ");
            var allQueryWords = new LinkedList<>(Arrays.asList(sqlQuery.split(" ")));
            int matchPosition = allQueryWords.indexOf("CONSTRAINT");
            while (matchPosition != -1) {
                requiredConstraintsNames.put(allQueryWords.get(matchPosition + 1).toUpperCase(Locale.ROOT), queryFilename);
                allQueryWords.remove(matchPosition);
                matchPosition = allQueryWords.indexOf("CONSTRAINT");
            }
        }

        for (var constraintName : requiredConstraintsNames.entrySet()) {
            String queriesPath = sourcePath + "/";
            String name = constraintName.getKey();
            String table = constraintName.getValue();
            if (!this.createdConstrainsNames.containsKey(name)) {
                String sqlQuery = Files.readString(Paths.get(queriesPath + table))
                        .replaceAll("\n", " ");
                var queries = sqlQuery.split(";");
                for (var query : queries) {
                    if (query.contains(name)) {
                        if (query.charAt(query.length() - 1) == ';') {
                            query = query.substring(0, query.length() - 1);
                        }
                        provider.getCreatedStatement().execute(query.trim().replaceAll(" +", " "));
                        this.createdConstrainsNames.put(name, table.substring(table.length() - 4).toUpperCase(Locale.ROOT));
                    }
                }
            }
        }
    }

    private void verifyDatabaseObjects(OracleDbProvider provider,
                                       String knowQuery,
                                       List<String> createdObjects,
                                       String sourcePath,
                                       ObjectTypes type) throws SQLException, IOException {
        ResultSet resultSet = provider.getStringsQueryResultSet(knowQuery, Collections.emptyList());
        while (resultSet.next()) {
            createdObjects.add(resultSet.getString(1));
        }
        List<String> schemaQueriesFilenames = this.getSchemaQueriesFilenames(sourcePath);
        for (var queryFilename : schemaQueriesFilenames) {
            String objectName = queryFilename.substring(0, queryFilename.length() - 4).toUpperCase(Locale.ROOT);
            String queriesPath = sourcePath + "/";
            if (!createdObjects.contains(objectName)) {
                String sqlQuery = Files.readString(Paths.get(queriesPath + queryFilename))
                        .replaceAll("\n", " ").trim().replaceAll(" +", " ");
                if (sqlQuery.charAt(sqlQuery.length() - 1) == ';' && !type.equals(ObjectTypes.TRIGGER)) {
                    sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 1);
                }
                provider.getCreatedStatement().execute(sqlQuery);
                createdObjects.add(objectName);
            }
        }
    }

    private List<String> getSchemaQueriesFilenames(String path) {
        List<String> verifiedFileNames = new ArrayList<>();
        File folder = new File(path);
        File[] filesList = folder.listFiles();
        for (int i = 0; i < Objects.requireNonNull(filesList).length; ++i) {
            if (filesList[i].isFile()) {
                verifiedFileNames.add(filesList[i].getName());
            }
        }
        return verifiedFileNames;
    }
}
