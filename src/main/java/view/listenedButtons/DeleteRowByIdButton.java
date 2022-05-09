package view.listenedButtons;

import controller.ControllerManager;

import javax.swing.*;
import java.util.Map;

public class DeleteRowByIdButton extends JButton {
    public DeleteRowByIdButton(ControllerManager controllerManager, String tableName, Map<String, String> primaryKey, JFrame parentView) {
        this.setText("Delete");
        this.addActionListener(event -> {
            int result = JOptionPane.showConfirmDialog(this, "Are you sure?",
                    this.getText() + " record", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                parentView.setVisible(false);
                String resultMessage = "";
                try {
                    controllerManager.deleteTableRowById(tableName, primaryKey);
                    resultMessage = "Record was successfully deleted";
                } catch (Exception exception) {
                    resultMessage = exception.getMessage();
                } finally {
                    JOptionPane.showMessageDialog(null, resultMessage);
                }
            }
        });
    }
}
