package view.listenedButtons;

import controller.ControllerManager;
import view.utilities.ChooseTableViewer;
import view.utilities.DisplaysManager;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewAllRowsButton extends JButton {
    public ViewAllRowsButton(ControllerManager controllerManager) {
        this.setText("View all rows");
        this.addActionListener(event -> {
            ChooseTableViewer tableViewer = new ChooseTableViewer(controllerManager.getTableNames());
            int choosingResult = tableViewer.showChoosingTableView();
            if (choosingResult != JOptionPane.OK_OPTION) {
                return;
            }
            String selectedTableName = (String) tableViewer.getComboBox().getSelectedItem();
            try {
                String[] columnNames = controllerManager.getTableColumnNames(selectedTableName).toArray(new String[0]);
                String[][] allRows = controllerManager.getAllRowsValues(selectedTableName);

                JFrame tableDisplay = new JFrame("View all rows");

                JTable tableView = new JTable(allRows, columnNames);
                JScrollPane scrollPane = new JScrollPane(tableView);

                tableDisplay.getContentPane().add(scrollPane);
                tableDisplay.setPreferredSize(new Dimension(600, 300));
                tableDisplay.pack();
                tableDisplay.setLocationRelativeTo(null);
                tableDisplay.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        });
    }
}
