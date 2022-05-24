package forms;

import controller.ControllerManager;
import view.listenedButtons.MakeRequestButton;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

public class Request4Provider extends AbstractRequestProvider {
    public Request4Provider() {
        super("<html>Получить перечень и общее число самолетов приписанных к аэpопоpту, " +
                        "по количеству совеpшенных pейсов.</html>",

                "WITH DEPARTURES_NUMBERS AS ( " +
                        "SELECT AIRPLANE_ID, COUNT(DEPARTURE_ID) AS DEPARTURES_NUMBER " +
                        "FROM DEPARTURE " +
                        "LEFT JOIN DEPARTURE_STATUS USING (DEPARTURE_STATUS_ID) " +
                        "WHERE (DEPARTURE_DATE < CURRENT_DATE) " +
                        "AND (DEPARTURE_STATUS.DESCRIPTION = 'Вылетел' OR DEPARTURE_STATUS.DESCRIPTION = 'Приземлился') " +
                        "GROUP BY AIRPLANE_ID " +
                        ") " +
                        "SELECT AIRPLANE_ID, TYPE_NAME, MANUFACTURE_YEAR, LOCATION_NAME, DEPARTURES_NUMBER " +
                        "FROM AIRPLANE " +
                        "LEFT JOIN AIRPLANE_TYPE USING (AIRPLANE_TYPE_ID) " +
                        "LEFT JOIN LOCATION ON AIRPORT_LOCATION_ID = LOCATION_ID " +
                        "LEFT JOIN DEPARTURES_NUMBERS USING (AIRPLANE_ID) " +
                        "WHERE ",

                new LinkedHashMap<>() {{
                    put("Аэропорт приписки", new String[]{"="});
                    put("Кол-во совершен. рейсов", new String[]{"<", ">", "="});
                }}
        );
    }

    private String getCompletedRequestQuery() {
        var answers = this.getAnswers();
        var selectedOptions = this.getSelectedOptions();
        var noOption = MakeRequestButton.NO_OPTION;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getRequestBlank());
        boolean previousExists = false;
        if (!noOption.equals(selectedOptions.get("Аэропорт приписки"))) {
            stringBuilder.append("LOCATION_NAME")
                    .append(" ")
                    .append(selectedOptions.get("Аэропорт приписки"))
                    .append(" ")
                    .append("'")
                    .append(answers.get("Аэропорт приписки"))
                    .append("'")
                    .append(" ");
            previousExists = true;
        }
        if (!noOption.equals(selectedOptions.get("Кол-во совершен. рейсов"))) {
            stringBuilder.append((previousExists) ? "AND " : "");
            stringBuilder.append("DEPARTURES_NUMBER")
                    .append(" ")
                    .append(selectedOptions.get("Кол-во совершен. рейсов"))
                    .append(" ")
                    .append(answers.get("Кол-во совершен. рейсов"))
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
        // SELECT AIRPLANE_ID, TYPE_NAME, MANUFACTURE_YEAR, LOCATION_NAME, DEPARTURES_NUMBER
        resultPackage.setColumnNames(new String[]{"ID самолета", "Тип самолета", "Год выпуска", "Аэропорт приписки", "Кол-во совершен. вылетов"});
        while (resultSet.next()) {
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(resultSet.getInt(1)));
            row.add(resultSet.getString(2));
            row.add(String.valueOf(resultSet.getInt(3)));
            row.add(resultSet.getString(4));
            row.add(String.valueOf(resultSet.getInt(5)));
            allRows.add(row.toArray(new String[0]));
        }
        resultPackage.setResultRows(allRows.toArray(new String[0][]));
        return resultPackage;
    }
}
