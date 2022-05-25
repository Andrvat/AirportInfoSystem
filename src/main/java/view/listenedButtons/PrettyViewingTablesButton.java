package view.listenedButtons;

import controller.ControllerManager;
import view.utilities.ChooseOptionViewer;

import javax.swing.*;

public class PrettyViewingTablesButton extends JButton {
    public PrettyViewingTablesButton(ControllerManager controllerManager) {
        this.setText("Pretty view table");
        this.addActionListener(event -> {
            ChooseOptionViewer tableViewer = new ChooseOptionViewer(controllerManager.getPrettyViewingTableNames(),
                    "Choose the table");
            int choosingResult = tableViewer.showView();
            if (choosingResult != JOptionPane.OK_OPTION) {
                return;
            }
            try {
                var selectedTableName = (String) tableViewer.getComboBox().getSelectedItem();
                var resultPackage = controllerManager.getPrettyViewingRowsValues(selectedTableName);
                ManageTablesButton.manageTable(controllerManager, resultPackage, ManageTablesButton.ManageType.PRETTY_VIEW);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        });
    }
}
