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

public class Request8BProvider extends AbstractRequestProvider {
    public Request8BProvider() {
        super("<html>Получить количество сданных билетов за вpемя задеpжки</html>",

                "WITH ARRIVAL_LOCATIONS AS ( " +
                        "SELECT FLIGHT_ID, LOCATION_NAME AS ARRIVAL_LOCATION_NAME " +
                        "FROM FLIGHT " +
                        "LEFT OUTER JOIN LOCATION ON ARRIVAL_LOCATION_ID = LOCATION_ID), " +
                        "DEPARTURE_LOCATIONS AS ( " +
                        "SELECT FLIGHT_ID, LOCATION_NAME AS DEPARTURE_LOCATION_NAME " +
                        "FROM FLIGHT " +
                        "LEFT OUTER JOIN LOCATION ON DEPARTURE_LOCATION_ID = LOCATION_ID), " +
                        "DELAYED_DEPARTURES AS ( " +
                        "SELECT DEPARTURE_ID, STATUS_SET_DATE AS DELAYED_DATE " +
                        "FROM DEPARTURE_STATUS_HISTORY " +
                        "LEFT JOIN DEPARTURE_STATUS ON STATUS_ID = DEPARTURE_STATUS_ID " +
                        "WHERE (DEPARTURE_STATUS.DESCRIPTION = 'Задержан')), " +
                        "NEXT_AFTER_DELAYED AS ( " +
                        "SELECT DEPARTURE_ID, MIN(STATUS_SET_DATE) AS NEXT_DATE " +
                        "FROM DEPARTURE_STATUS_HISTORY " +
                        "LEFT JOIN DELAYED_DEPARTURES USING (DEPARTURE_ID) " +
                        "WHERE DELAYED_DATE IS NOT NULL AND STATUS_SET_DATE > DELAYED_DATE " +
                        "GROUP BY DEPARTURE_ID), " +
                        "DELAY_PERIODS AS ( " +
                        "SELECT DEPARTURE_ID, DELAYED_DATE, NEXT_DATE " +
                        "FROM DELAYED_DEPARTURES " +
                        "LEFT JOIN NEXT_AFTER_DELAYED USING(DEPARTURE_ID) " +
                        ") " +
                        "SELECT COUNT(*) AS HANDED_IN_TICKETS " +
                        "FROM TICKET_STATUS_HISTORY " +
                        "LEFT JOIN TICKET_STATUS USING(TICKET_STATUS_ID) " +
                        "LEFT JOIN DELAY_PERIODS USING(DEPARTURE_ID) " +
                        "WHERE (DESCRIPTION = 'В продаже') " +
                        "AND (STATUS_SET_DATE > DELAYED_DATE) " +
                        "AND (STATUS_SET_DATE < NEXT_DATE) ",

                new LinkedHashMap<>() {{
                    put("Номер вылета", new String[]{"="});
                }}
        );
    }

    private String getCompletedRequestQuery() {
        var answers = this.getAnswers();
        var selectedOptions = this.getSelectedOptions();
        var noOption = MakeRequestButton.NO_OPTION;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getRequestBlank());
        if (!noOption.equals(selectedOptions.get("Номер вылета"))) {
            stringBuilder.append("AND ");
            stringBuilder.append("DEPARTURE_ID")
                    .append(" ")
                    .append(selectedOptions.get("Номер вылета"))
                    .append(" ")
                    .append(answers.get("Номер вылета"))
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
        resultPackage.setColumnNames(new String[]{"Количество сданных билетов"});
        while (resultSet.next()) {
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(resultSet.getInt(1)));
            allRows.add(row.toArray(new String[0]));
        }
        resultPackage.setResultRows(allRows.toArray(new String[0][]));
        return resultPackage;
    }
}
