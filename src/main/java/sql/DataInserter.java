package sql;

import controller.ControllerManager;
import model.support.FilesManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

public class DataInserter {
    private final ControllerManager controllerManager;

    public DataInserter(ControllerManager controllerManager) {
        this.controllerManager = controllerManager;
    }

    public void insertTestData(String source) throws IOException, SQLException {
        // TODO: make a correct order for running inserts with FK
        List<String> queriesFilenames = FilesManager.getQueriesFilenames(source);
        for (var filename : queriesFilenames) {
            String queriesPath = source + "/";
            String sqlQuery = Files.readString(Paths.get(queriesPath + filename))
                    .replaceAll("\n", " ")
                    .trim().replaceAll(" +", " ");
            if (sqlQuery.charAt(sqlQuery.length() - 1) == ';') {
                sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 1);
            }
            try {
                controllerManager.getProvider().getCreatedStatement().execute(sqlQuery);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        controllerManager.getProvider().commitChanges();
    }
}
