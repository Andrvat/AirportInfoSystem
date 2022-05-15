package converters;

import entities.Ticket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TicketsFormConverter {
    public static String[] convertToConjointArray(List<String[]> tickets) {
        List<String> answer = new ArrayList<>();
        for (var ticket : tickets) {
            answer.add("dep. № " + ticket[1] + " | seat № " + ticket[2]);
        }
        return answer.toArray(new String[0]);
    }

    public static Ticket getTicketFromConjointForm(String form) {
        form = form.replaceAll("[^0-9]+", " ");
        List<String> list = new ArrayList<>(Arrays.asList(form.trim().split(" ")));
        Ticket ticket = new Ticket();
        ticket.setDepartureId(Integer.valueOf(list.get(0)));
        ticket.setSeat(Integer.valueOf(list.get(1)));
        return ticket;
    }
}
