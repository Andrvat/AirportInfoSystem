package view.listenedButtons;

import controller.ControllerManager;
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
                JComponent component;
                if ("boolean".equals(columnInfo.getTypeValue())) {
                    component = new JCheckBox("");
                } else {
                    component = new JTextField("");
                }
                inputForms.put(new JLabel(columnInfo.getName().replaceAll("_", " ")), component);
            }
            for (var entry : inputForms.entrySet()) {
                insertDataPanel.add(entry.getKey());
                insertDataPanel.add(entry.getValue());
            }

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
                    if ("".equals(input)) {
                        JOptionPane.showMessageDialog(null, "Empty field " + fieldName);
                        return;
                    }
                } else if (entry.getValue() instanceof JCheckBox checkBox) {
                    input = (checkBox.isSelected()) ? "Y" : "N";
                }
                enteredValues.put(fieldName, input);
            }

            String resultMessage = "";
            try {
                controllerManager.insertValueIntoTable(selectedTableName, enteredValues);
                resultMessage = "Data was successfully inserted";
            } catch (Exception exception) {
                resultMessage = exception.getMessage();
            } finally {
                JOptionPane.showMessageDialog(null, resultMessage);
            }
        });
    }
}
