package view.listenedButtons;

import controller.ControllerManager;

import javax.swing.*;
import java.awt.*;

public class RowsCounterButton extends JButton {
    public RowsCounterButton(ControllerManager controllerManager) {
        this.setText("Count rows");
        this.addActionListener(event -> {
            String[] tableNames = controllerManager.getTableNames();
            JComboBox<String> comboBox = new JComboBox<>(tableNames);
            JPanel comboPanel = new JPanel(new GridLayout(0, 1));
            comboPanel.add(comboBox);
            int result = JOptionPane.showConfirmDialog(null, comboPanel, "Choose the table",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                try {
                    String selectedTableName = (String) comboBox.getSelectedItem();
                    int amount = controllerManager.getRowsNumberByName(selectedTableName);
                    JOptionPane.showMessageDialog(null, "Table <" + selectedTableName + "> contains " + amount + " rows");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        });
    }
}
