package sql;

import controller.ControllerManager;
import dbConnection.OracleDbProvider;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SchemaVerifier {
    private final List<String> createdTableNames = new ArrayList<>();
    private final List<String> createdSequenceNames = new ArrayList<>();
    private final List<String> createdTriggerNames = new ArrayList<>();

    private final ControllerManager controllerManager;

    public SchemaVerifier(ControllerManager controllerManager) {
        this.controllerManager = controllerManager;
    }

    public void verify() throws SQLException, IOException {
        OracleDbProvider provider = this.controllerManager.getProvider();

        String tableNamesQuery = "SELECT TABLE_NAME FROM USER_TABLES";
        ResultSet resultSet = provider.getStringsQueryResultSet(tableNamesQuery, Collections.emptyList());
        while (resultSet.next()) {
            this.createdTableNames.add(resultSet.getString(1));
        }
        List<String> schemaTableQueriesFilenames = this.getSchemaQueriesFilenames(
                "src/main/resources/sql/tables/creating");
        for (var tableQueryFilename : schemaTableQueriesFilenames) {
            String tableName = tableQueryFilename.substring(0, tableQueryFilename.length() - 4).toUpperCase(Locale.ROOT);
            String queriesPath = "src/main/resources/sql/tables/creating/";
            if (!this.createdTableNames.contains(tableName)) {
                String sqlQuery = Files.readString(Paths.get(queriesPath + tableQueryFilename))
                        .replaceAll("\n", " ");
                provider.getCreatedStatement().execute(sqlQuery);
                this.createdTableNames.add(tableName);
            }
        }

        String sequenceNamesQuery = "SELECT SEQUENCE_NAME FROM ALL_SEQUENCES";
        resultSet = provider.getStringsQueryResultSet(sequenceNamesQuery, Collections.emptyList());
        while (resultSet.next()) {
            this.createdSequenceNames.add(resultSet.getString(1));
        }
        List<String> schemaSequenceQueriesFilenames = this.getSchemaQueriesFilenames(
                "src/main/resources/sql/tables/sequences");
        for (var sequenceQueryFilename : schemaSequenceQueriesFilenames) {
            String sequenceName = sequenceQueryFilename.substring(0, sequenceQueryFilename.length() - 4).toUpperCase(Locale.ROOT);
            String queriesPath = "src/main/resources/sql/tables/sequences/";
            if (!this.createdSequenceNames.contains(sequenceName)) {
                String sqlQuery = Files.readString(Paths.get(queriesPath + sequenceQueryFilename))
                        .replaceAll("\n", " ");
                provider.getCreatedStatement().execute(sqlQuery);
                this.createdSequenceNames.add(sequenceName);
            }
        }

        // TODO: make a query for special table by its name
        String triggersNamesQuery = "SELECT TRIGGER_NAME FROM ALL_TRIGGERS";
        resultSet = provider.getStringsQueryResultSet(triggersNamesQuery, Collections.emptyList());
        while (resultSet.next()) {
            this.createdTriggerNames.add(resultSet.getString(1));
        }
        List<String> schemaTriggerQueriesFilenames = this.getSchemaQueriesFilenames(
                "src/main/resources/sql/tables/triggers");
        for (var triggerQueryFilename : schemaTriggerQueriesFilenames) {
            String triggerName = triggerQueryFilename.substring(0, triggerQueryFilename.length() - 4).toUpperCase(Locale.ROOT);
            String queriesPath = "src/main/resources/sql/tables/triggers/";
            if (!this.createdTriggerNames.contains(triggerName)) {
                String sqlQuery = Files.readString(Paths.get(queriesPath + triggerQueryFilename))
                        .replaceAll("\n", " ");
                provider.getCreatedStatement().execute(sqlQuery);
                this.createdTriggerNames.add(triggerName);
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
