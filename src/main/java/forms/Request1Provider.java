package forms;

import controller.ControllerManager;
import entities.Employee;
import view.listenedButtons.MakeRequestButton;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Request1Provider extends AbstractRequestProvider {
    private static final String DESCRIPTION = "<html>Получить список и общее число всех pаботников аэpопоpта, " +
            "начальников отделов, pаботников указанного отдела, по стажу pаботы в аэpопоpту, " +
            "половому пpизнаку, возpасту, пpизнаку наличия и количеству детей, " +
            "по pазмеpу заpаботной платы</html>";

    private static final String REQUEST_BLANK =
            "SELECT EMPLOYEE_ID, SURNAME, NAME, PATRONYMIC, SPECIALTY_NAME, CREW_ID, DEPARTMENT_NAME " +
                    "FROM EMPLOYEE " +
                    "LEFT JOIN SPECIALIZATION USING (SPECIALTY_ID) " +
                    "LEFT OUTER JOIN DEPARTMENT USING (DEPARTMENT_ID) " +
                    "WHERE ";

    private final Map<String, String[]> options = new LinkedHashMap<>() {{
        put("Специальность", new String[]{"="});
        put("Отдел", new String[]{"="});
        put("Стаж работы", new String[]{"<", ">", "="});
        put("Возраст", new String[]{"<", ">", "="});
        put("Пол (Y: муж., N: жен.)", new String[]{"="});
        put("Наличие детей (Y: да, N: нет)", new String[]{"="});
        put("Количество детей", new String[]{"<", ">", "="});
        put("Размер заработной платы", new String[]{"<", ">", "="});
    }};

    public Request1Provider() {
        super(DESCRIPTION);
    }

    @Override
    public void performRequest(ControllerManager controllerManager) {
        var answers = this.getAnswers();
        var selectedOptions = this.getSelectedOptions();
        var noOption = MakeRequestButton.NO_OPTION;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(REQUEST_BLANK);
        boolean previousExists = false;

        if (!noOption.equals(selectedOptions.get("Специальность"))) {
            stringBuilder.append("SPECIALTY_NAME")
                    .append(" ")
                    .append(selectedOptions.get("Специальность"))
                    .append(" ")
                    .append("'")
                    .append(answers.get("Специальность"))
                    .append("'")
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
        if (!noOption.equals(selectedOptions.get("Стаж работы"))) {
            stringBuilder.append((previousExists) ? "AND " : "");
            stringBuilder.append("WORK_EXPERIENCE")
                    .append(" ")
                    .append(selectedOptions.get("Стаж работы"))
                    .append(" ")
                    .append(answers.get("Стаж работы"))
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
        if (!noOption.equals(selectedOptions.get("Пол (Y: муж., N: жен.)"))) {
            stringBuilder.append((previousExists) ? "AND " : "");
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
        if (!noOption.equals(selectedOptions.get("Наличие детей (Y: да, N: нет)"))) {
            stringBuilder.append((previousExists) ? "AND " : "");
            stringBuilder.append("CHILDREN_NUMBER")
                    .append(" ");
            if ("Y".equals(answers.get("Наличие детей (Y: да, N: нет)"))) {
                stringBuilder.append("> 0");
            } else if ("N".equals(answers.get("Наличие детей (Y: да, N: нет)"))) {
                stringBuilder.append("= 0");
            }
            stringBuilder.append(" ");
            previousExists = true;
        }
        if (!noOption.equals(selectedOptions.get("Количество детей"))) {
            stringBuilder.append((previousExists) ? "AND " : "");
            stringBuilder.append("CHILDREN_NUMBER")
                    .append(" ")
                    .append(selectedOptions.get("Количество детей"))
                    .append(" ")
                    .append(answers.get("Количество детей"))
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
        System.out.println(stringBuilder);
    }

    @Override
    public FormPackage getPreparedFromPackage() {
        FormPackage formPackage = new FormPackage();
        formPackage.setDescription(this.getDescription());
        formPackage.setLabelTexts(this.options.keySet().toArray(new String[0]));
        formPackage.setOptions(new ArrayList<>(this.options.values()));
        return formPackage;
    }
}
