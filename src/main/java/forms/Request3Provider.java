package forms;

import controller.ControllerManager;
import view.listenedButtons.MakeRequestButton;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Request3Provider extends AbstractRequestProvider {
    public Request3Provider() {
        super("<html>Получить перечень и общее число пилотов, пpошедших медосмотp " +
                        "либо не пpошедших его в указанный год, по половому пpизнаку, " +
                        "возpасту, pазмеpу заpаботной платы.</html>",

                "WITH SUITABLE_PILOTS AS " +
                        "(SELECT PILOT_ID, MEDICAL_CHECKUP_DATE, MEDICAL_CHECKUP_RESULT " +
                        "FROM PILOT_MEDICAL_CHECKUP_HISTORY " +
                        "WHERE MEDICAL_CHECKUP_DATE - TO_DATE('@year@/01/01', 'YYYY/MM/DD') @first@ 365.25 " +
                        "@cond@ MEDICAL_CHECKUP_DATE - TO_DATE('@year@/01/01', 'YYYY/MM/DD') @second@ 0) " +
                        "SELECT EMPLOYEE_ID, SURNAME, NAME, PATRONYMIC, SEX, MEDICAL_CHECKUP_RESULT, " +
                        "(CURRENT_DATE - BIRTH_DATE) / 365.25 AS AGE, SALARY " +
                        "FROM SUITABLE_PILOTS LEFT JOIN EMPLOYEE ON EMPLOYEE_ID = PILOT_ID " +
                        "WHERE ",

                new LinkedHashMap<>() {{
                    put("Год медосмотра", new String[]{"=", "!="});
                    put("Пол (Y: муж., N: жен.)", new String[]{"="});
                    put("Возраст", new String[]{"=", "<", ">"});
                    put("Размер заработной платы", new String[]{"<", ">", "="});
                }});
    }

    @Override
    public RequestResultPackage getRequestResultRows(ControllerManager controllerManager) throws SQLException {
        var answers = this.getAnswers();
        var selectedOptions = this.getSelectedOptions();
        var resultPackage = new RequestResultPackage();
        var noOption = MakeRequestButton.NO_OPTION;
        String preQuery = this.getRequestBlank();
        boolean previousExists = false;

        if (!noOption.equals(selectedOptions.get("Год медосмотра"))) {
            preQuery = preQuery.replaceAll("@year@", answers.get("Год медосмотра"));
            if ("=".equals(selectedOptions.get("Год медосмотра"))) {
                preQuery = preQuery.replaceAll("@first@", "<=")
                        .replaceAll("@second@", ">")
                        .replaceAll("@cond@", "AND");
            } else if ("!=".equals(selectedOptions.get("Год медосмотра"))) {
                preQuery = preQuery.replaceAll("@first@", ">")
                        .replaceAll("@second@", "<=")
                        .replaceAll("@cond@", "OR");
            }
        }

        StringBuilder stringBuilder = new StringBuilder(preQuery);
        if (!noOption.equals(selectedOptions.get("Пол (Y: муж., N: жен.)"))) {
            stringBuilder.append("SEX")
                    .append(" ")
                    .append(selectedOptions.get("Пол (Y: муж., N: жен.)"))
                    .append(" ")
                    .append("'")
                    .append(answers.get("Пол (Y: муж., N: жен.)"))
                    .append("'")
                    .append(" ");
            previousExists = true;
        }
        if (!noOption.equals(selectedOptions.get("Возраст"))) {
            stringBuilder.append((previousExists) ? "AND " : "");
            stringBuilder.append("(CURRENT_DATE - BIRTH_DATE) / 365.25")
                    .append(" ")
                    .append(selectedOptions.get("Возраст"))
                    .append(" ")
                    .append(answers.get("Возраст"))
                    .append(" ");
            previousExists = true;
        }
        if (!noOption.equals(selectedOptions.get("Размер заработной платы"))) {
            stringBuilder.append((previousExists) ? "AND " : "");
            stringBuilder.append("SALARY")
                    .append(" ")
                    .append(selectedOptions.get("Размер заработной платы"))
                    .append(" ")
                    .append(answers.get("Размер заработной платы"))
                    .append(" ");
        }

        AbstractRequestProvider.removeWhereIfNeed(stringBuilder);

        ResultSet resultSet = controllerManager.getProvider().getStringsQueryResultSet(stringBuilder.toString(), Collections.emptyList());
        List<String[]> allRows = new ArrayList<>();
        // SELECT EMPLOYEE_ID, SURNAME, NAME, PATRONYMIC, SEX, MEDICAL_CHECKUP_RESULT, (CURRENT_DATE - BIRTH_DATE) / 365.25 AS AGE, SALARY
        resultPackage.setColumnNames(new String[]{"ID сотрудника", "Фамилия", "Имя", "Отчество", "Пол", "Результат мед. осм.", "Возраст", "Зарплата"});
        while (resultSet.next()) {
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(resultSet.getInt(1)));
            row.add(resultSet.getString(2));
            row.add(resultSet.getString(3));
            row.add(resultSet.getString(4));
            row.add(("Y".equals(resultSet.getString(5))) ? "Муж." : "Жен.");
            row.add(("Y".equals(resultSet.getString(6))) ? "Пройден" : "Не пройден");
            row.add(String.valueOf(resultSet.getInt(7)));
            row.add(String.valueOf(resultSet.getFloat(8)));
            allRows.add(row.toArray(new String[0]));
        }
        resultPackage.setResultRows(allRows.toArray(new String[0][]));
        return resultPackage;
    }
}