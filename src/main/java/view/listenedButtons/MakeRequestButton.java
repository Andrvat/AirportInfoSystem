package view.listenedButtons;

import controller.ControllerManager;
import forms.AbstractRequestProvider;
import forms.FormPackage;
import model.ApplicationConstants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MakeRequestButton extends JButton {
    public MakeRequestButton(ControllerManager controllerManager, AbstractRequestProvider provider) {
        FormPackage formPackage = provider.getPreparedFromPackage();
        this.setText("Перейти к заполнению формы");
        this.addActionListener(event -> {
            JPanel panel = new JPanel(new BorderLayout(5, 5));
            panel.setPreferredSize(new Dimension(600, 30 * formPackage.getLabelTexts().size()));

            JPanel labelPanel = new JPanel(new GridLayout(0, 1, 5, 5));
            for (var label : formPackage.getLabelTexts()) {
                labelPanel.add(new JLabel(label, SwingConstants.RIGHT));
            }
            panel.add(labelPanel, BorderLayout.WEST);

            JPanel optionPanel = new JPanel(new GridLayout(0, 1, 5, 5));
            List<JComboBox<?>> optionComboBoxes = new ArrayList<>();
            for (var options : formPackage.getOptions()) {
                var copy = Arrays.copyOf(options, options.length + 1);
                copy[copy.length - 1] = "Не выбрано";
                JComboBox<String> comboBox = new JComboBox<>(copy);
                optionComboBoxes.add(comboBox);
                optionPanel.add(comboBox);
            }
            panel.add(optionPanel, BorderLayout.CENTER);

            JPanel controlPanel = new JPanel(new GridLayout(0, 1, 5, 5));
            List<JTextField> textFieldAnswers = new ArrayList<>();
            for (var i = 0; i < formPackage.getLabelTexts().size(); ++i) {
                JTextField textField = new JTextField();
                textField.setPreferredSize(new Dimension(300, 20));
                textFieldAnswers.add(textField);
                controlPanel.add(textField);
            }
            panel.add(controlPanel, BorderLayout.EAST);

            int result = JOptionPane.showConfirmDialog(this, panel, "Request", JOptionPane.OK_CANCEL_OPTION);
            if (result != JOptionPane.OK_OPTION) {
                return;
            }

            List<String> answers = new ArrayList<>();
            for (var text : textFieldAnswers) {
                answers.add(text.getText());
            }
            List<String> selectedOptions = new ArrayList<>();
            for (var comboBox : optionComboBoxes) {
                selectedOptions.add((String) comboBox.getSelectedItem());
            }

            provider.setSelectedOptions(selectedOptions);
            provider.setAnswers(answers);
            provider.performRequest(controllerManager);
        });
    }
}
