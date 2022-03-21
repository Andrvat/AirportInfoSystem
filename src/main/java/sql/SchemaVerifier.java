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
    private final List<String> alters = new ArrayList<>();
    private final List<String> creates = new ArrayList<>();
    private final List<String> sequences = new ArrayList<>();
    private final List<String> triggers = new ArrayList<>();

    private final ControllerManager controllerManager;

    public SchemaVerifier(ControllerManager controllerManager) {
        this.controllerManager = controllerManager;
    }

    public void verify() throws SQLException, IOException {
        OracleDbProvider provider = this.controllerManager.getProvider();

        String tableNamesQuery = "SELECT TABLE_NAME FROM USER_TABLES";
        ResultSet resultSet = provider.getStringsQueryResultSet(tableNamesQuery, Collections.emptyList());
        while (resultSet.next()) {
            this.creates.add(resultSet.getString(1));
        }
        List<String> schemaTableQueriesFilenames = this.getSchemaTableQueriesFilenames();
        for (var tableQueryFilename : schemaTableQueriesFilenames) {
            String tableName = tableQueryFilename.substring(0, tableQueryFilename.length() - 4).toUpperCase(Locale.ROOT);
            if (!creates.contains(tableName)) {
                String creatingScriptsPath = "src/main/resources/sql/tables/creating/";
                String sqlQuery = Files.readString(Paths.get(creatingScriptsPath + tableQueryFilename))
                        .replaceAll("\n", " ");
                provider.getCreatedStatement().execute(sqlQuery);
            }
        }


        String triggerNamesQuery = "SELECT TRIGGER_NAME FROM ALL_TRIGGERS WHERE TABLE_NAME = ?";

    }

    private List<String> getSchemaTableQueriesFilenames() {
        List<String> verifiedFileNames = new ArrayList<>();
        File folder = new File("src/main/resources/sql/tables/creating");
        File[] filesList = folder.listFiles();
        for (int i = 0; i < Objects.requireNonNull(filesList).length; ++i) {
            if (filesList[i].isFile()) {
                verifiedFileNames.add(filesList[i].getName());
            }
        }
        return verifiedFileNames;
    }
}
