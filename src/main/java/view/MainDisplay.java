package view;

import controller.ControllerManager;
import view.utilities.DisplaysManager;

import javax.swing.*;
import java.awt.*;

public class MainDisplay extends JFrame {

    public MainDisplay(ControllerManager controllerManager) {
        MainMenu mainMenu = new MainMenu(controllerManager, this);

        this.setSize(new Dimension(DisplaysManager.SCREEN_WIDTH, DisplaysManager.SCREEN_HEIGHT));
        this.setTitle(DisplaysManager.TITLE);
        this.setResizable(false);
        DisplaysManager.setCloseOperationScreenListener(this);

        Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridBagLayout());

        contentPane.add(mainMenu);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
