package forms;

import controller.ControllerManager;
import view.listenedButtons.MakeRequestButton;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

public class Request9AProvider extends AbstractRequestProvider {
    public Request9AProvider() {
        super("<html>Получить перечень и общее число pейсов, по котоpым летают самолеты заданного типа</html>",

                "WITH ARRIVAL_LOCATIONS AS ( " +
                        "SELECT FLIGHT_ID, LOCATION_NAME AS ARRIVAL_LOCATION_NAME " +
                        "FROM FLIGHT " +
                        "LEFT OUTER JOIN LOCATION ON ARRIVAL_LOCATION_ID = LOCATION_ID), " +
                        "DEPARTURE_LOCATIONS AS ( " +
                        "SELECT FLIGHT_ID, LOCATION_NAME AS DEPARTURE_LOCATION_NAME " +
                        "FROM FLIGHT " +
                        "LEFT OUTER JOIN LOCATION ON DEPARTURE_LOCATION_ID = LOCATION_ID) " +
                        "SELECT FLIGHT_ID, DEPARTURE_LOCATION_NAME, ARRIVAL_LOCATION_NAME, AIRLINE_NAME " +
                        "FROM FLIGHT " +
                        "LEFT JOIN AIRPLANE_TYPE USING (AIRPLANE_TYPE_ID)" +
                        "LEFT OUTER JOIN AIRLINE USING (AIRLINE_ID) " +
                        "LEFT OUTER JOIN ARRIVAL_LOCATIONS USING (FLIGHT_ID) " +
                        "LEFT OUTER JOIN DEPARTURE_LOCATIONS USING (FLIGHT_ID) " +
                        "WHERE ",

                new LinkedHashMap<>() {{
                    put("Тип самолета", new String[]{"="});
                }}
        );
    }

    private String getCompletedRequestQuery() {
        var answers = this.getAnswers();
        var selectedOptions = this.getSelectedOptions();
        var noOption = MakeRequestButton.NO_OPTION;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getRequestBlank());
        if (!noOption.equals(selectedOptions.get("Тип самолета"))) {
            stringBuilder.append("TYPE_NAME")
                    .append(" ")
                    .append(selectedOptions.get("Тип самолета"))
                    .append(" ")
                    .append("'")
                    .append(answers.get("Тип самолета"))
                    .append("'")
                    .append(" ");
        }
        AbstractRequestProvider.removeWhereIfNeed(stringBuilder);
        return stringBuilder.toString();
    }

    @Override
    public RequestResultPackage getRequestResultRowsNumber(ControllerManager controllerManager) throws SQLException {
        var requestQuery = this.getCompletedRequestQuery();
        return AbstractRequestProvider.makePackageWithRowsNumber(controllerManager, requestQuery);
    }

    @Override
    public RequestResultPackage getRequestResultRows(ControllerManager controllerManager) throws SQLException {
        var requestQuery = this.getCompletedRequestQuery();
        var resultPackage = new RequestResultPackage();
        ResultSet resultSet = controllerManager.getProvider().getStringsQueryResultSet(requestQuery, Collections.emptyList());
        List<String[]> allRows = new ArrayList<>();
        // SELECT FLIGHT_ID, DEPARTURE_LOCATION_NAME, ARRIVAL_LOCATION_NAME, AIRLINE_NAME
        resultPackage.setColumnNames(new String[]{"ID рейса", "Место вылета", "Место прилета", "Авиакомпания"});
        while (resultSet.next()) {
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(resultSet.getInt(1)));
            row.add(resultSet.getString(2));
            row.add(resultSet.getString(3));
            row.add(resultSet.getString(4));
            allRows.add(row.toArray(new String[0]));
        }
        resultPackage.setResultRows(allRows.toArray(new String[0][]));
        return resultPackage;
    }
}
