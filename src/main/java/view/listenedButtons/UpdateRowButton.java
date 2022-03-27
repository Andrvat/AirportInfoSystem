package view.listenedButtons;

import controller.ControllerManager;
import javassist.compiler.ast.Pair;
import view.utilities.ChooseTableViewer;
import view.utilities.TableColumnInfo;
import view.utilities.TableColumnRequestOption;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateRowButton extends JButton {
    public UpdateRowButton(ControllerManager controllerManager, String tableName,
                           Map<String, String> existingValues, Map.Entry<String, String> primaryKey, JFrame parentView) {
        this.setText("Update");
        this.addActionListener(event -> {
            List<TableColumnInfo> columnsInfos = controllerManager.getTableColumnInfos(
                    tableName, TableColumnRequestOption.INSERT);

            JPanel updateDataPanel = new JPanel(new GridLayout(0, 1));
            Map<JLabel, JComponent> inputForms = new HashMap<>();
            for (var columnInfo : columnsInfos) {
                String columnName = columnInfo.getName().replaceAll("_", " ");
                JComponent component;
                if ("boolean".equals(columnInfo.getTypeValue())) {
                    component = new JCheckBox("", Boolean.parseBoolean(existingValues.get(columnName)));
                } else {
                    component = new JTextField(existingValues.get(columnName));
                }
                inputForms.put(new JLabel(columnName), component);
            }
            for (var entry : inputForms.entrySet()) {
                updateDataPanel.add(entry.getKey());
                updateDataPanel.add(entry.getValue());
            }

            int result = JOptionPane.showConfirmDialog(null, updateDataPanel, this.getText(),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result != JOptionPane.OK_OPTION) {
                return;
            }

            parentView.setVisible(false);
            Map<String, String> enteredValues = new HashMap<>();
            enteredValues.put(primaryKey.getKey(), primaryKey.getValue());
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
                controllerManager.updateTableRecordById(tableName, enteredValues);
                resultMessage = "Data was successfully updated";
            } catch (Exception exception) {
                resultMessage = exception.getMessage();
            } finally {
                JOptionPane.showMessageDialog(null, resultMessage);
            }
        });
    }
}
