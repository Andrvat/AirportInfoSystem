package view.listenedButtons;

import controller.ControllerManager;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Objects;

public class RowsCounterButton extends JButton {
    public RowsCounterButton(ControllerManager controllerManager) {
        this.setText("Count rows");
        this.addActionListener(event -> {
            String tableName = JOptionPane.showInputDialog("Enter the table name");
            if (Objects.isNull(tableName)) {
                return;
            }
            if (tableName.equals("")) {
                JOptionPane.showMessageDialog(null, "Empty table name!");
                return;
            }
            try {
                int amount = controllerManager.getRowsNumberByName(tableName);
                JOptionPane.showMessageDialog(null, "Table <" + tableName + "> contains " + amount + " rows");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        });
    }
}
