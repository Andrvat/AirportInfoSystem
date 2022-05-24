package forms;

import controller.ControllerManager;
import view.listenedButtons.MakeRequestButton;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

public class Request13Provider extends AbstractRequestProvider {
    public Request13Provider() {
        super("<html>Получить общее число сданных билетов на некоторый pейс, " +
                        "в указанный день, по определенному маpшpуту, по цене билета, по возpасту, полу.</html>",

                "WITH ARRIVAL_LOCATIONS AS ( " +
                        "SELECT FLIGHT_ID, LOCATION_NAME AS ARRIVAL_LOCATION_NAME " +
                        "FROM FLIGHT " +
                        "LEFT OUTER JOIN LOCATION ON ARRIVAL_LOCATION_ID = LOCATION_ID), " +
                        "DEPARTURE_LOCATIONS AS ( " +
                        "SELECT FLIGHT_ID, LOCATION_NAME AS DEPARTURE_LOCATION_NAME " +
                        "FROM FLIGHT " +
                        "LEFT OUTER JOIN LOCATION ON DEPARTURE_LOCATION_ID = LOCATION_ID) " +
                        "SELECT COUNT(SEAT) AS RETURNED_TICKETS_NUM FROM ( " +
                        "SELECT RECORD_ID, SEAT, DESCRIPTION, ROW_NUM, SEX, BIRTH_DATE, TICKET_PRICE, DEPARTURE_LOCATION_NAME, ARRIVAL_LOCATION_NAME " +
                        "FROM ( " +
                        "SELECT RECORD_ID, SEAT, DESCRIPTION, TICKET_STATUS_HISTORY.STATUS_SET_DATE, SEX, BIRTH_DATE, TICKET_PRICE, DEPARTURE_LOCATION_NAME, ARRIVAL_LOCATION_NAME, " +
                        "ROW_NUMBER() OVER (PARTITION BY DEPARTURE_ID, SEAT ORDER BY TICKET_STATUS_HISTORY.STATUS_SET_DATE DESC) AS ROW_NUM " +
                        "FROM TICKET_STATUS_HISTORY " +
                        "LEFT JOIN TICKET_STATUS USING (TICKET_STATUS_ID) " +
                        "LEFT JOIN DEPARTURE USING (DEPARTURE_ID) " +
                        "LEFT JOIN FLIGHT USING (FLIGHT_ID) " +
                        "LEFT JOIN PASSENGER USING (PASSENGER_ID) " +
                        "LEFT JOIN ARRIVAL_LOCATIONS USING (FLIGHT_ID) " +
                        "LEFT JOIN DEPARTURE_LOCATIONS USING (FLIGHT_ID) " +
                        "WHERE (DEPARTURE_ID = @dep_id@) " +
                        ") WHERE ((ROW_NUM = 1 AND DESCRIPTION = 'В продаже') OR ROW_NUM = 2) " +
                        ") WHERE (ROW_NUM = 2 AND DESCRIPTION = 'Выкуплен') ",

                new LinkedHashMap<>() {{
                    put("Номер вылета", new String[]{"="});
                    put("Место вылета", new String[]{"="});
                    put("Место прилета", new String[]{"="});
                    put("День вылета", new String[]{"="});
                    put("Месяц вылета", new String[]{"="});
                    put("Год вылета", new String[]{"="});
                    put("Цена билета", new String[]{"<", ">", "="});
                    put("Пол (М/Ж)", new String[]{"="});
                    put("Возраст", new String[]{"<", ">", "="});
                }}
        );
    }

    private String getCompletedRequestQuery() {
        var answers = this.getAnswers();
        var selectedOptions = this.getSelectedOptions();
        var noOption = MakeRequestButton.NO_OPTION;
        StringBuilder stringBuilder = new StringBuilder(this.getRequestBlank()
                .replaceAll("@dep_id@", answers.get("Номер вылета")));
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
        if (!noOption.equals(selectedOptions.get("День вылета"))) {
            stringBuilder.append("AND ");
            stringBuilder.append("EXTRACT(DAY FROM DEPARTURE_DATE)")
                    .append(" ")
                    .append(selectedOptions.get("День вылета"))
                    .append(" ")
                    .append(answers.get("День вылета"))
                    .append(" ");
        }
        if (!noOption.equals(selectedOptions.get("Месяц вылета"))) {
            stringBuilder.append("AND ");
            stringBuilder.append("EXTRACT(MONTH FROM DEPARTURE_DATE)")
                    .append(" ")
                    .append(selectedOptions.get("Месяц вылета"))
                    .append(" ")
                    .append(answers.get("Месяц вылета"))
                    .append(" ");
        }
        if (!noOption.equals(selectedOptions.get("Год вылета"))) {
            stringBuilder.append("AND ");
            stringBuilder.append("EXTRACT(YEAR FROM DEPARTURE_DATE)")
                    .append(" ")
                    .append(selectedOptions.get("Год вылета"))
                    .append(" ")
                    .append(answers.get("Год вылета"))
                    .append(" ");
        }
        if (!noOption.equals(selectedOptions.get("Цена билета"))) {
            stringBuilder.append("AND ");
            stringBuilder.append("TICKET_PRICE")
                    .append(" ")
                    .append(selectedOptions.get("Цена билета"))
                    .append(" ")
                    .append(answers.get("Цена билета"))
                    .append(" ");
        }
        if (!noOption.equals(selectedOptions.get("Пол (М/Ж)"))) {
            stringBuilder.append("AND ");
            stringBuilder.append("SEX")
                    .append(" ")
                    .append(selectedOptions.get("Пол (М/Ж)"))
                    .append(" ")
                    .append("'")
                    .append("М".equals(answers.get("Пол (М/Ж)")) ? "Y" : "N")
                    .append("'")
                    .append(" ");
        }
        if (!noOption.equals(selectedOptions.get("Возраст"))) {
            stringBuilder.append("AND ");
            stringBuilder.append("CURRENT_DATE - BIRTH_DATE")
                    .append(" ")
                    .append(selectedOptions.get("Возраст"))
                    .append(" ")
                    .append(answers.get("Возраст")).append(" * 365.25")
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
        // COUNT(SEAT) AS RETURNED_TICKETS_NUM
        resultPackage.setColumnNames(new String[]{"Число сданных билетов"});
        while (resultSet.next()) {
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(resultSet.getInt(1)));
            allRows.add(row.toArray(new String[0]));
        }
        resultPackage.setResultRows(allRows.toArray(new String[0][]));
        return resultPackage;
    }
}
