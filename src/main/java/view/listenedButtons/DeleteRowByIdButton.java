package view.listenedButtons;

import controller.ControllerManager;
import view.utilities.ChooseTableViewer;

import javax.swing.*;
import java.awt.*;

public class DeleteRowByIdButton extends JButton {
    public DeleteRowByIdButton(ControllerManager controllerManager) {
        this.setText("Delete row by id");
        this.addActionListener(event -> {
            ChooseTableViewer tableViewer = new ChooseTableViewer(controllerManager.getTableNames());
            int result = tableViewer.showChoosingTableView();

            if (result != JOptionPane.OK_OPTION) {
                return;
            }
            String selectedTableName = (String) tableViewer.getComboBox().getSelectedItem();

            JPanel panel = new JPanel(new BorderLayout(5, 5));
            panel.setPreferredSize(new Dimension(300, 20));

            JPanel label = new JPanel(new GridLayout(0, 1, 3, 3));
            label.add(new JLabel("ID = ", SwingConstants.RIGHT));
            panel.add(label, BorderLayout.WEST);

            JPanel controlPanel = new JPanel(new GridLayout(0, 1, 3, 3));
            JTextField dbId = new JTextField();
            controlPanel.add(dbId);

            panel.add(controlPanel, BorderLayout.CENTER);

            result = JOptionPane.showConfirmDialog(this, panel, this.getText(), JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String resultMessage = "";
                try {
                    controllerManager.deleteTableRowById(selectedTableName, dbId.getText());
                    resultMessage = "Row was successfully deleted";
                } catch (Exception exception) {
                    resultMessage = exception.getMessage();
                } finally {
                    JOptionPane.showMessageDialog(null, resultMessage);
                }
            }

        });
    }
}
