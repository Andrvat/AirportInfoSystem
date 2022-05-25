package view.listenedButtons;

import annotations.DbTable;
import controller.ControllerManager;
import entities.Employee;

import javax.swing.*;

public class ManageEmployeesButton extends JButton {
    public ManageEmployeesButton(ControllerManager controllerManager) {
        this.setText("Manage employees");
        this.addActionListener(event -> {
            try {
                var resultPackage = ManageTablesButton.getResultPackageByName(controllerManager,
                        Employee.class.getAnnotation(DbTable.class).name());
                ManageTablesButton.manageTable(controllerManager, resultPackage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
