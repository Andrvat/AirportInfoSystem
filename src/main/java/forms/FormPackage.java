package forms;

import java.util.ArrayList;
import java.util.List;

public class FormPackage {

    private String description;
    private List<String> labelTexts;
    private List<String[]> options;
    private List<String> answers;
    private List<String> elementTypeNames;

    public List<String> getLabelTexts() {
        return labelTexts;
    }

    public void setLabelTexts(String[] labelTexts) {
        this.labelTexts = new ArrayList<>(List.of(labelTexts));
    }

    public List<String[]> getOptions() {
        return options;
    }

    public void setOptions(List<String[]> options) {
        this.options = options;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public void setLabelTexts(List<String> labelTexts) {
        this.labelTexts = labelTexts;
    }

    public List<String> getElementTypeNames() {
        return elementTypeNames;
    }

    public void setElementTypeNames(String[] elementTypeNames) {
        this.elementTypeNames = new ArrayList<>(List.of(elementTypeNames));
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setElementTypeNames(List<String> elementTypeNames) {
        this.elementTypeNames = elementTypeNames;
    }
}
