package entities;

import annotations.DbColumnDate;
import annotations.DbColumnNumber;
import annotations.DbConstrains;
import annotations.DbTable;
import dbConnection.OracleDbProvider;
import model.support.TimeCalendar;

import java.sql.SQLException;

@DbTable(name = "FLIGHT")
public class Flight extends AbstractComponent {
    @DbColumnNumber(name = "flight_id", constrains = @DbConstrains(isPrimaryKey = true, isAllowedNull = false))
    private Integer idFlight;

    @DbColumnNumber(name = "airplane_type_id", constrains = @DbConstrains(isAllowedNull = false))
    private Integer airplaneTypeId;

    @DbColumnNumber(name = "departure_location_id", constrains = @DbConstrains(isAllowedNull = false))
    private Integer departureLocationId;

    @DbColumnNumber(name = "arrival_location_id", constrains = @DbConstrains(isAllowedNull = false))
    private Integer arrivalLocationId;

    @DbColumnDate(name = "travel_time", constrains = @DbConstrains(isAllowedNull = false))
    private TimeCalendar travelTime;

    @DbColumnNumber(name = "ticket_price", constrains = @DbConstrains(isAllowedNull = false))
    private Float ticketPrice;

    @DbColumnNumber(name = "min_passengers_number", constrains = @DbConstrains(isAllowedNull = false))
    private Integer minPassengersNumber;

    @DbColumnNumber(name = "airline_id", constrains = @DbConstrains(isAllowedNull = false))
    private Integer airlineId;

    @DbColumnNumber(name = "flight_category_id", constrains = @DbConstrains(isAllowedNull = false))
    private Integer flightCategoryId;

    public Flight() {
        super(Flight.class.getAnnotation(DbTable.class).name());
    }

    public static String getIdFlightAnnotationName() throws NoSuchFieldException {
        return Flight.class.getDeclaredField("idFlight").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getAirplaneTypeIdAnnotationName() throws NoSuchFieldException {
        return Flight.class.getDeclaredField("airplaneTypeId").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getDepartureLocationIdAnnotationName() throws NoSuchFieldException {
        return Flight.class.getDeclaredField("departureLocationId").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getArrivalLocationIdAnnotationName() throws NoSuchFieldException {
        return Flight.class.getDeclaredField("arrivalLocationId").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getTravelTimeAnnotationName() throws NoSuchFieldException {
        return Flight.class.getDeclaredField("travelTime").getAnnotation(DbColumnDate.class).name();
    }

    public static String getTicketPriceAnnotationName() throws NoSuchFieldException {
        return Flight.class.getDeclaredField("ticketPrice").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getMinPassengersNumberAnnotationName() throws NoSuchFieldException {
        return Flight.class.getDeclaredField("minPassengersNumber").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getAirlineIdAnnotationName() throws NoSuchFieldException {
        return Flight.class.getDeclaredField("airlineId").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getFlightCategoryIdAnnotationName() throws NoSuchFieldException {
        return Flight.class.getDeclaredField("flightCategoryId").getAnnotation(DbColumnNumber.class).name();
    }

    public Integer getIdFlight() {
        return idFlight;
    }

    public void setIdFlight(Integer idFlight) {
        this.idFlight = idFlight;
    }

    public Integer getAirplaneTypeId() {
        return airplaneTypeId;
    }

    public void setAirplaneTypeId(Integer airplaneTypeId) {
        this.airplaneTypeId = airplaneTypeId;
    }

    public Integer getDepartureLocationId() {
        return departureLocationId;
    }

    public void setDepartureLocationId(Integer departureLocationId) {
        this.departureLocationId = departureLocationId;
    }

    public Integer getArrivalLocationId() {
        return arrivalLocationId;
    }

    public void setArrivalLocationId(Integer arrivalLocationId) {
        this.arrivalLocationId = arrivalLocationId;
    }

    public TimeCalendar getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(TimeCalendar travelTime) {
        this.travelTime = travelTime;
    }

    public Float getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Integer getMinPassengersNumber() {
        return minPassengersNumber;
    }

    public void setMinPassengersNumber(Integer minPassengersNumber) {
        this.minPassengersNumber = minPassengersNumber;
    }

    public Integer getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(Integer airlineId) {
        this.airlineId = airlineId;
    }

    public Integer getFlightCategoryId() {
        return flightCategoryId;
    }

    public void setFlightCategoryId(Integer flightCategoryId) {
        this.flightCategoryId = flightCategoryId;
    }

    @Override
    public void saveValues(OracleDbProvider provider) throws IllegalAccessException, SQLException {

    }

    @Override
    public String[][] getAllRows(OracleDbProvider provider) throws SQLException {
        return new String[0][];
    }

    @Override
    public void deleteRowByPrimaryKey(OracleDbProvider provider) throws NoSuchFieldException, SQLException {

    }

    @Override
    public void updateRow(OracleDbProvider provider) throws SQLException, IllegalAccessException, NoSuchFieldException {

    }
}
