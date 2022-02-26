package view.listenedButtons;

import controller.ControllerManager;
import view.utilities.ChooseTableViewer;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

public class InsertDataButton extends JButton {
    public InsertDataButton(ControllerManager controllerManager) {
        this.setText("Insert new values");
        this.addActionListener(event -> {
            ChooseTableViewer tableViewer = new ChooseTableViewer(controllerManager.getTableNames());
            int choosingResult = tableViewer.showChoosingTableView();
            if (choosingResult == JOptionPane.OK_OPTION) {
                String selectedTableName = (String) tableViewer.getComboBox().getSelectedItem();
                try {
                    assert selectedTableName != null;
                    List<AbstractMap.SimpleEntry<String, String>> columnsInfo = controllerManager.getColumnsInfoByName(selectedTableName);

                    JPanel insertDataPanel = new JPanel(new GridLayout(0, 1));
                    Map<JLabel, JTextField> inputForms = new HashMap<>();
                    for (AbstractMap.SimpleEntry<String, String> entry : columnsInfo) {
                        inputForms.put(new JLabel(entry.getKey().replaceAll("_", " ")), new JTextField(""));
                    }

                    for (Map.Entry<JLabel, JTextField> entry : inputForms.entrySet()) {
                        insertDataPanel.add(entry.getKey());
                        insertDataPanel.add(entry.getValue());
                    }

                    int result = JOptionPane.showConfirmDialog(null, insertDataPanel, this.getText(),
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (result == JOptionPane.OK_OPTION) {
                        Map<String, String> enteredData = new HashMap<>();
                        for (Map.Entry<JLabel, JTextField> entry : inputForms.entrySet()) {
                            String fieldName = entry.getKey().getText().replaceAll(" ", "_");
                            StringBuilder input = new StringBuilder(entry.getValue().getText());
                            if (input.toString().equals("")) {
                                JOptionPane.showMessageDialog(null, "Empty field " + fieldName);
                                return;
                            }
                            for (AbstractMap.SimpleEntry<String, String> columnInfo : columnsInfo) {
                                if (columnInfo.getKey().equals(fieldName) && columnInfo.getValue().contains("VARCHAR")) {
                                    input = new StringBuilder('\'' + input.toString() + '\'');
                                }
                                if (columnInfo.getKey().equals(fieldName) && columnInfo.getValue().contains("DATE")) {
                                    input = new StringBuilder("TO_DATE('" +
                                            input.toString()
                                                    .replaceAll("-", ".")
                                                    .replaceAll("/", ".")
                                                    .replaceAll("\\\\", "-") +
                                            "', 'DD/MM/YYYY')");
                                }
                            }
                            enteredData.put(fieldName, input.toString());
                        }

                        String resultMessage = "";
                        try {
                            controllerManager.insertValuesIntoTable(selectedTableName, enteredData);
                            resultMessage = "Data was successfully inserted";
                        } catch (SQLException exception) {
                            resultMessage = exception.getMessage();
                        } finally {
                            JOptionPane.showMessageDialog(null, resultMessage);
                        }
                    }

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        });
    }
}
