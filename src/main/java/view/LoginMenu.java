package view;

import controller.ControllerManager;
import view.listenedButtons.LoginFormButton;

import javax.swing.*;
import java.awt.*;

public class LoginMenu extends JPanel {

    public LoginMenu(ControllerManager controllerManager, JFrame parentDisplay) {

        this.setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;

        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        JPanel menuButtons = new JPanel(new GridBagLayout());

        JButton loginFormButton = new LoginFormButton(controllerManager, parentDisplay);
        menuButtons.add(loginFormButton, gridBagConstraints);
        menuButtons.add(new JLabel(" "), gridBagConstraints);

        gridBagConstraints.weighty = 1;
        this.add(menuButtons, gridBagConstraints);
    }
}
