package view.listenedButtons;

import controller.ControllerManager;
import model.ApplicationConstants;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class GenerateDeparturesButton extends JButton {
    public GenerateDeparturesButton(ControllerManager controllerManager) {
        this.setText("Generate departures");
        this.addActionListener(event -> {
            JPanel panel = new JPanel(new BorderLayout(5, 5));
            panel.setPreferredSize(new Dimension(500, 30));

            JPanel labelPanel = new JPanel(new GridLayout(0, 1, 5, 5));
            labelPanel.add(new JLabel("На сколько дней вы хотите сгенерировать расписание?", SwingConstants.RIGHT));
            panel.add(labelPanel, BorderLayout.WEST);

            JPanel controlPanel = new JPanel(new GridLayout(0, 1, 5, 5));
            JTextField daysTextField = new JTextField();
            controlPanel.add(daysTextField);
            panel.add(controlPanel, BorderLayout.CENTER);

            int result = JOptionPane.showConfirmDialog(this, panel, "Generation", JOptionPane.OK_CANCEL_OPTION);
            if (result != JOptionPane.OK_OPTION) {
                return;
            }
            String resultMessage = "";
            try {
                controllerManager.generateDepartures(Integer.valueOf(daysTextField.getText()));
                resultMessage = "Departures were generated";
            } catch (Exception exception) {
                resultMessage = exception.getMessage();
            } finally {
                JOptionPane.showMessageDialog(null, resultMessage);
            }
        });
    }
}
