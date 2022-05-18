package view.listenedButtons;

import controller.ControllerManager;
import forms.AbstractRequestProvider;
import forms.FormPackage;
import org.apache.commons.lang3.ArrayUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

public class MakeRequestButton extends JButton {

    public static final String NO_OPTION = "Не выбрано";

    private JDialog parentDialog;

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
                JComboBox<String> comboBox = new JComboBox<>(ArrayUtils.add(options, 0, NO_OPTION));
                comboBox.setPreferredSize(new Dimension(200, 20));
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

            Map<String, String> answers = new LinkedHashMap<>();
            for (var i = 0; i < formPackage.getLabelTexts().size(); ++i) {
                answers.put(formPackage.getLabelTexts().get(i), textFieldAnswers.get(i).getText());
            }
            Map<String, String> selectedOptions = new LinkedHashMap<>();
            for (var i = 0; i < formPackage.getLabelTexts().size(); ++i) {
                selectedOptions.put(formPackage.getLabelTexts().get(i), (String) optionComboBoxes.get(i).getSelectedItem());
            }

            try {
                provider.setSelectedOptions(selectedOptions);
                provider.setAnswers(answers);
                var resultPackage = provider.getRequestResultRows(controllerManager);

                JFrame tableDisplay = new JFrame("View all rows");
                JTable tableView = new JTable(resultPackage.getResultRows(), resultPackage.getColumnNames());
                JScrollPane scrollPane = new JScrollPane(tableView);
                tableView.setEnabled(false);
                tableDisplay.getContentPane().add(scrollPane);
                tableDisplay.setPreferredSize(new Dimension(600, 300));
                tableDisplay.pack();
                tableDisplay.setLocationRelativeTo(null);

                this.parentDialog.dispose();
                tableDisplay.setVisible(true);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        });
    }

    public void setParentDialog(JDialog parentDialog) {
        this.parentDialog = parentDialog;
    }
}
