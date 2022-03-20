package view;

import controller.ControllerManager;
import view.utilities.DisplaysManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.DataBufferUShort;

public class LoginDisplay extends JFrame {

    private final LoginMenu mainMenu;

    public LoginDisplay(ControllerManager controllerManager) {
        this.mainMenu = new LoginMenu(controllerManager, this);

        this.setSize(new Dimension(DisplaysManager.SCREEN_WIDTH, DisplaysManager.SCREEN_HEIGHT));
        this.setTitle(DisplaysManager.TITLE);
        this.setResizable(false);
        DisplaysManager.setCloseOperationScreenListener(this);

        Container contentPane = this.getContentPane();
        SpringLayout springLayout = new SpringLayout();
        contentPane.setLayout(springLayout);

        JLabel menuLabel = new JLabel() {{
            setText("LOGIN MENU");
            setFont(DisplaysManager.LABELS_DEFAULT_FONT);
        }};
        contentPane.add(menuLabel);
        contentPane.add(this.mainMenu);

        springLayout.putConstraint(SpringLayout.WEST, menuLabel, DisplaysManager.LAYOUT_SIDE_PAD, SpringLayout.WEST, contentPane);
        springLayout.putConstraint(SpringLayout.WEST, this.mainMenu, DisplaysManager.LAYOUT_SIDE_PAD, SpringLayout.WEST, contentPane);
        springLayout.putConstraint(SpringLayout.NORTH, menuLabel, DisplaysManager.LAYOUT_NORTH_PAD, SpringLayout.NORTH, contentPane);
        springLayout.putConstraint(SpringLayout.NORTH, this.mainMenu, DisplaysManager.LAYOUT_COMPONENTS_PAD, SpringLayout.NORTH, menuLabel);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
