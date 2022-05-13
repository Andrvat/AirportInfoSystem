package view.listenedButtons;

import controller.ControllerManager;
import dbConnection.OracleDbProvider;
import model.ApplicationConstants;
import sql.DataInserter;
import sql.SchemaVerifier;
import view.MainDisplay;

import javax.swing.*;
import java.awt.*;

public class LoginFormButton extends JButton {
    public LoginFormButton(ControllerManager controllerManager, JFrame display) {
        this.setText("Connect to database");
        this.addActionListener(event -> {

            JPanel panel = new JPanel(new BorderLayout(5, 5));
            panel.setPreferredSize(new Dimension(300, 80));

            JPanel labelPanel = new JPanel(new GridLayout(0, 1, 3, 3));
            labelPanel.add(new JLabel("Database url", SwingConstants.RIGHT));
            labelPanel.add(new JLabel("User", SwingConstants.RIGHT));
            labelPanel.add(new JLabel("Password", SwingConstants.RIGHT));
            panel.add(labelPanel, BorderLayout.WEST);

            JPanel controlPanel = new JPanel(new GridLayout(0, 1, 3, 3));
            JTextField dbUrl = new JTextField();
            controlPanel.add(dbUrl);
            JTextField username = new JTextField();
            controlPanel.add(username);
            JPasswordField password = new JPasswordField();
            controlPanel.add(password);
            panel.add(controlPanel, BorderLayout.CENTER);

            int result = JOptionPane.showConfirmDialog(this, panel, "Connection", JOptionPane.OK_CANCEL_OPTION);
            if (result != JOptionPane.OK_OPTION) {
                return;
            }
            try {
                JPanel rolePanel = new JPanel(new BorderLayout(5, 5));
                rolePanel.setPreferredSize(new Dimension(300, 100));
                JPanel roleLabelPanel = new JPanel(new GridLayout(0, 1, 3, 3));
                roleLabelPanel.add(new JLabel("Role", SwingConstants.RIGHT));
                roleLabelPanel.add(new JLabel("Account login", SwingConstants.RIGHT));
                roleLabelPanel.add(new JLabel("Account password", SwingConstants.RIGHT));
                roleLabelPanel.add(new JLabel("Add test data?", SwingConstants.RIGHT));
                rolePanel.add(roleLabelPanel, BorderLayout.WEST);

                JPanel roleControlPanel = new JPanel(new GridLayout(0, 1, 3, 3));
                JComboBox<String> roleComboBox = new JComboBox<>(ApplicationConstants.getRoleNames());
                roleControlPanel.add(roleComboBox);
                JTextField roleUsername = new JTextField();
                roleControlPanel.add(roleUsername);
                JPasswordField rolePassword = new JPasswordField();
                roleControlPanel.add(rolePassword);
                JCheckBox testDataCheckBox = new JCheckBox();
                roleControlPanel.add(testDataCheckBox);
                rolePanel.add(roleControlPanel, BorderLayout.CENTER);

                result = JOptionPane.showConfirmDialog(this, rolePanel, "Login via role", JOptionPane.OK_CANCEL_OPTION);
                if (result != JOptionPane.OK_OPTION) {
                    return;
                }

                OracleDbProvider provider = controllerManager.getProvider();
                provider.setDbUrl(dbUrl.getText());
                provider.setUserLogin(username.getText());
                provider.setUserPassword(String.valueOf(password.getPassword()));
                provider.setRole((String) roleComboBox.getSelectedItem());
                provider.registerDbProvider();

                SchemaVerifier verifier = new SchemaVerifier(controllerManager,
                        roleUsername.getText(),
                        String.valueOf(rolePassword.getPassword()));
                verifier.verifyRoles();
                verifier.verifyAccount();
                verifier.verifySchema();
                if (testDataCheckBox.isSelected()) {
                    DataInserter inserter = new DataInserter(controllerManager);
                    inserter.insertTestData("src/main/resources/sql/tables/inserts");
                }

                display.setVisible(false);
                new MainDisplay(controllerManager);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        });
    }
}
