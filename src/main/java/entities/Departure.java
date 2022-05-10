package entities;

import annotations.DbColumnDate;
import annotations.DbColumnNumber;
import annotations.DbConstrains;
import annotations.DbTable;
import dbConnection.OracleDbProvider;
import model.support.TimeCalendar;

import java.sql.SQLException;
import java.util.HashMap;

@DbTable(name = "DEPARTURE")
public class Departure extends AbstractComponent {
    @DbColumnNumber(name = "departure_id", constrains = @DbConstrains(isPrimaryKey = true, isAllowedNull = false))
    private Integer idDeparture;

    @DbColumnNumber(name = "airplane_id")
    private Integer airplaneId;

    @DbColumnNumber(name = "flight_id", constrains = @DbConstrains(isAllowedNull = false))
    private Integer flightId;

    @DbColumnDate(name = "departure_date", constrains = @DbConstrains(isAllowedNull = false))
    private TimeCalendar departureDate;

    @DbColumnDate(name = "arrival_date", constrains = @DbConstrains(isAllowedNull = false))
    private TimeCalendar arrivalDate;

    @DbColumnNumber(name = "departure_status_id", constrains = @DbConstrains(isAllowedNull = false))
    private Integer departureStatusId;

    @DbColumnDate(name = "status_set_date", constrains = @DbConstrains(isAllowedNull = false))
    private TimeCalendar statusSetDate;

    public Departure() {
        super(Departure.class.getAnnotation(DbTable.class).name());
    }

    public static String getIdDepartureAnnotationName() throws NoSuchFieldException {
        return Departure.class.getDeclaredField("idDeparture").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getAirplaneIdAnnotationName() throws NoSuchFieldException {
        return Departure.class.getDeclaredField("airplaneId").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getFlightIdAnnotationName() throws NoSuchFieldException {
        return Departure.class.getDeclaredField("flightId").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getDepartureDateAnnotationName() throws NoSuchFieldException {
        return Departure.class.getDeclaredField("departureDate").getAnnotation(DbColumnDate.class).name();
    }

    public static String getArrivalDateAnnotationName() throws NoSuchFieldException {
        return Departure.class.getDeclaredField("arrivalDate").getAnnotation(DbColumnDate.class).name();
    }

    public static String getDepartureStatusIdAnnotationName() throws NoSuchFieldException {
        return Departure.class.getDeclaredField("departureStatusId").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getStatusSetDateAnnotationName() throws NoSuchFieldException {
        return Departure.class.getDeclaredField("statusSetDate").getAnnotation(DbColumnDate.class).name();
    }

    public Integer getIdDeparture() {
        return idDeparture;
    }

    public void setIdDeparture(Integer idDeparture) {
        this.idDeparture = idDeparture;
    }

    public Integer getAirplaneId() {
        return airplaneId;
    }

    public void setAirplaneId(Integer airplaneId) {
        this.airplaneId = airplaneId;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public TimeCalendar getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(TimeCalendar departureDate) {
        this.departureDate = departureDate;
    }

    public TimeCalendar getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(TimeCalendar arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Integer getDepartureStatusId() {
        return departureStatusId;
    }

    public void setDepartureStatusId(Integer departureStatusId) {
        this.departureStatusId = departureStatusId;
    }

    public TimeCalendar getStatusSetDate() {
        return statusSetDate;
    }

    public void setStatusSetDate(TimeCalendar statusSetDate) {
        this.statusSetDate = statusSetDate;
    }

    @Override
    public void saveValues(OracleDbProvider provider) throws IllegalAccessException, SQLException {
        AbstractComponent.saveTo(Departure.class, this, provider, this.getTableName());
    }

    @Override
    public String[][] getAllRows(OracleDbProvider provider) throws SQLException, IllegalAccessException {
        return AbstractComponent.getAllFrom(Departure.class, this, provider, this.getTableName());
    }

    @Override
    public void deleteRowByPrimaryKey(OracleDbProvider provider) throws NoSuchFieldException, SQLException {
        AbstractComponent.deleteFrom(this.getTableName(), provider,
                new HashMap<>() {{
                    put(Departure.getIdDepartureAnnotationName(), String.valueOf(idDeparture));

                }});
    }

    @Override
    public void updateRow(OracleDbProvider provider) throws SQLException, IllegalAccessException, NoSuchFieldException {
        AbstractComponent.updateTo(Departure.class, this, provider, this.getTableName(),
                new HashMap<>() {{
                    put(Departure.getIdDepartureAnnotationName(), String.valueOf(idDeparture));
                }});
    }
}
