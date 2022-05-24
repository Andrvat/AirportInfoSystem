package view.listenedButtons;

import annotations.DbTable;
import controller.ControllerManager;
import entities.Employee;

import javax.swing.*;

public class ManageEmployeesButton extends JButton {
    public ManageEmployeesButton(ControllerManager controllerManager) {
        this.setText("Manage employees");
        this.addActionListener(event -> {
            ManageTablesButton.manageTable(controllerManager, Employee.class.getAnnotation(DbTable.class).name());
        });
    }
}
