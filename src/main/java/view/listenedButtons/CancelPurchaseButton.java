package view.listenedButtons;

import controller.ControllerManager;
import converters.TicketsFormConverter;
import entities.Ticket;
import entities.TicketStatusHistory;
import model.ApplicationConstants;

import javax.swing.*;
import java.awt.*;

public class CancelPurchaseButton extends JButton {
    public CancelPurchaseButton(ControllerManager controllerManager) {
        this.setText("Cancel purchase");
        this.addActionListener(event -> {
            JPanel panel = new JPanel(new BorderLayout(5, 5));
            panel.setPreferredSize(new Dimension(300, 20));
            JPanel labelPanel = new JPanel(new GridLayout(0, 1, 3, 3));
            labelPanel.add(new JLabel("Ваш ID пассажира", SwingConstants.RIGHT));
            panel.add(labelPanel, BorderLayout.WEST);

            JPanel controlPanel = new JPanel(new GridLayout(0, 1, 3, 3));
            JTextField passengerTextField = new JTextField();
            controlPanel.add(passengerTextField);
            panel.add(controlPanel, BorderLayout.CENTER);

            int result = JOptionPane.showConfirmDialog(this, panel, "Cancel tickets purchase", JOptionPane.OK_CANCEL_OPTION);
            if (result != JOptionPane.OK_OPTION) {
                return;
            }
            try {
                TicketStatusHistory ticketStatusHistory = new TicketStatusHistory();
                ticketStatusHistory.setPassengerId(Integer.valueOf(passengerTextField.getText()));
                var boughtTickets = ticketStatusHistory.getSpecifiedTicketsById
                        (controllerManager.getProvider(), ApplicationConstants.TICKET_STATUSES.get("Выкуплен"));
                if (boughtTickets.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Вы: пассажир с ID = " + ticketStatusHistory.getPassengerId() + ", не имеете купленных билетов");
                    return;
                }
                JPanel ticketsPanel = new JPanel(new BorderLayout(5, 5));
                ticketsPanel.setPreferredSize(new Dimension(500, 30));
                JPanel ticketsLabelPanel = new JPanel(new GridLayout(0, 1, 3, 3));
                ticketsLabelPanel.add(new JLabel("Выберите нужные вылет и место", SwingConstants.RIGHT));
                ticketsPanel.add(ticketsLabelPanel, BorderLayout.WEST);

                JPanel ticketsControlPanel = new JPanel(new GridLayout(0, 1, 3, 3));
                JComboBox<String> ticketsComboBox = new JComboBox<>(TicketsFormConverter.convertToConjointArray(boughtTickets));
                ticketsControlPanel.add(ticketsComboBox);
                ticketsPanel.add(ticketsControlPanel, BorderLayout.CENTER);

                result = JOptionPane.showConfirmDialog(this, ticketsPanel, "Bought tickets", JOptionPane.OK_CANCEL_OPTION);
                if (result != JOptionPane.OK_OPTION) {
                    return;
                }
                result = JOptionPane.showConfirmDialog(this,
                        "Вы действительно хотите отменить покупку " + ticketsComboBox.getSelectedItem() + "?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION);
                if (result != JOptionPane.OK_OPTION) {
                    return;
                }
                Ticket ticket = TicketsFormConverter.getTicketFromConjointForm((String) ticketsComboBox.getSelectedItem());
                controllerManager.changeTicketStatus(null,
                        ticket.getDepartureId(),
                        ticket.getSeat(),
                        ApplicationConstants.TICKET_STATUSES.get("В продаже"));
                JOptionPane.showMessageDialog(null, "Заказ успешно отменен.");
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage());
            }
        });
    }
}
