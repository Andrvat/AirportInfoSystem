package view.listenedButtons;

import controller.ControllerManager;
import model.ApplicationConstants;

import javax.swing.*;
import java.awt.*;

public class BuyTicketButton extends JButton {
    public BuyTicketButton(ControllerManager controllerManager) {
        this.setText("Book/Buy ticket");
        this.addActionListener(event -> {
            JPanel panel = new JPanel(new BorderLayout(5, 5));
            panel.setPreferredSize(new Dimension(300, 100));
            JPanel labelPanel = new JPanel(new GridLayout(0, 1, 3, 3));
            labelPanel.add(new JLabel("Ваш ID пассажира", SwingConstants.RIGHT));
            labelPanel.add(new JLabel("Выбранный ID вылета", SwingConstants.RIGHT));
            labelPanel.add(new JLabel("Выбранный номер места", SwingConstants.RIGHT));
            labelPanel.add(new JLabel("Купить билет сразу?", SwingConstants.RIGHT));
            panel.add(labelPanel, BorderLayout.WEST);

            JPanel controlPanel = new JPanel(new GridLayout(0, 1, 3, 3));
            JTextField passengerTextField = new JTextField();
            controlPanel.add(passengerTextField);
            JTextField departureTextField = new JTextField();
            controlPanel.add(departureTextField);
            JTextField seatTextField = new JTextField();
            controlPanel.add(seatTextField);
            JCheckBox buyImmediatelyCheckBox = new JCheckBox();
            controlPanel.add(buyImmediatelyCheckBox);
            panel.add(controlPanel, BorderLayout.CENTER);

            int result = JOptionPane.showConfirmDialog(this, panel, "Buying/Booking tickets", JOptionPane.OK_CANCEL_OPTION);
            if (result != JOptionPane.OK_OPTION) {
                return;
            }

        });
    }
}
