package forms;

import controller.ControllerManager;

import java.sql.SQLException;
import java.util.ArrayList;
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
}
