package entities;

import annotations.DbColumnDate;
import annotations.DbColumnNumber;
import annotations.DbConstrains;
import annotations.DbTable;
import dbConnection.OracleDbProvider;
import model.support.TimeCalendar;

import java.sql.SQLException;
import java.util.HashMap;

@DbTable(name = "FLIGHT_DAY")
public class FlightDay extends AbstractComponent {
    @DbColumnNumber(name = "record_id", constrains = @DbConstrains(isPrimaryKey = true, isAllowedNull = false))
    private Integer idRecord;

    @DbColumnNumber(name = "flight_id", constrains = @DbConstrains(isAllowedNull = false))
    private Integer flightId;

    @DbColumnNumber(name = "week_day", constrains = @DbConstrains(isAllowedNull = false))
    private Integer weekDay;

    @DbColumnDate(name = "departure_time", constrains = @DbConstrains(isAllowedNull = false))
    private TimeCalendar departureTime;

    public FlightDay() {
        super(FlightDay.class.getAnnotation(DbTable.class).name());
    }

    public static String getIdRecordAnnotationName() throws NoSuchFieldException {
        return FlightDay.class.getDeclaredField("idRecord").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getFlightIdAnnotationName() throws NoSuchFieldException {
        return FlightDay.class.getDeclaredField("flightId").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getWeekDayAnnotationName() throws NoSuchFieldException {
        return FlightDay.class.getDeclaredField("weekDay").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getDepartureTimeAnnotationName() throws NoSuchFieldException {
        return FlightDay.class.getDeclaredField("departureTime").getAnnotation(DbColumnDate.class).name();
    }

    public Integer getIdRecord() {
        return idRecord;
    }

    public void setIdRecord(Integer idRecord) {
        this.idRecord = idRecord;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public Integer getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(Integer weekDay) {
        this.weekDay = weekDay;
    }

    public TimeCalendar getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(TimeCalendar departureTime) {
        this.departureTime = departureTime;
    }

    @Override
    public void saveValues(OracleDbProvider provider) throws IllegalAccessException, SQLException {
        AbstractComponent.saveTo(FlightDay.class, this, provider, this.getTableName());
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
        AbstractComponent.updateTo(FlightDay.class, this, provider, this.getTableName(),
                new HashMap<>() {{
                    put(FlightDay.getIdRecordAnnotationName(), String.valueOf(idRecord));
                }});
    }
}
