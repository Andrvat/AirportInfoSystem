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

public class Request7Provider extends AbstractRequestProvider {
    public Request7Provider() {
        super("<html>Получить перечень и общее число отмененных pейсов полностью, " +
                        "в указанном напpавлении, по указанному маpшpуту, " +
                        "по количеству невостpебованных мест, " +
                        "по пpоцентному соотношению невостpебованных мест.</html>",

                "WITH UNCLAIMED_TICKETS AS( " +
                        "SELECT DEPARTURE_ID, COUNT(*) AS UNCLAIMED_SEAT_NUM " +
                        "FROM TICKET " +
                        "WHERE TICKET_STATUS_ID = 1 " +
                        "GROUP BY DEPARTURE_ID), " +
                        "ARRIVAL_LOCATIONS AS ( " +
                        "SELECT FLIGHT_ID, LOCATION_NAME AS ARRIVAL_LOCATION_NAME " +
                        "FROM FLIGHT " +
                        "LEFT OUTER JOIN LOCATION ON ARRIVAL_LOCATION_ID = LOCATION_ID), " +
                        "DEPARTURE_LOCATIONS AS ( " +
                        "SELECT FLIGHT_ID, LOCATION_NAME AS DEPARTURE_LOCATION_NAME " +
                        "FROM FLIGHT " +
                        "LEFT OUTER JOIN LOCATION ON DEPARTURE_LOCATION_ID = LOCATION_ID) " +
                        "SELECT DEPARTURE_ID, FLIGHT_ID, DEPARTURE_DATE, ARRIVAL_DATE, DEPARTURE_LOCATION_NAME, ARRIVAL_LOCATION_NAME, AIRLINE_NAME " +
                        "FROM DEPARTURE " +
                        "LEFT OUTER JOIN FLIGHT USING (FLIGHT_ID) " +
                        "LEFT OUTER JOIN AIRPLANE_TYPE USING (AIRPLANE_TYPE_ID) " +
                        "LEFT OUTER JOIN AIRLINE USING (AIRLINE_ID) " +
                        "LEFT OUTER JOIN ARRIVAL_LOCATIONS USING (FLIGHT_ID) " +
                        "LEFT OUTER JOIN DEPARTURE_LOCATIONS USING (FLIGHT_ID) " +
                        "LEFT OUTER JOIN DEPARTURE_STATUS USING (DEPARTURE_STATUS_ID) " +
                        "LEFT OUTER JOIN UNCLAIMED_TICKETS USING (DEPARTURE_ID) " +
                        "WHERE ",

                new LinkedHashMap<>() {{
                    put("Место вылета", new String[]{"="});
                    put("Место прилета", new String[]{"="});
                    put("Статус вылета", new String[]{"="});
                    put("Число невостребованных мест", new String[]{"<", ">", "="});
                    put("Процент невостребованных мест", new String[]{"<", ">", "="});
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

        if (!noOption.equals(selectedOptions.get("Место вылета"))) {
            stringBuilder.append("DEPARTURE_LOCATION_NAME")
                    .append(" ")
                    .append(selectedOptions.get("Место вылета"))
                    .append(" ")
                    .append("'")
                    .append(answers.get("Место вылета"))
                    .append("'")
                    .append(" ");
            previousExists = true;
        }
        if (!noOption.equals(selectedOptions.get("Место прилета"))) {
            stringBuilder.append((previousExists) ? "AND " : "");
            stringBuilder.append("ARRIVAL_LOCATION_NAME")
                    .append(" ")
                    .append(selectedOptions.get("Место прилета"))
                    .append(" ")
                    .append("'")
                    .append(answers.get("Место прилета"))
                    .append("'")
                    .append(" ");
            previousExists = true;
        }
        if (!noOption.equals(selectedOptions.get("Статус вылета"))) {
            stringBuilder.append((previousExists) ? "AND " : "");
            stringBuilder.append("DEPARTURE_STATUS.DESCRIPTION")
                    .append(" ")
                    .append(selectedOptions.get("Статус вылета"))
                    .append(" ")
                    .append("'")
                    .append(answers.get("Статус вылета"))
                    .append("'")
                    .append(" ");
            previousExists = true;
        }
        if (!noOption.equals(selectedOptions.get("Число невостребованных мест"))) {
            stringBuilder.append((previousExists) ? "AND " : "");
            stringBuilder.append("UNCLAIMED_SEAT_NUM")
                    .append(" ")
                    .append(selectedOptions.get("Число невостребованных мест"))
                    .append(" ")
                    .append(answers.get("Число невостребованных мест"))
                    .append(" ");
            previousExists = true;
        }
        if (!noOption.equals(selectedOptions.get("Процент невостребованных мест"))) {
            stringBuilder.append((previousExists) ? "AND " : "");
            stringBuilder.append("UNCLAIMED_SEAT_NUM * 100 / PEOPLE_CAPACITY")
                    .append(" ")
                    .append(selectedOptions.get("Процент невостребованных мест"))
                    .append(" ")
                    .append(answers.get("Процент невостребованных мест"))
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
        // SELECT DEPARTURE_ID, FLIGHT_ID, DEPARTURE_DATE, ARRIVAL_DATE, DEPARTURE_LOCATION_NAME, ARRIVAL_LOCATION_NAME, AIRLINE_NAME
        resultPackage.setColumnNames(new String[]{"ID вылета", "ID рейса", "Дата вылета", "Дата прилета", "Место вылета", "Место прилета", "Авиакомпания"});
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
