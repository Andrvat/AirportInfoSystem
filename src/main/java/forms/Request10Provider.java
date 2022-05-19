package forms;

import controller.ControllerManager;
import view.listenedButtons.MakeRequestButton;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Request10Provider extends AbstractRequestProvider {
    public Request10Provider() {
        super("<html>Получить перечень и общее число авиаpейсов указанной категоpии, " +
                        "в определенном напpавлении, с указанным типом самолета</html>",

                "SELECT FLIGHT_ID, DEPARTURE_LOCATION_NAME, LOCATION_NAME AS ARRIVAL_LOCATION_NAME, CATEGORY_NAME, TYPE_NAME " +
                        "FROM FLIGHT " +
                        "LEFT JOIN (SELECT FLIGHT_ID, LOCATION_NAME AS DEPARTURE_LOCATION_NAME " +
                        "FROM FLIGHT " +
                        "LEFT JOIN LOCATION ON DEPARTURE_LOCATION_ID = LOCATION_ID) USING (FLIGHT_ID) " +
                        "LEFT JOIN LOCATION ON ARRIVAL_LOCATION_ID = LOCATION_ID " +
                        "LEFT JOIN FLIGHT_CATEGORY USING (FLIGHT_CATEGORY_ID) " +
                        "LEFT JOIN AIRPLANE_TYPE USING (AIRPLANE_TYPE_ID) " +
                        "WHERE ",

                new LinkedHashMap<>() {{
                    put("Место вылета", new String[]{"="});
                    put("Место прилета", new String[]{"="});
                    put("Категория рейса", new String[]{"="});
                    put("Тип самолета", new String[]{"="});
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
            stringBuilder.append("LOCATION_NAME")
                    .append(" ")
                    .append(selectedOptions.get("Место прилета"))
                    .append(" ")
                    .append("'")
                    .append(answers.get("Место прилета"))
                    .append("'")
                    .append(" ");
            previousExists = true;
        }
        if (!noOption.equals(selectedOptions.get("Категория рейса"))) {
            stringBuilder.append((previousExists) ? "AND " : "");
            stringBuilder.append("CATEGORY_NAME")
                    .append(" ")
                    .append(selectedOptions.get("Категория рейса"))
                    .append(" ")
                    .append("'")
                    .append(answers.get("Категория рейса"))
                    .append("'")
                    .append(" ");
            previousExists = true;
        }
        if (!noOption.equals(selectedOptions.get("Тип самолета"))) {
            stringBuilder.append((previousExists) ? "AND " : "");
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

        ResultSet resultSet = controllerManager.getProvider().getStringsQueryResultSet(stringBuilder.toString(), Collections.emptyList());
        List<String[]> allRows = new ArrayList<>();
        // SELECT FLIGHT_ID, DEPARTURE_LOCATION_NAME, LOCATION_NAME AS ARRIVAL_LOCATION_NAME, CATEGORY_NAME, TYPE_NAME
        resultPackage.setColumnNames(new String[]{"ID рейса", "Место вылета", "Место прилета", "Категория рейса", "Тип самолета"});
        while (resultSet.next()) {
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(resultSet.getInt(1)));
            row.add(resultSet.getString(2));
            row.add(resultSet.getString(3));
            row.add(resultSet.getString(4));
            row.add(resultSet.getString(5));
            allRows.add(row.toArray(new String[0]));
        }
        resultPackage.setResultRows(allRows.toArray(new String[0][]));
        return resultPackage;
    }
}
