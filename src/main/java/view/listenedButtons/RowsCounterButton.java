package view.listenedButtons;

import controller.ControllerManager;
import view.utilities.ChooseOptionViewer;

import javax.swing.*;

public class RowsCounterButton extends JButton {
    public RowsCounterButton(ControllerManager controllerManager) {
        this.setText("Count rows");
        this.addActionListener(event -> {
            ChooseOptionViewer tableViewer = new ChooseOptionViewer(controllerManager.getTableNames(), "Choose the table");
            int result = tableViewer.showView();
            if (result == JOptionPane.OK_OPTION) {
                try {
                    String selectedTableName = (String) tableViewer.getComboBox().getSelectedItem();
                    int amount = controllerManager.getTableRowsNumber(selectedTableName);
                    JOptionPane.showMessageDialog(null, "Table <" + selectedTableName + "> contains " + amount + " rows");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        });
    }
}
