package view.listenedButtons;

import controller.ControllerManager;
import view.LoginDisplay;

import javax.swing.*;

public class DisconnectButton extends JButton {
    public DisconnectButton(ControllerManager controllerManager, JFrame display) {
        this.setText("Disconnect");
        this.addActionListener(event -> {
            int result = JOptionPane.showConfirmDialog(this,
                    "Are you sure?", "Disconnect", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                controllerManager.getProvider().close();

                display.setVisible(false);
                new LoginDisplay(controllerManager);
            }
        });
    }
}
