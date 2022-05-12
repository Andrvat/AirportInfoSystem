package view.listenedButtons;

import controller.ControllerManager;
import model.ApplicationConstants;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class AddNewAccountButton extends JButton {
    public AddNewAccountButton(ControllerManager controllerManager) {
        this.setText("Add new account");
        this.addActionListener(event -> {
            JPanel panel = new JPanel(new BorderLayout(5, 5));
            panel.setPreferredSize(new Dimension(300, 80));

            JPanel labelPanel = new JPanel(new GridLayout(0, 1, 5, 5));
            labelPanel.add(new JLabel("User", SwingConstants.RIGHT));
            labelPanel.add(new JLabel("Password", SwingConstants.RIGHT));
            labelPanel.add(new JLabel("Confirm password", SwingConstants.RIGHT));
            labelPanel.add(new JLabel("Role", SwingConstants.RIGHT));
            panel.add(labelPanel, BorderLayout.WEST);

            JPanel controlPanel = new JPanel(new GridLayout(0, 1, 5, 5));
            JTextField user = new JTextField();
            controlPanel.add(user);
            JPasswordField password = new JPasswordField();
            controlPanel.add(password);
            JPasswordField confirmPassword = new JPasswordField();
            controlPanel.add(confirmPassword);
            JComboBox<String> roleComboBox = new JComboBox<>(ApplicationConstants.getRoleNames());
            controlPanel.add(roleComboBox);
            panel.add(controlPanel, BorderLayout.CENTER);

            int result = JOptionPane.showConfirmDialog(this, panel, "Registration", JOptionPane.OK_CANCEL_OPTION);
            String passwordValue = String.valueOf(password.getPassword());
            if (result != JOptionPane.OK_OPTION) {
                return;
            }
            if (!passwordValue.equals(String.valueOf(confirmPassword.getPassword()))) {
                JOptionPane.showMessageDialog(null, "Passwords don't match");
                return;
            }
            String resultMessage = "";
            try {
                controllerManager.registerNewAccount(user.getText(), passwordValue, (String) roleComboBox.getSelectedItem());
                resultMessage = "New account was registered";
            } catch (Exception exception) {
                resultMessage = exception.getMessage();
            } finally {
                JOptionPane.showMessageDialog(null, resultMessage);
            }
        });
    }
}
