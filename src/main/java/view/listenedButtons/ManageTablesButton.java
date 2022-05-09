package view.listenedButtons;

import controller.ControllerManager;
import view.TableObjectViewForm;
import view.utilities.ChooseTableViewer;
import view.utilities.TableColumnInfo;
import view.utilities.TableColumnRequestOption;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ManageTablesButton extends JButton {
    public ManageTablesButton(ControllerManager controllerManager) {
        this.setText("Manage tables");
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

                tableView.setEnabled(false);
                tableView.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getClickCount() == 2) {
                            int row = tableView.rowAtPoint(e.getPoint());
                            if (row >= 0) {
                                String[] objectValues = allRows[row];
                                try {
                                    new TableObjectViewForm(controllerManager, selectedTableName, columnNames, objectValues);
                                } catch (ClassNotFoundException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                        }
                    }
                });

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
