package forms;

import controller.ControllerManager;
import view.listenedButtons.MakeRequestButton;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

public class Request2Provider extends AbstractRequestProvider {
    public Request2Provider() {
        super("<html>Получить перечень и общее число работников в бригаде, по всем отделам, " +
                        "в указанном отделе, обслуживающих конкретный самолет, " +
                        "по возpасту, суммаpной (сpедней) заpплате в бpигаде.</html>",

                "WITH AVG_CREW_SALARY AS ( " +
                        "SELECT CREW_ID, AVG(SALARY) AS AVG_SALARY " +
                        "FROM EMPLOYEE " +
                        "GROUP BY CREW_ID) " +
                        "SELECT EMPLOYEE_ID, CREW_ID, SURNAME, NAME, PATRONYMIC, SEX, SALARY, DEPARTMENT_NAME " +
                        "FROM EMPLOYEE " +
                        "LEFT JOIN DEPARTMENT USING (DEPARTMENT_ID) " +
                        "JOIN AVG_CREW_SALARY USING (CREW_ID) " +
                        "WHERE ",

                new LinkedHashMap<>() {{
                    put("Бригада", new String[]{"="});
                    put("Отдел", new String[]{"="});
                    put("Зарплата отн-но средней", new String[]{"<", ">", "="});
                    put("Возраст", new String[]{"<", ">", "="});
                    put("Обсуживаемый самолет", new String[]{"="});
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

        if (!noOption.equals(selectedOptions.get("Бригада"))) {
            stringBuilder.append("CREW_ID")
                    .append(" ")
                    .append(selectedOptions.get("Бригада"))
                    .append(" ")
                    .append(answers.get("Бригада"))
                    .append(" ");
            previousExists = true;
        }
        if (!noOption.equals(selectedOptions.get("Отдел"))) {
            stringBuilder.append((previousExists) ? "AND " : "");
            stringBuilder.append("DEPARTMENT_NAME")
                    .append(" ")
                    .append(selectedOptions.get("Отдел"))
                    .append(" ")
                    .append("'")
                    .append(answers.get("Отдел"))
                    .append("'")
                    .append(" ");
            previousExists = true;
        }
        if (!noOption.equals(selectedOptions.get("Зарплата отн-но средней"))) {
            stringBuilder.append((previousExists) ? "AND " : "");
            stringBuilder.append("SALARY")
                    .append(" ")
                    .append(selectedOptions.get("Зарплата отн-но средней"))
                    .append(" ")
                    .append("AVG_SALARY")
                    .append(" ");
            previousExists = true;
        }
        if (!noOption.equals(selectedOptions.get("Возраст"))) {
            stringBuilder.append((previousExists) ? "AND " : "");
            stringBuilder.append("CURRENT_DATE - BIRTH_DATE")
                    .append(" ")
                    .append(selectedOptions.get("Возраст"))
                    .append(" ")
                    .append(answers.get("Возраст")).append(" * 365.25")
                    .append(" ");
            previousExists = true;
        }
        if (!noOption.equals(selectedOptions.get("Обсуживаемый самолет"))) {
            stringBuilder.append((previousExists) ? "AND " : "");
            stringBuilder.append("CREW_ID")
                    .append(" ")
                    .append("IN")
                    .append(" ")
                    .append("(")
                    .append("SELECT PILOT_CREW_ID AS CREW_ID FROM AIRPLANE WHERE AIRPLANE_ID")
                    .append(" ")
                    .append(selectedOptions.get("Обсуживаемый самолет"))
                    .append(" ")
                    .append(answers.get("Обсуживаемый самолет"))
                    .append(" ")
                    .append("UNION ALL")
                    .append(" ")
                    .append("SELECT TECHNICAL_CREW_ID FROM AIRPLANE WHERE AIRPLANE_ID")
                    .append(" ")
                    .append(selectedOptions.get("Обсуживаемый самолет"))
                    .append(" ")
                    .append(answers.get("Обсуживаемый самолет"))
                    .append(" ")
                    .append("UNION ALL")
                    .append(" ")
                    .append("SELECT SERVICE_CREW_ID FROM AIRPLANE WHERE AIRPLANE_ID")
                    .append(" ")
                    .append(selectedOptions.get("Обсуживаемый самолет"))
                    .append(" ")
                    .append(answers.get("Обсуживаемый самолет"))
                    .append(" ")
                    .append(")")
                    .append(" ");
        }
        AbstractRequestProvider.removeWhereIfNeed(stringBuilder);

        ResultSet resultSet = controllerManager.getProvider().getStringsQueryResultSet(stringBuilder.toString(), Collections.emptyList());
        List<String[]> allRows = new ArrayList<>();
        // EMPLOYEE_ID, CREW_ID, SURNAME, NAME, PATRONYMIC, SEX, SALARY, DEPARTMENT_NAME
        resultPackage.setColumnNames(new String[]{"ID сотрудника", "ID бригады", "Фамилия", "Имя", "Отчество", "Пол", "Зарплата", "Отдел"});
        while (resultSet.next()) {
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(resultSet.getInt(1)));
            row.add(String.valueOf(resultSet.getInt(2)));
            row.add(resultSet.getString(3));
            row.add(resultSet.getString(4));
            row.add(resultSet.getString(5));
            row.add(("Y".equals(resultSet.getString(6))) ? "Муж." : "Жен.");
            row.add(String.valueOf(resultSet.getFloat(7)));
            row.add(resultSet.getString(8));
            allRows.add(row.toArray(new String[0]));
        }
        resultPackage.setResultRows(allRows.toArray(new String[0][]));
        return resultPackage;
    }
}
