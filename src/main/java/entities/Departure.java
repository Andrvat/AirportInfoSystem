package entities;

import annotations.DbColumnDate;
import annotations.DbColumnNumber;
import annotations.DbConstrains;
import annotations.DbTable;
import dbConnection.OracleDbProvider;
import forms.RequestResultPackage;
import model.support.TimeCalendar;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

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

    @Override
    public RequestResultPackage getPrettyViewingResultPackage(OracleDbProvider provider) throws SQLException {
        var resultPackage = new RequestResultPackage();
        resultPackage.setTableName(this.getTableName());
        var resultSet = provider.getStringsQueryResultSet(
                "WITH ARRIVAL_LOCATIONS AS ( " +
                        "SELECT FLIGHT_ID, LOCATION_NAME AS arrival_location_name " +
                        "FROM FLIGHT " +
                        "LEFT OUTER JOIN LOCATION ON ARRIVAL_LOCATION_ID = LOCATION_ID), " +
                        "DEPARTURE_LOCATIONS AS ( " +
                        "SELECT FLIGHT_ID, LOCATION_NAME AS departure_location_name " +
                        "FROM FLIGHT " +
                        "LEFT OUTER JOIN LOCATION ON DEPARTURE_LOCATION_ID = LOCATION_ID) " +
                        "SELECT departure_id, departure_location_name, arrival_location_name, departure_date, " +
                        "arrival_date, description, travel_time, ticket_price, airline_name, " +
                        "category_name, type_name, people_capacity " +
                        "FROM DEPARTURE " +
                        "LEFT JOIN FLIGHT USING (flight_id) " +
                        "LEFT JOIN AIRPLANE_TYPE USING (airplane_type_id) " +
                        "LEFT JOIN DEPARTURE_LOCATIONS USING (flight_id) " +
                        "LEFT JOIN ARRIVAL_LOCATIONS USING (flight_id) " +
                        "LEFT JOIN DEPARTURE_STATUS USING (departure_status_id) " +
                        "LEFT JOIN AIRLINE USING (airline_id) " +
                        "LEFT JOIN FLIGHT_CATEGORY USING (flight_category_id) " +
                        "ORDER BY departure_id",
                Collections.emptyList());
        resultPackage.setColumnNames(new String[]{"Номер вылета", "Место вылета", "Место прилета", "Дата вылета",
                "Дата прилета", "Статус рейса", "Время в пути", "Цена билета", "Авиакомпания", "Категория",
                "Самолет", "Вместимость"});
        List<String[]> allRows = new ArrayList<>();
        final double nightDiscount = 0.15;
        while (resultSet.next()) {
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(resultSet.getInt(1)));
            row.add(resultSet.getString(2));
            row.add(resultSet.getString(3));
            row.add(new TimeCalendar(resultSet.getDate(4)).toTypedString(TimeCalendar.TimeCalendarType.FULL_DATE));
            row.add(new TimeCalendar(resultSet.getDate(5)).toTypedString(TimeCalendar.TimeCalendarType.FULL_DATE));
            row.add(resultSet.getString(6));
            row.add(new TimeCalendar(resultSet.getDate(7)).toTypedString(TimeCalendar.TimeCalendarType.TIME_ONLY));
            row.add(new TimeCalendar(resultSet.getDate(4)).isBetween(
                    new TimeCalendar(0, 0, 0),
                    new TimeCalendar(6, 0, 0)) ?
                    String.valueOf((int) (resultSet.getInt(8) * (1 - nightDiscount)))
                    : String.valueOf(resultSet.getInt(8)));
            row.add(resultSet.getString(9));
            row.add(resultSet.getString(10));
            row.add(resultSet.getString(11));
            row.add(String.valueOf(resultSet.getInt(12)));
            allRows.add(row.toArray(new String[0]));
        }
        resultPackage.setResultRows(allRows.toArray(new String[0][]));
        return resultPackage;
    }
}
