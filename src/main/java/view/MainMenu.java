package view;

import controller.ControllerManager;
import view.listenedButtons.*;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {

    private final ControllerManager controllerManager;
    private final JFrame parentDisplay;

    public MainMenu(ControllerManager controllerManager, JFrame parentDisplay) {
        this.parentDisplay = parentDisplay;
        this.controllerManager = controllerManager;

        this.setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;

        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        JPanel menuButtons = new JPanel(new GridBagLayout());

        JButton disconnectButton = new DisconnectButton(this.controllerManager, parentDisplay);
        menuButtons.add(disconnectButton, gridBagConstraints);
        menuButtons.add(new JLabel(" "), gridBagConstraints);

        JButton rowsCounterButton = new RowsCounterButton(this.controllerManager);
        menuButtons.add(rowsCounterButton, gridBagConstraints);
        menuButtons.add(new JLabel(" "), gridBagConstraints);

        JButton insertDataButton = new InsertDataButton(this.controllerManager);
        menuButtons.add(insertDataButton, gridBagConstraints);
        menuButtons.add(new JLabel(" "), gridBagConstraints);

        JButton viewAllRowsButton = new ViewAllRowsButton(this.controllerManager);
        menuButtons.add(viewAllRowsButton, gridBagConstraints);
        menuButtons.add(new JLabel(" "), gridBagConstraints);

        gridBagConstraints.weighty = 1;
        this.add(menuButtons, gridBagConstraints);
    }
}
