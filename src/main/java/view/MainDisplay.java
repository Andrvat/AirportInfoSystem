package view;

import controller.ControllerManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainDisplay extends JFrame {
    private static final String TITLE = "AIRPORT INFORMATION SYSTEM";

    private static final Font LABELS_DEFAULT_FONT = new Font("Veranda", Font.BOLD, 20);

    private static final int SCREEN_WIDTH = 1080;
    private static final int SCREEN_HEIGHT = 720;

    private static final int LAYOUT_SIDE_PAD = 465;
    private static final int LAYOUT_NORTH_PAD = 200;
    private static final int LAYOUT_COMPONENTS_PAD = 50;

    private final MainMenu mainMenu;

    public MainDisplay(ControllerManager controllerManager) {
        this.mainMenu = new MainMenu(controllerManager);

        this.setSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setTitle(TITLE);
        this.setResizable(false);
        this.setCloseOperationScreenListener();

        Container contentPane = this.getContentPane();
        SpringLayout springLayout = new SpringLayout();
        contentPane.setLayout(springLayout);

        JLabel menuLabel = new JLabel() {{
            setText("MAIN MENU");
            setFont(LABELS_DEFAULT_FONT);
        }};
        contentPane.add(menuLabel);
        contentPane.add(this.mainMenu);

        springLayout.putConstraint(SpringLayout.WEST, menuLabel, LAYOUT_SIDE_PAD, SpringLayout.WEST, contentPane);
        springLayout.putConstraint(SpringLayout.WEST, this.mainMenu, LAYOUT_SIDE_PAD - 10, SpringLayout.WEST, contentPane);
        springLayout.putConstraint(SpringLayout.NORTH, menuLabel, LAYOUT_NORTH_PAD, SpringLayout.NORTH, contentPane);
        springLayout.putConstraint(SpringLayout.NORTH, this.mainMenu, LAYOUT_COMPONENTS_PAD, SpringLayout.NORTH, menuLabel);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void setCloseOperationScreenListener() {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        JFrame thisFrame = this;
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int chosenOption =
                        JOptionPane.showConfirmDialog(thisFrame,
                                "Are you sure?",
                                "Exit",
                                JOptionPane.YES_NO_OPTION);
                if (isYesChosen(chosenOption)) {
                    System.exit(0);
                }
            }

            private boolean isYesChosen(int index) {
                return index == 0;
            }
        });
    }
}
