package view.utilities;

import entities.Passenger;
import entities.Ticket;

import java.util.Map;

public class TableRecordBuilder {
    public static Passenger buildPassenger(Map<String, String> keyValues) throws NoSuchFieldException {
        Passenger passenger = new Passenger();
        passenger.setSurname(keyValues.get(Passenger.getSurnameAnnotationName()));
        passenger.setName(keyValues.get(Passenger.getPassengerNameAnnotationName()));
        passenger.setPatronymic(keyValues.get(Passenger.getPatronymicAnnotationName()));
        passenger.setPassportNumber(Integer.valueOf(keyValues.get(Passenger.getPassportAnnotationName())));
        passenger.setInternationalPassportNumber(Integer.valueOf(keyValues.get(Passenger.getInternationalPassportAnnotationName())));
        passenger.setCustomControlPassed("Y".equals(keyValues.get(Passenger.getCustomControlAnnotationName())));

        return passenger;
    }

    public static Ticket buildTicket(Map<String, String> keyValues) throws NoSuchFieldException {
        Ticket ticket = new Ticket();
        ticket.setIdTicket(Integer.valueOf(keyValues.get(Ticket.getIdTicketAnnotationName())));
        ticket.setFirstName(keyValues.get(Ticket.getFirstNameAnnotationName()));
        ticket.setLastName(keyValues.get(Ticket.getLastNameAnnotationName()));
        ticket.setPatronymic(keyValues.get(Ticket.getPatronymicAnnotationName()));
        ticket.setSeatNumber(Integer.valueOf(keyValues.get(Ticket.getSeatNumberAnnotationName())));

        return ticket;
    }
}
