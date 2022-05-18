package forms;

import controller.ControllerManager;
import entities.AbstractComponent;

import java.text.Normalizer;
import java.util.List;
import java.util.Map;

public abstract class AbstractRequestProvider {

    private final String description;
    private Map<String, String> answers;
    private Map<String, String> selectedOptions;

    public AbstractRequestProvider(String description) {
        this.description = description;
    }
    public abstract void performRequest(ControllerManager controllerManager);

    public abstract FormPackage getPreparedFromPackage();

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
