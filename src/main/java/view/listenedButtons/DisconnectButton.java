package view.listenedButtons;

import controller.ControllerManager;

import javax.swing.*;

public class DisconnectButton extends JButton {
    public DisconnectButton(ControllerManager controllerManager) {
        this.setText("Disconnect");
        this.addActionListener(event -> {
            int result = JOptionPane.showConfirmDialog(this,
                    "Are you sure?", "Disconnect", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                controllerManager.getProvider().close();
            }
        });
    }
}
