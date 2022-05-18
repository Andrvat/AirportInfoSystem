package forms;

import controller.ControllerManager;
import entities.AbstractComponent;

import java.text.Normalizer;
import java.util.List;

public abstract class AbstractRequestProvider {

    private final String description;
    private List<String> answers;
    private List<String> selectedOptions;

    public AbstractRequestProvider(String description) {
        this.description = description;
    }
    public abstract void performRequest(ControllerManager controllerManager);

    public abstract FormPackage getPreparedFromPackage();

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public List<String> getSelectedOptions() {
        return selectedOptions;
    }

    public void setSelectedOptions(List<String> selectedOptions) {
        this.selectedOptions = selectedOptions;
    }

    public String getDescription() {
        return description;
    }
}
