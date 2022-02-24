package view;

import controller.ControllerManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;
import java.util.Objects;

public class MainMenu extends JPanel {

    private final ControllerManager controllerManager;

    public MainMenu(ControllerManager controllerManager) {
        this.controllerManager = controllerManager;

        this.setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;

        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        JPanel menuButtons = new JPanel(new GridBagLayout());

        JButton counterButton = new JButton("Count rows");
        menuButtons.add(counterButton, gridBagConstraints);
        counterButton.addActionListener(event -> {
            String tableName = JOptionPane.showInputDialog("Enter the table name");
            if (Objects.isNull(tableName) || tableName.equals("")) {
                JOptionPane.showMessageDialog(null, "Empty table name!");
                return;
            }
            try {
                int amount = this.controllerManager.getRowsNumberByName(tableName);
                JOptionPane.showMessageDialog(null, "Table <" + tableName + "> contains " + amount + " rows");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        });

        gridBagConstraints.weighty = 1;
        this.add(menuButtons, gridBagConstraints);
    }
}
