package forms;

import controller.ControllerManager;
import model.support.TimeCalendar;
import view.listenedButtons.MakeRequestButton;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

public class Request8AProvider extends AbstractRequestProvider {
    public Request8AProvider() {
        super("<html>Получить перечень и общее число задеpжанных pейсов полностью," +
                        " по указанной пpичине, по указанному маpшpуту.</html>",

                "WITH ARRIVAL_LOCATIONS AS ( " +
                        "SELECT FLIGHT_ID, LOCATION_NAME AS ARRIVAL_LOCATION_NAME " +
                        "FROM FLIGHT " +
                        "LEFT OUTER JOIN LOCATION ON ARRIVAL_LOCATION_ID = LOCATION_ID), " +
                        "DEPARTURE_LOCATIONS AS ( " +
                        "SELECT FLIGHT_ID, LOCATION_NAME AS DEPARTURE_LOCATION_NAME " +
                        "FROM FLIGHT " +
                        "LEFT OUTER JOIN LOCATION ON DEPARTURE_LOCATION_ID = LOCATION_ID) " +
                        "SELECT DEPARTURE_ID, FLIGHT_ID, DEPARTURE_DATE, ARRIVAL_DATE, ARRIVAL_LOCATION_NAME, DEPARTURE_LOCATION_NAME, AIRLINE_NAME " +
                        "FROM DEPARTURE " +
                        "LEFT JOIN FLIGHT USING(FLIGHT_ID) " +
                        "LEFT JOIN DEPARTURE_STATUS USING(DEPARTURE_STATUS_ID) " +
                        "LEFT JOIN ARRIVAL_LOCATIONS USING (FLIGHT_ID) " +
                        "LEFT JOIN DEPARTURE_LOCATIONS USING (FLIGHT_ID) " +
                        "LEFT JOIN AIRLINE USING (AIRLINE_ID) " +
                        "WHERE (DEPARTURE_STATUS.DESCRIPTION = 'Задержан') ",

                new LinkedHashMap<>() {{
                    put("Причина", new String[]{"="});
                    put("Место вылета", new String[]{"="});
                    put("Место прилета", new String[]{"="});
                }}
        );
    }

    private String getCompletedRequestQuery() {
        var answers = this.getAnswers();
        var selectedOptions = this.getSelectedOptions();
        var noOption = MakeRequestButton.NO_OPTION;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getRequestBlank());

        if (!noOption.equals(selectedOptions.get("Место вылета"))) {
            stringBuilder.append("AND ");
            stringBuilder.append("DEPARTURE_LOCATION_NAME")
                    .append(" ")
                    .append(selectedOptions.get("Место вылета"))
                    .append(" ")
                    .append("'")
                    .append(answers.get("Место вылета"))
                    .append("'")
                    .append(" ");
        }
        if (!noOption.equals(selectedOptions.get("Место прилета"))) {
            stringBuilder.append("AND ");
            stringBuilder.append("ARRIVAL_LOCATION_NAME")
                    .append(" ")
                    .append(selectedOptions.get("Место прилета"))
                    .append(" ")
                    .append("'")
                    .append(answers.get("Место прилета"))
                    .append("'")
                    .append(" ");
        }
        if (!noOption.equals(selectedOptions.get("Причина"))) {
            stringBuilder.append("AND ");
            stringBuilder.append("DEPARTURE_STATUS.DESCRIPTION")
                    .append(" ")
                    .append(selectedOptions.get("Причина"))
                    .append(" ")
                    .append("'")
                    .append(answers.get("Причина"))
                    .append("'")
                    .append(" ");
        }
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
        // SELECT DEPARTURE_ID, FLIGHT_ID, DEPARTURE_DATE, ARRIVAL_DATE, ARRIVAL_LOCATION_NAME, DEPARTURE_LOCATION_NAME, AIRLINE_NAME
        resultPackage.setColumnNames(new String[]{"ID вылета", "ID рейса", "Дата вылета", "Дата прилета", "Место прилета", "Место вылета", "Авиакомпания"});
        while (resultSet.next()) {
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(resultSet.getInt(1)));
            row.add(String.valueOf(resultSet.getInt(2)));
            row.add(new TimeCalendar(resultSet.getDate(3)).toTypedString(TimeCalendar.TimeCalendarType.FULL_DATE));
            row.add(new TimeCalendar(resultSet.getDate(4)).toTypedString(TimeCalendar.TimeCalendarType.FULL_DATE));
            row.add(resultSet.getString(5));
            row.add(resultSet.getString(6));
            row.add(resultSet.getString(7));
            allRows.add(row.toArray(new String[0]));
        }
        resultPackage.setResultRows(allRows.toArray(new String[0][]));
        return resultPackage;
    }
}
