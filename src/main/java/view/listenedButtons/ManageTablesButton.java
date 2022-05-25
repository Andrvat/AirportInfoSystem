package view.listenedButtons;

import controller.ControllerManager;
import forms.RequestResultPackage;
import view.TableObjectViewForm;
import view.utilities.ChooseOptionViewer;
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
            ChooseOptionViewer tableViewer = new ChooseOptionViewer(controllerManager.getTableNames(), "Choose the table");
            int choosingResult = tableViewer.showView();
            if (choosingResult != JOptionPane.OK_OPTION) {
                return;
            }
            try {
                var selectedTableName = (String) tableViewer.getComboBox().getSelectedItem();
                var resultPackage = ManageTablesButton.getResultPackageByName(controllerManager, selectedTableName);
                ManageTablesButton.manageTable(controllerManager, resultPackage);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        });
    }

    public static RequestResultPackage getResultPackageByName(ControllerManager controllerManager,
                                                              String tableName) throws Exception {
        List<TableColumnInfo> columnInfos = controllerManager.getTableColumnInfos(
                tableName, TableColumnRequestOption.VIEW);
        List<String> columnNamesList = new ArrayList<>();
        for (TableColumnInfo info : columnInfos) {
            columnNamesList.add(info.getName().replaceAll("_", " "));
        }
        RequestResultPackage resultPackage = new RequestResultPackage();
        resultPackage.setTableName(tableName);
        resultPackage.setColumnNames(columnNamesList.toArray(new String[0]));
        resultPackage.setResultRows(controllerManager.getAllRowsValues(tableName));
        return resultPackage;
    }

    public static void manageTable(ControllerManager controllerManager, RequestResultPackage resultPackage) {
        JFrame tableDisplay = new JFrame("View all rows");

        JTable tableView = new JTable(resultPackage.getResultRows(), resultPackage.getColumnNames());
        JScrollPane scrollPane = new JScrollPane(tableView);

        tableView.setEnabled(false);
        tableView.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = tableView.rowAtPoint(e.getPoint());
                    if (row >= 0) {
                        String[] objectValues = resultPackage.getResultRows()[row];
                        try {
                            new TableObjectViewForm(controllerManager, resultPackage.getTableName(), resultPackage.getColumnNames(), objectValues);
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
    }
}
