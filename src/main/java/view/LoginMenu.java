package view;

import controller.ControllerManager;
import view.listenedButtons.DisconnectButton;
import view.listenedButtons.InsertDataButton;
import view.listenedButtons.LoginFormButton;
import view.listenedButtons.RowsCounterButton;

import javax.swing.*;
import java.awt.*;

public class LoginMenu extends JPanel {

    private final ControllerManager controllerManager;
    private final JFrame parentDisplay;

    public LoginMenu(ControllerManager controllerManager, JFrame parentDisplay) {
        this.parentDisplay = parentDisplay;
        this.controllerManager = controllerManager;

        this.setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;

        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        JPanel menuButtons = new JPanel(new GridBagLayout());

        JButton loginFormButton = new LoginFormButton(this.controllerManager, parentDisplay);
        menuButtons.add(loginFormButton, gridBagConstraints);
        menuButtons.add(new JLabel(" "), gridBagConstraints);

        gridBagConstraints.weighty = 1;
        this.add(menuButtons, gridBagConstraints);
    }
}
