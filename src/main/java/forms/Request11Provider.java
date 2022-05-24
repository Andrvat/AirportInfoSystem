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

public class Request11Provider extends AbstractRequestProvider {
    public Request11Provider() {
        super("<html>Получить перечень и общее число пассажиpов " +
                        "на данном pейсе, " +
                        "улетевших в указанный день, " +
                        "улетевших за гpаницу в указанный день, " +
                        "по пpизнаку сдачи вещей в багажное отделение, по половому пpизнаку, по возpасту.</html>",

                "SELECT DEPARTURE_ID, DEPARTURE_DATE, SEAT, PASSENGER_ID, SURNAME, NAME, PATRONYMIC, SEX, BIRTH_DATE, CARGO " +
                        "FROM ( " +
                        "SELECT RECORD_ID, DEPARTURE_ID, SEAT, DESCRIPTION, STATUS_SET_DATE, " +
                        "ROW_NUMBER() OVER (PARTITION BY DEPARTURE_ID, SEAT ORDER BY STATUS_SET_DATE DESC) AS ROW_NUM, PASSENGER_ID " +
                        "FROM TICKET_STATUS_HISTORY " +
                        "LEFT JOIN TICKET_STATUS USING (TICKET_STATUS_ID) " +
                        ") " +
                        "LEFT JOIN PASSENGER USING (PASSENGER_ID) " +
                        "LEFT JOIN DEPARTURE USING (DEPARTURE_ID) " +
                        "LEFT JOIN FLIGHT USING (FLIGHT_ID) " +
                        "LEFT JOIN FLIGHT_CATEGORY USING (FLIGHT_CATEGORY_ID) " +
                        "WHERE ROW_NUM = 1 AND DESCRIPTION = 'Выкуплен' ",

                new LinkedHashMap<>() {{
                    put("Номер вылета", new String[]{"="});
                    put("День вылета", new String[]{"="});
                    put("Месяц вылета", new String[]{"="});
                    put("Год вылета", new String[]{"="});
                    put("Категория рейса", new String[]{"="});
                    put("Наличие багажа (Да/Нет)", new String[]{"="});
                    put("Пол (М/Ж)", new String[]{"="});
                    put("Возраст", new String[]{"<", ">", "="});
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
        if (!noOption.equals(selectedOptions.get("Категория рейса"))) {
            stringBuilder.append("AND ");
            stringBuilder.append("CATEGORY_NAME")
                    .append(" ")
                    .append(selectedOptions.get("Категория рейса"))
                    .append(" ")
                    .append("'")
                    .append(answers.get("Категория рейса"))
                    .append("'")
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
        if (!noOption.equals(selectedOptions.get("Наличие багажа (Да/Нет)"))) {
            stringBuilder.append("AND ");
            stringBuilder.append("CARGO")
                    .append(" ")
                    .append(selectedOptions.get("Наличие багажа (Да/Нет)"))
                    .append(" ")
                    .append("'")
                    .append("Да".equals(answers.get("Наличие багажа (Да/Нет)")) ? "Y" : "N")
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
        // SELECT DEPARTURE_ID, DEPARTURE_DATE, SEAT, PASSENGER_ID, SURNAME, NAME, PATRONYMIC, SEX, BIRTH_DATE, CARGO
        resultPackage.setColumnNames(new String[]{"Номер вылета", "Дата вылета", "Место", "ID пассажира", "Фамилия", "Имя", "Отчество", "Пол", "Дата рожд.", "Нал. багажа"});
        while (resultSet.next()) {
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(resultSet.getInt(1)));
            row.add(new TimeCalendar(resultSet.getDate(2)).toTypedString(TimeCalendar.TimeCalendarType.FULL_DATE));
            row.add(String.valueOf(resultSet.getInt(3)));
            row.add(String.valueOf(resultSet.getInt(4)));
            row.add(resultSet.getString(5));
            row.add(resultSet.getString(6));
            row.add(resultSet.getString(7));
            row.add("Y".equals(resultSet.getString(8)) ? "Муж." : "Жен.");
            row.add(new TimeCalendar(resultSet.getDate(9)).toTypedString(TimeCalendar.TimeCalendarType.DATE_ONLY));
            row.add("Y".equals(resultSet.getString(10)) ? "Да" : "Нет");
            allRows.add(row.toArray(new String[0]));
        }
        resultPackage.setResultRows(allRows.toArray(new String[0][]));
        return resultPackage;

    }
}
