package sql;

import controller.ControllerManager;
import model.support.FilesManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataInserter {

    private static final List<String> INSERTION_ORDER = new ArrayList<>() {{
        add("dispatcher_work_direction");
        add("operator_work_direction");
        add("cashier_work_direction");
        add("security_work_direction");
        add("technician_work_direction");
        add("eng_level");
        add("specialization");
        add("crew");
        add("department");
        add("employee");
        add("pilot");
        add("pilot_medical_checkup_history");
        add("director");
        add("technician");
        add("dispatcher");
        add("cashier");
        add("security_officer");
        add("operator");
        add("location");
        add("airplane_type");
        add("airplane");
        add("maintenance_type");
        add("airplane_service_history");
        add("airplane_maintenance_history");
        add("airline");
        add("flight_category");
        add("flight");
        add("flight_day");
        add("departure_status");
        add("departure");
        add("departure_status_history");
        add("ticket_status");
        add("ticket");
        add("passenger");
        add("ticket_status_history");
    }};
    private final ControllerManager controllerManager;

    public DataInserter(ControllerManager controllerManager) {
        this.controllerManager = controllerManager;
    }

    public void insertTestData(String source) throws IOException, SQLException {
        for (var order : INSERTION_ORDER) {
            String filename = order + ".sql";
            String queriesPath = source + "/";
            String sqlQuery = Files.readString(Paths.get(queriesPath + filename))
                    .replaceAll("\n", " ")
                    .trim().replaceAll(" +", " ");
            if (sqlQuery.charAt(sqlQuery.length() - 1) == ';') {
                sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 1);
            }
            if (sqlQuery.contains("Auto generate")) {
                continue;
            }
            try {
                controllerManager.getProvider().getCreatedStatement().execute(sqlQuery);
            } catch (SQLException e) {
                System.out.println(filename + ": " + e.getMessage());
            }
        }
        controllerManager.getProvider().commitChanges();
    }

    public void insertLastGenerationDate() throws SQLException {
        ResultSet resultSet = this.controllerManager.getProvider().getStringsQueryResultSet(
                "SELECT COUNT(*) FROM LAST_GENERATION_DATE", Collections.emptyList());
        while (resultSet.next()) {
            if (resultSet.getInt(1) == 0) {
                this.controllerManager.getProvider().getCreatedStatement().execute("INSERT INTO LAST_GENERATION_DATE (LAST_DATE) VALUES (NULL)");
            }
        }
    }
}
