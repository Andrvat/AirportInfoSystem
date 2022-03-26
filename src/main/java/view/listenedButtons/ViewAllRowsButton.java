package view.listenedButtons;

import controller.ControllerManager;
import view.utilities.ChooseTableViewer;
import view.utilities.TableColumnInfo;
import view.utilities.TableColumnRequestOption;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
                List<TableColumnInfo> columnInfos = controllerManager.getTableColumnInfos(
                        selectedTableName, TableColumnRequestOption.VIEW);
                List<String> columnNamesList = new ArrayList<>();
                for (TableColumnInfo info : columnInfos) {
                    columnNamesList.add(info.getName().replaceAll("_", " "));
                }
                String[] columnNames = columnNamesList.toArray(new String[0]);
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
