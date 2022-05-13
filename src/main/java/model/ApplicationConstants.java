package model;

public class ApplicationConstants {
    public static final String ADMIN = "ADMIN";
    public static final String PASSENGER = "PASSENGER";
    public static final String AIRLINE = "AIRLINE";
    public static final String AIRPORT_MANAGER = "AIRPORT_MANAGER";

    public static String[] getRoleNames() {
        return new String[]{ADMIN, PASSENGER, AIRLINE, AIRPORT_MANAGER};
    }
}
