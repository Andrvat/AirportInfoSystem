package forms;

import controller.ControllerManager;
import view.listenedButtons.MakeRequestButton;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

public class Request12Provider extends AbstractRequestProvider {
    public Request12Provider() {
        super("<html>Получить перечень и общее число свободных и забpониpованных мест " +
                        "на указанном вылете, " +
                        "на определенный день, по указанному маpшpуту, по цене, по времени вылета.</html>",

                "WITH ARRIVAL_LOCATIONS AS ( " +
                        "SELECT FLIGHT_ID, LOCATION_NAME AS ARRIVAL_LOCATION_NAME " +
                        "FROM FLIGHT " +
                        "LEFT OUTER JOIN LOCATION ON ARRIVAL_LOCATION_ID = LOCATION_ID), " +
                        "DEPARTURE_LOCATIONS AS ( " +
                        "SELECT FLIGHT_ID, LOCATION_NAME AS DEPARTURE_LOCATION_NAME " +
                        "FROM FLIGHT " +
                        "LEFT OUTER JOIN LOCATION ON DEPARTURE_LOCATION_ID = LOCATION_ID) " +
                        "SELECT DEPARTURE_ID, DESCRIPTION, COUNT(*) AS COUNTER " +
                        "FROM ( " +
                        "SELECT DEPARTURE_ID, SEAT, DESCRIPTION " +
                        "FROM TICKET " +
                        "LEFT JOIN TICKET_STATUS USING (TICKET_STATUS_ID) " +
                        "LEFT JOIN DEPARTURE USING (DEPARTURE_ID) " +
                        "LEFT JOIN FLIGHT USING (FLIGHT_ID) " +
                        "LEFT JOIN ARRIVAL_LOCATIONS USING (FLIGHT_ID) " +
                        "LEFT JOIN DEPARTURE_LOCATIONS USING (FLIGHT_ID) " +
                        "WHERE ",

                new LinkedHashMap<>() {{
                    put("Номер вылета", new String[]{"="});
                    put("День вылета", new String[]{"="});
                    put("Месяц вылета", new String[]{"="});
                    put("Год вылета", new String[]{"="});
                    put("Место вылета", new String[]{"="});
                    put("Место прилета", new String[]{"="});
                    put("Цена билета", new String[]{"<", ">", "="});
                    put("Время вылета", new String[]{"=", "<", ">"});
                }}
        );
    }

    @Override
    public RequestResultPackage getRequestResultRows(ControllerManager controllerManager) throws SQLException {
        var answers = this.getAnswers();
        var selectedOptions = this.getSelectedOptions();
        var resultPackage = new RequestResultPackage();
        var noOption = MakeRequestButton.NO_OPTION;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getRequestBlank());
        boolean previousExists = false;

        if (!noOption.equals(selectedOptions.get("Номер вылета"))) {
            stringBuilder.append("DEPARTURE_ID")
                    .append(" ")
                    .append(selectedOptions.get("Номер вылета"))
                    .append(" ")
                    .append(answers.get("Номер вылета"))
                    .append(" ");
            previousExists = true;
        }

        if (!noOption.equals(selectedOptions.get("Место вылета"))) {
            stringBuilder.append((previousExists) ? "AND " : "");
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
        if (!noOption.equals(selectedOptions.get("Цена билета"))) {
            stringBuilder.append((previousExists) ? "AND " : "");
            stringBuilder.append("TICKET_PRICE")
                    .append(" ")
                    .append(selectedOptions.get("Цена билета"))
                    .append(" ")
                    .append(answers.get("Цена билета"))
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
            previousExists = true;
        }
        if (!noOption.equals(selectedOptions.get("День вылета"))) {
            stringBuilder.append((previousExists) ? "AND " : "");
            stringBuilder.append("EXTRACT(DAY FROM DEPARTURE_DATE)")
                    .append(" ")
                    .append(selectedOptions.get("День вылета"))
                    .append(" ")
                    .append(answers.get("День вылета"))
                    .append(" ");
            previousExists = true;
        }
        if (!noOption.equals(selectedOptions.get("Месяц вылета"))) {
            stringBuilder.append((previousExists) ? "AND " : "");
            stringBuilder.append("EXTRACT(MONTH FROM DEPARTURE_DATE)")
                    .append(" ")
                    .append(selectedOptions.get("Месяц вылета"))
                    .append(" ")
                    .append(answers.get("Месяц вылета"))
                    .append(" ");
            previousExists = true;
        }
        if (!noOption.equals(selectedOptions.get("Год вылета"))) {
            stringBuilder.append((previousExists) ? "AND " : "");
            stringBuilder.append("EXTRACT(YEAR FROM DEPARTURE_DATE)")
                    .append(" ")
                    .append(selectedOptions.get("Год вылета"))
                    .append(" ")
                    .append(answers.get("Год вылета"))
                    .append(" ");
        }


        AbstractRequestProvider.removeWhereIfNeed(stringBuilder);
        stringBuilder.append(") " +
                "WHERE (DESCRIPTION = 'В продаже' OR DESCRIPTION = 'Забронирован') " +
                "GROUP BY (DEPARTURE_ID, DESCRIPTION)");

        ResultSet resultSet = controllerManager.getProvider().getStringsQueryResultSet(stringBuilder.toString(), Collections.emptyList());
        List<String[]> allRows = new ArrayList<>();
        // SELECT DEPARTURE_ID, DESCRIPTION, COUNT(*) AS COUNTER
        resultPackage.setColumnNames(new String[]{"Номер вылета", "Описание", "Число билетов"});
        while (resultSet.next()) {
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(resultSet.getInt(1)));
            row.add(resultSet.getString(2));
            row.add(String.valueOf(resultSet.getInt(3)));
            allRows.add(row.toArray(new String[0]));
        }
        resultPackage.setResultRows(allRows.toArray(new String[0][]));
        return resultPackage;
    }
}
