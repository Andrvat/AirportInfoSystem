package view.listenedButtons;

import controller.ControllerManager;
import dbConnection.OracleDbProvider;
import sql.SchemaVerifier;
import view.MainDisplay;

import javax.swing.*;
import java.awt.*;

public class LoginFormButton extends JButton {
    public LoginFormButton(ControllerManager controllerManager, JFrame display) {
        this.setText("Login database");
        this.addActionListener(event -> {

            JPanel panel = new JPanel(new BorderLayout(5, 5));
            panel.setPreferredSize(new Dimension(300, 80));

            JPanel label = new JPanel(new GridLayout(0, 1, 3, 3));
            label.add(new JLabel("Database url", SwingConstants.RIGHT));
            label.add(new JLabel("User", SwingConstants.RIGHT));
            label.add(new JLabel("Password", SwingConstants.RIGHT));
            panel.add(label, BorderLayout.WEST);

            JPanel controlPanel = new JPanel(new GridLayout(0, 1, 3, 3));
            JTextField dbUrl = new JTextField();
            controlPanel.add(dbUrl);
            JTextField username = new JTextField();
            controlPanel.add(username);
            JPasswordField password = new JPasswordField();
            controlPanel.add(password);
            panel.add(controlPanel, BorderLayout.CENTER);

            int result = JOptionPane.showConfirmDialog(this, panel, "Login form", JOptionPane.OK_CANCEL_OPTION);
            if (result != JOptionPane.OK_OPTION) {
                return;
            }
            try {
                OracleDbProvider provider = controllerManager.getProvider();
                provider.setDbUrl(dbUrl.getText());
                provider.setUserLogin(username.getText());
                provider.setUserPassword(String.valueOf(password.getPassword()));
                provider.registerDbProvider();

                SchemaVerifier verifier = new SchemaVerifier(controllerManager);
                verifier.verifySchema();

                display.setVisible(false);
                new MainDisplay(controllerManager);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        });
    }
}
