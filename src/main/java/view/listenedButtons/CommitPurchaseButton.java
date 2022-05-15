package view.listenedButtons;

import controller.ControllerManager;
import converters.TicketsFormConverter;
import entities.Ticket;
import entities.TicketStatusHistory;
import model.ApplicationConstants;

import javax.swing.*;
import java.awt.*;

public class CommitPurchaseButton extends JButton {
    public CommitPurchaseButton(ControllerManager controllerManager) {
        this.setText("Commit purchase");
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

            int result = JOptionPane.showConfirmDialog(this, panel, "Commit tickets purchase", JOptionPane.OK_CANCEL_OPTION);
            if (result != JOptionPane.OK_OPTION) {
                return;
            }
            try {
                TicketStatusHistory ticketStatusHistory = new TicketStatusHistory();
                ticketStatusHistory.setPassengerId(Integer.valueOf(passengerTextField.getText()));
                var bookedTickets = ticketStatusHistory.getSpecifiedTicketsById
                        (controllerManager.getProvider(), ApplicationConstants.TICKET_STATUSES.get("Забронирован"));
                if (bookedTickets.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Вы: пассажир с ID = " + ticketStatusHistory.getPassengerId() + ", не имеете забронированных билетов");
                    return;
                }
                JPanel ticketsPanel = new JPanel(new BorderLayout(5, 5));
                ticketsPanel.setPreferredSize(new Dimension(500, 30));
                JPanel ticketsLabelPanel = new JPanel(new GridLayout(0, 1, 3, 3));
                ticketsLabelPanel.add(new JLabel("Выберите нужные вылет и место", SwingConstants.RIGHT));
                ticketsPanel.add(ticketsLabelPanel, BorderLayout.WEST);

                JPanel ticketsControlPanel = new JPanel(new GridLayout(0, 1, 3, 3));
                JComboBox<String> ticketsComboBox = new JComboBox<>(TicketsFormConverter.convertToConjointArray(bookedTickets));
                ticketsControlPanel.add(ticketsComboBox);
                ticketsPanel.add(ticketsControlPanel, BorderLayout.CENTER);

                result = JOptionPane.showConfirmDialog(this, ticketsPanel, "Booked tickets", JOptionPane.OK_CANCEL_OPTION);
                if (result != JOptionPane.OK_OPTION) {
                    return;
                }
                String[] answerOptions = {"Отменить бронирование", "Выйти из формы", "Выкупить"};
                JLabel label = new JLabel("<html><center>Что вы хотите сделать с заказом " + ticketsComboBox.getSelectedItem() + "?");
                label.setHorizontalAlignment(SwingConstants.CENTER);
                result = JOptionPane.showOptionDialog(this,
                        label, "Choosing options",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, answerOptions, answerOptions[0]);
                result++;
                Ticket ticket = TicketsFormConverter.getTicketFromConjointForm((String) ticketsComboBox.getSelectedItem());
                if (result == ApplicationConstants.TICKET_STATUSES.get("Забронирован")) {
                    return;
                }
                String message = "";
                if (result == ApplicationConstants.TICKET_STATUSES.get("В продаже")) {
                    controllerManager.changeTicketStatus(null,
                            ticket.getDepartureId(),
                            ticket.getSeat(),
                            ApplicationConstants.TICKET_STATUSES.get("В продаже"));
                    message = "Бронирование успешно отменено.";
                } else if (result == ApplicationConstants.TICKET_STATUSES.get("Выкуплен")) {
                    controllerManager.changeTicketStatus(Integer.valueOf(passengerTextField.getText()),
                            ticket.getDepartureId(),
                            ticket.getSeat(),
                            ApplicationConstants.TICKET_STATUSES.get("Выкуплен"));
                    message = "Билет успешно выкуплен!";
                }
                JOptionPane.showMessageDialog(null, message);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage());
            }
        });
    }
}
