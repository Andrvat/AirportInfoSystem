package view.listenedButtons;

import controller.ControllerManager;
import view.utilities.ChooseTableViewer;

import javax.swing.*;
import java.awt.*;

public class RowsCounterButton extends JButton {
    public RowsCounterButton(ControllerManager controllerManager) {
        this.setText("Count rows");
        this.addActionListener(event -> {
            ChooseTableViewer tableViewer = new ChooseTableViewer(controllerManager.getTableNames());
            int result = tableViewer.showChoosingTableView();
            if (result == JOptionPane.OK_OPTION) {
                try {
                    String selectedTableName = (String) tableViewer.getComboBox().getSelectedItem();
                    int amount = controllerManager.getRowsNumberByName(selectedTableName);
                    JOptionPane.showMessageDialog(null, "Table <" + selectedTableName + "> contains " + amount + " rows");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        });
    }
}
