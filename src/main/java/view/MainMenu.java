package view;

import controller.ControllerManager;
import view.listenedButtons.*;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {

    public MainMenu(ControllerManager controllerManager, JFrame parentDisplay) {

        this.setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;

        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        JPanel menuButtons = new JPanel(new GridBagLayout());

        JButton disconnectButton = new DisconnectButton(controllerManager, parentDisplay);
        menuButtons.add(disconnectButton, gridBagConstraints);
        menuButtons.add(new JLabel(" "), gridBagConstraints);

        JButton rowsCounterButton = new RowsCounterButton(controllerManager);
        menuButtons.add(rowsCounterButton, gridBagConstraints);
        menuButtons.add(new JLabel(" "), gridBagConstraints);

        JButton insertDataButton = new InsertDataButton(controllerManager);
        menuButtons.add(insertDataButton, gridBagConstraints);
        menuButtons.add(new JLabel(" "), gridBagConstraints);

        JButton manageTablesButton = new ManageTablesButton(controllerManager);
        menuButtons.add(manageTablesButton, gridBagConstraints);
        menuButtons.add(new JLabel(" "), gridBagConstraints);

        gridBagConstraints.weighty = 1;
        this.add(menuButtons, gridBagConstraints);
    }
}
