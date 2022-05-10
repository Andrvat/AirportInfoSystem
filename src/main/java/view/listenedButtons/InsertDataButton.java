package view.listenedButtons;

import controller.ControllerManager;
import model.support.TimeCalendar;
import view.utilities.ChooseTableViewer;
import view.utilities.TableColumnInfo;
import view.utilities.TableColumnRequestOption;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class InsertDataButton extends JButton {
    public InsertDataButton(ControllerManager controllerManager) {
        this.setText("Insert new values");
        this.addActionListener(event -> {
            ChooseTableViewer tableViewer = new ChooseTableViewer(controllerManager.getTableNames());
            int choosingResult = tableViewer.showChoosingTableView();
            if (choosingResult != JOptionPane.OK_OPTION) {
                return;
            }
            String selectedTableName = (String) tableViewer.getComboBox().getSelectedItem();
            List<TableColumnInfo> columnsInfos = controllerManager.getTableColumnInfos(
                    selectedTableName, TableColumnRequestOption.INSERT);

            JPanel insertDataPanel = new JPanel(new GridLayout(0, 1));

            Map<JLabel, JComponent> inputForms = new HashMap<>();
            for (var columnInfo : columnsInfos) {
                String columnName = columnInfo.getName().replaceAll("_", " ");
                if ("boolean".equals(columnInfo.getTypeValue())) {
                    inputForms.put(new JLabel(columnName), new JCheckBox(""));
                } else {
                    inputForms.put(new JLabel(columnName), new JTextField(""));
                }
            }
            for (var entry : inputForms.entrySet()) {
                insertDataPanel.add(entry.getKey());
                insertDataPanel.add(entry.getValue());

            }

            JLabel caution = new JLabel("<html><center>" +
                    "When filling in values of the \"DATE\" type, use the format" +
                    "<br><br>" +
                    TimeCalendar.JAVA_DATE_FORMAT);
            caution.setHorizontalAlignment(SwingConstants.CENTER);
            JOptionPane.showMessageDialog(null, caution, "Caution", JOptionPane.INFORMATION_MESSAGE);
            int result = JOptionPane.showConfirmDialog(null, insertDataPanel, this.getText(),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result != JOptionPane.OK_OPTION) {
                return;
            }

            Map<String, String> enteredValues = new HashMap<>();
            for (var entry : inputForms.entrySet()) {
                String fieldName = entry.getKey().getText().replaceAll(" ", "_");
                String input = null;
                if (entry.getValue() instanceof JTextField textField) {
                    input = textField.getText();
                } else if (entry.getValue() instanceof JCheckBox checkBox) {
                    input = (checkBox.isSelected()) ? "Y" : "N";
                }
                enteredValues.put(fieldName, input);
            }

            String resultMessage = "";
            try {
                controllerManager.insertValuesInto(selectedTableName, enteredValues);
                resultMessage = "Data was successfully inserted";
            } catch (Exception exception) {
                resultMessage = exception.getMessage();
            } finally {
                JOptionPane.showMessageDialog(null, resultMessage);
            }
        });
    }
}
