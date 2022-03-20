package view.utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DisplaysManager {
    public static final String TITLE = "AIRPORT INFORMATION SYSTEM";

    public static final Font LABELS_DEFAULT_FONT = new Font("Veranda", Font.BOLD, 20);

    public static final int SCREEN_WIDTH = 1080;
    public static final int SCREEN_HEIGHT = 720;

    public static final int LAYOUT_SIDE_PAD = 465;
    public static final int LAYOUT_NORTH_PAD = 200;
    public static final int LAYOUT_COMPONENTS_PAD = 50;

    public static void setCloseOperationScreenListener(JFrame display) {
        display.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        display.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(display,
                        "Are you sure?",
                        "Exit",
                        JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }

        });
    }
}
