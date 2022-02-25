package view;

import controller.ControllerManager;
import view.listenedButtons.InsertDataButton;
import view.listenedButtons.RowsCounterButton;

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

        JButton rowsCounterButton = new RowsCounterButton(this.controllerManager);
        menuButtons.add(rowsCounterButton, gridBagConstraints);
        menuButtons.add(new JLabel(" "), gridBagConstraints);

        JButton insertDataButton = new InsertDataButton(this.controllerManager);
        menuButtons.add(insertDataButton, gridBagConstraints);
        menuButtons.add(new JLabel(" "), gridBagConstraints);


        gridBagConstraints.weighty = 1;
        this.add(menuButtons, gridBagConstraints);
    }
}
