package forms;

import controller.ControllerManager;
import entities.AbstractComponent;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractRequestProvider {

    private final String description;

    private final Map<String, String[]> options;
    private final String requestBlank;
    private Map<String, String> answers;
    private Map<String, String> selectedOptions;

    public AbstractRequestProvider(String description, String requestBlank, Map<String, String[]> options) {
        this.description = description;
        this.requestBlank = requestBlank;
        this.options = options;
    }

    public Map<String, String[]> getOptions() {
        return options;
    }

    public String getRequestBlank() {
        return requestBlank;
    }

    public static RequestResultPackage makePackageWithRowsNumber(ControllerManager controllerManager, String requestQuery) throws SQLException {
        int rowsNumber = AbstractComponent.getRowsNumber(controllerManager.getProvider(), requestQuery);
        List<String[]> allRows = new ArrayList<>();
        allRows.add(new String[]{String.valueOf(rowsNumber)});
        var resultPackage = new RequestResultPackage();
        resultPackage.setColumnNames(new String[]{"Количество"});
        resultPackage.setResultRows(allRows.toArray(new String[0][]));
        return resultPackage;
    }

    public abstract RequestResultPackage getRequestResultRowsNumber(ControllerManager controllerManager) throws SQLException;

    public abstract RequestResultPackage getRequestResultRows(ControllerManager controllerManager) throws SQLException;

    public FormPackage getPreparedFromPackage() {
        FormPackage formPackage = new FormPackage();
        formPackage.setDescription(this.getDescription());
        formPackage.setLabelTexts(this.options.keySet().toArray(new String[0]));
        formPackage.setOptions(new ArrayList<>(this.options.values()));
        return formPackage;
    }

    public Map<String, String> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<String, String> answers) {
        this.answers = answers;
    }

    public Map<String, String> getSelectedOptions() {
        return selectedOptions;
    }

    public void setSelectedOptions(Map<String, String> selectedOptions) {
        this.selectedOptions = selectedOptions;
    }

    public String getDescription() {
        return description;
    }

    public static void removeWhereIfNeed(StringBuilder stringBuilder) {
        if (stringBuilder.toString().endsWith("WHERE ")) {
            stringBuilder.replace(stringBuilder.length() - "WHERE ".length(), stringBuilder.length(), "");
        }
    }
}
