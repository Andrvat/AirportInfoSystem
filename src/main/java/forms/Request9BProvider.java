package forms;

import controller.ControllerManager;
import view.listenedButtons.MakeRequestButton;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

public class Request9BProvider extends AbstractRequestProvider {
    private static final String LAST_REQUEST_BLANK_PART =
            "), LAST_STATUSES_ORDER AS ( " +
                    "SELECT RECORD_ID, DEPARTURE_ID, SEAT, " +
                    "ROW_NUMBER() OVER (PARTITION BY DEPARTURE_ID, SEAT ORDER BY STATUS_SET_DATE DESC) AS ROW_NUM, " +
                    "TICKET_STATUS_ID  " +
                    "FROM TICKET_STATUS_HISTORY " +
                    "RIGHT JOIN SUITABLE_DEPARTURES USING (DEPARTURE_ID)  " +
                    "LEFT JOIN TICKET_STATUS USING (TICKET_STATUS_ID)  " +
                    "ORDER BY DEPARTURE_ID),    " +
                    "LAST_STATUSES AS (  " +
                    "SELECT RECORD_ID, DEPARTURE_ID, SEAT  " +
                    "FROM LAST_STATUSES_ORDER  " +
                    "LEFT JOIN TICKET_STATUS USING (TICKET_STATUS_ID)  " +
                    "WHERE ROW_NUM = 1 AND TICKET_STATUS.DESCRIPTION = 'Выкуплен'  )    " +
                    "SELECT AVG(SALED_OUT_TICKETS_NUM) AS AVG_SALED_TICKETS_NUM  " +
                    "FROM (  " +
                    "SELECT DEPARTURE_ID, COUNT(RECORD_ID) AS SALED_OUT_TICKETS_NUM  " +
                    "FROM LAST_STATUSES  " +
                    "GROUP BY DEPARTURE_ID)";

    public Request9BProvider() {
        super("<html>Получить сpеднее количество пpоданных билетов на опpеделенный маpшpут, " +
                        "по длительности пеpелета, по цене билета, по вpемени вылета.</html>",

                "WITH ARRIVAL_LOCATIONS AS ( " +
                        "SELECT FLIGHT_ID, LOCATION_NAME AS ARRIVAL_LOCATION_NAME " +
                        "FROM FLIGHT " +
                        "LEFT OUTER JOIN LOCATION ON ARRIVAL_LOCATION_ID = LOCATION_ID), " +
                        "DEPARTURE_LOCATIONS AS (" +
                        "SELECT FLIGHT_ID, LOCATION_NAME AS DEPARTURE_LOCATION_NAME " +
                        "FROM FLIGHT " +
                        "LEFT OUTER JOIN LOCATION ON DEPARTURE_LOCATION_ID = LOCATION_ID), " +
                        "SUITABLE_DEPARTURES AS ( " +
                        "SELECT DEPARTURE_ID " +
                        "FROM DEPARTURE_STATUS_HISTORY " +
                        "LEFT JOIN DEPARTURE_STATUS ON DEPARTURE_STATUS_ID = STATUS_ID " +
                        "LEFT JOIN DEPARTURE USING (DEPARTURE_ID) " +
                        "LEFT JOIN FLIGHT USING (FLIGHT_ID) " +
                        "LEFT OUTER JOIN ARRIVAL_LOCATIONS USING (FLIGHT_ID) " +
                        "LEFT OUTER JOIN DEPARTURE_LOCATIONS USING (FLIGHT_ID) " +
                        "WHERE ",

                new LinkedHashMap<>() {{
                    put("Статус вылета", new String[]{"="});
                    put("Место вылета", new String[]{"="});
                    put("Место прилета", new String[]{"="});
                    put("Время в пути", new String[]{"=", "<", ">"});
                    put("Стоимость билета", new String[]{"=", "<", ">"});
                    put("Время вылета", new String[]{"=", "<", ">"});
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
        if (!noOption.equals(selectedOptions.get("Статус вылета"))) {
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
        if (!noOption.equals(selectedOptions.get("Место вылета"))) {
            stringBuilder.append((previousExists) ? "AND " : "");
            stringBuilder.append("ARRIVAL_LOCATION_NAME")
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
            stringBuilder.append("DEPARTURE_LOCATION_NAME")
                    .append(" ")
                    .append(selectedOptions.get("Место прилета"))
                    .append(" ")
                    .append("'")
                    .append(answers.get("Место прилета"))
                    .append("'")
                    .append(" ");
            previousExists = true;
        }
        if (!noOption.equals(selectedOptions.get("Время в пути"))) {
            String inputDate = "TO_DATE('2015/01/01 " + answers.get("Время в пути") + "', 'YYYY/MM/DD HH24:MI:SS')";
            stringBuilder.append((previousExists) ? "AND " : "");
            stringBuilder.append("TRAVEL_TIME - TRUNC(TRAVEL_TIME)")
                    .append(" ")
                    .append(selectedOptions.get("Время в пути"))
                    .append(" ")
                    .append(inputDate)
                    .append(" - ")
                    .append("TRUNC(").append(inputDate).append(")")
                    .append(" ");
            previousExists = true;
        }
        if (!noOption.equals(selectedOptions.get("Стоимость билета"))) {
            stringBuilder.append((previousExists) ? "AND " : "");
            stringBuilder.append("TICKET_PRICE")
                    .append(" ")
                    .append(selectedOptions.get("Стоимость билета"))
                    .append(" ")
                    .append(answers.get("Стоимость билета"))
                    .append(" ");
            previousExists = true;
        }
        if (!noOption.equals(selectedOptions.get("Время вылета"))) {
            String inputDate = "TO_DATE('2015/01/01 " + answers.get("Время вылета") + "', 'YYYY/MM/DD HH24:MI:SS')";
            stringBuilder.append((previousExists) ? "AND " : "");
            stringBuilder.append("DEPARTURE_DATE - TRUNC(DEPARTURE_DATE)")
                    .append(" ")
                    .append(selectedOptions.get("Время вылета"))
                    .append(" ")
                    .append(inputDate)
                    .append(" - ")
                    .append("TRUNC(").append(inputDate).append(")")
                    .append(" ");
        }
        AbstractRequestProvider.removeWhereIfNeed(stringBuilder);
        stringBuilder.append(Request9BProvider.LAST_REQUEST_BLANK_PART);
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
        resultPackage.setColumnNames(new String[]{"Средняя цена проданных билетов"});
        while (resultSet.next()) {
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(resultSet.getFloat(1)));
            allRows.add(row.toArray(new String[0]));
        }
        resultPackage.setResultRows(allRows.toArray(new String[0][]));
        return resultPackage;
    }
}
