package model;

import java.util.HashMap;
import java.util.Map;

public class ApplicationConstants {
    public static final String ADMIN = "ADMIN";
    public static final String PASSENGER = "PASSENGER";
    public static final String AIRLINE = "AIRLINE";
    public static final String AIRPORT_MANAGER = "AIRPORT_MANAGER";

    public static final Map<String, Integer> TICKET_STATUSES = new HashMap<>() {{
       put("В продаже", 1);
       put("Забронирован", 2);
       put("Выкуплен", 3);
    }};

    public static String[] getRoleNames() {
        return new String[]{ADMIN, PASSENGER, AIRLINE, AIRPORT_MANAGER};
    }
}
