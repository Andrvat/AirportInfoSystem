package view.utilities;

import entities.Passenger;
import entities.Ticket;
import model.support.TimeCalendar;

import java.text.ParseException;
import java.util.Map;

public class TableRecordBuilder {
    public static Passenger buildPassenger(Map<String, String> keyValues) throws NoSuchFieldException, ParseException {
        Passenger passenger = new Passenger();
        passenger.setSurname(keyValues.get(Passenger.getSurnameAnnotationName()));
        passenger.setName(keyValues.get(Passenger.getNameAnnotationName()));
        passenger.setPatronymic(keyValues.get(Passenger.getPatronymicAnnotationName()));
        passenger.setSex("Y".equals(keyValues.get(Passenger.getSexAnnotationName())));
        passenger.setBirthDate(new TimeCalendar(keyValues.get(Passenger.getBirthDateAnnotationName())));
        passenger.setPassport(keyValues.get(Passenger.getPassportAnnotationName()));
        passenger.setInternationalPassport(keyValues.get(Passenger.getInternationalPassportAnnotationName()));
        passenger.setCustomControlPassed("Y".equals(keyValues.get(Passenger.getCustomControlAnnotationName())));
        passenger.setHavingCargo("Y".equals(keyValues.get(Passenger.getHavingCargoAnnotationName())));
        return passenger;
    }

    public static Ticket buildTicket(Map<String, String> keyValues) throws NoSuchFieldException {
        Ticket ticket = new Ticket();
        ticket.setDepartureId(Integer.valueOf(keyValues.get(Ticket.getDepartureIdAnnotationName())));
        ticket.setSeat(Integer.valueOf(keyValues.get(Ticket.getSeatAnnotationName())));
        ticket.setTicketStatusId(Integer.valueOf(keyValues.get(Ticket.getTicketStatusIdAnnotationName())));
        ticket.setBagMaxCapacity(Integer.valueOf(keyValues.get(Ticket.getBagMaxCapacityAnnotationName())));
        return ticket;
    }
}
