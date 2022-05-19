package forms;

import controller.ControllerManager;
import model.support.TimeCalendar;
import view.listenedButtons.MakeRequestButton;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Request6Provider extends AbstractRequestProvider {
    public Request6Provider() {
        super("<html>Получить перечень и общее число pейсов по указанному маpшpуту, " +
                        "по длительности пеpелета, по цене билета и по всем этим кpитеpиям сpазу</html>",

                "SELECT FLIGHT_ID, DEPARTURE_LOCATION_NAME, LOCATION_NAME AS ARRIVAL_LOCATION_NAME, TRAVEL_TIME, TICKET_PRICE " +
                        "FROM FLIGHT " +
                        "LEFT JOIN (SELECT FLIGHT_ID, LOCATION_NAME AS DEPARTURE_LOCATION_NAME " +
                        "FROM FLIGHT " +
                        "LEFT JOIN LOCATION ON DEPARTURE_LOCATION_ID = LOCATION_ID) USING (FLIGHT_ID) " +
                        "LEFT JOIN LOCATION ON ARRIVAL_LOCATION_ID = LOCATION_ID " +
                        "WHERE ",

                new LinkedHashMap<>() {{
                    put("Место вылета", new String[]{"="});
                    put("Место прилета", new String[]{"="});
                    put("Длительность перелета", new String[]{"<", ">", "="});
                    put("Цена билета", new String[]{"<", ">", "="});
                }});
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
        if (!noOption.equals(selectedOptions.get("Длительность перелета"))) {
            String timeDate = "TO_DATE('" + answers.get("Длительность перелета") + "', 'HH24::MI:SS') - " +
                    "TRUNC(TO_DATE('" + answers.get("Длительность перелета") + "', 'HH24::MI:SS'))";
            stringBuilder.append((previousExists) ? "AND " : "");
            stringBuilder.append("TRAVEL_TIME - TRUNC(TRAVEL_TIME)")
                    .append(" ")
                    .append(selectedOptions.get("Длительность перелета"))
                    .append(" ")
                    .append(timeDate)
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
        }

        if (stringBuilder.toString().endsWith("WHERE ")) {
            stringBuilder.replace(stringBuilder.length() - "WHERE ".length(), stringBuilder.length(), "");
        }

        ResultSet resultSet = controllerManager.getProvider().getStringsQueryResultSet(stringBuilder.toString(), Collections.emptyList());
        List<String[]> allRows = new ArrayList<>();
        // SELECT FLIGHT_ID, DEPARTURE_LOCATION_NAME, LOCATION_NAME AS ARRIVAL_LOCATION_NAME, TRAVEL_TIME, TICKET_PRICE
        resultPackage.setColumnNames(new String[]{"ID рейса", "Место вылета", "Место прилета", "Время в пути", "Стоимость билета"});
        while (resultSet.next()) {
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(resultSet.getInt(1)));
            row.add(resultSet.getString(2));
            row.add(resultSet.getString(3));
            row.add(new TimeCalendar(resultSet.getDate(4)).toString());
            row.add(String.valueOf(resultSet.getFloat(5)));
            allRows.add(row.toArray(new String[0]));
        }
        resultPackage.setResultRows(allRows.toArray(new String[0][]));
        return resultPackage;
    }
}
