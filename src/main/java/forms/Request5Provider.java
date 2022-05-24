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

public class Request5Provider extends AbstractRequestProvider {
    public Request5Provider() {
        super(
                "<html>Получить перечень и общее число самолетов, " +
                        "пpошедших техосмотp за определенный пеpиод вpемени, по возpасту самолета.</html>",

                "SELECT DISTINCT AIRPLANE_ID, TYPE_NAME, FINAL_SERVICEABILITY, " +
                        "(EXTRACT(YEAR FROM CURRENT_DATE) - MANUFACTURE_YEAR) AS AIRPLANE_AGE " +
                        "FROM AIRPLANE_MAINTENANCE_HISTORY " +
                        "LEFT JOIN AIRPLANE USING (AIRPLANE_ID) " +
                        "LEFT JOIN AIRPLANE_TYPE USING (AIRPLANE_TYPE_ID) " +
                        "WHERE MAINTENANCE_TYPE_ID = 1 ",

                new LinkedHashMap<>() {{
                    put("Техосмотр, пройденный с", new String[]{"="});
                    put("Техосмотр, пройденный по", new String[]{"="});
                    put("Возраст самолета", new String[]{"=", "<", ">"});
                }}
        );
    }

    private String getCompletedRequestQuery() {
        var answers = this.getAnswers();
        var selectedOptions = this.getSelectedOptions();
        var noOption = MakeRequestButton.NO_OPTION;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getRequestBlank());

        if (!noOption.equals(selectedOptions.get("Техосмотр, пройденный с"))) {
            stringBuilder.append("AND ");
            stringBuilder.append("MAINTENANCE_DATE")
                    .append(" ")
                    .append(">=")
                    .append(" ")
                    .append("TO_DATE('")
                    .append(answers.get("Техосмотр, пройденный с"))
                    .append("', 'YYYY/MM/DD')")
                    .append(" ");
        }
        if (!noOption.equals(selectedOptions.get("Техосмотр, пройденный по"))) {
            stringBuilder.append("AND ");
            stringBuilder.append("MAINTENANCE_DATE")
                    .append(" ")
                    .append("<=")
                    .append(" ")
                    .append("TO_DATE('")
                    .append(answers.get("Техосмотр, пройденный по"))
                    .append("', 'YYYY/MM/DD')")
                    .append(" ");
        }
        if (!noOption.equals(selectedOptions.get("Возраст самолета"))) {
            stringBuilder.append("AND ");
            stringBuilder.append("(EXTRACT(YEAR FROM CURRENT_DATE) - MANUFACTURE_YEAR)")
                    .append(" ")
                    .append(selectedOptions.get("Возраст самолета"))
                    .append(" ")
                    .append(answers.get("Возраст самолета"))
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
        // SELECT DISTINCT AIRPLANE_ID, TYPE_NAME, FINAL_SERVICEABILITY,
        // (EXTRACT(YEAR FROM CURRENT_DATE) - MANUFACTURE_YEAR) AS AIRPLANE_AGE
        resultPackage.setColumnNames(new String[]{"ID самолета", "Тип самолета", "Исправность", "Возраст самолета"});
        while (resultSet.next()) {
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(resultSet.getInt(1)));
            row.add(resultSet.getString(2));
            row.add("Y".equals(resultSet.getString(3)) ? "Да" : "Нет");
            row.add(String.valueOf(resultSet.getInt(4)));
            allRows.add(row.toArray(new String[0]));
        }
        resultPackage.setResultRows(allRows.toArray(new String[0][]));
        return resultPackage;
    }
}
