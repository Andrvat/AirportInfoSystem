package entities;

import annotations.*;
import dbConnection.OracleDbProvider;

import java.sql.SQLException;

@DbTable(name = "LOCATION")
public class Location extends AbstractComponent {
    @DbColumnNumber(name = "location_id", constrains = @DbConstrains(isPrimaryKey = true, isAllowedNull = false))
    private Integer idLocation;

    @DbColumnVarchar(name = "country", value = 255, constrains = @DbConstrains(isAllowedNull = false))
    private String country;

    @DbColumnVarchar(name = "location_name", value = 255, constrains = @DbConstrains(isAllowedNull = false))
    private String locationName;

    @DbColumnBoolean(name = "weather_conditions", constrains = @DbConstrains(isAllowedNull = false))
    private Boolean weatherConditions;

    @DbColumnNumber(name = "timezone", constrains = @DbConstrains(isAllowedNull = false))
    private Integer timezone;

    public Location() {
        super(Location.class.getAnnotation(DbTable.class).name());
    }

    public static String getIdLocationAnnotationName() throws NoSuchFieldException {
        return Location.class.getDeclaredField("idLocation").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getCountryAnnotationName() throws NoSuchFieldException {
        return Location.class.getDeclaredField("country").getAnnotation(DbColumnVarchar.class).name();
    }

    public static String getLocationNameAnnotationName() throws NoSuchFieldException {
        return Location.class.getDeclaredField("locationName").getAnnotation(DbColumnVarchar.class).name();
    }

    public static String getWeatherConditionsAnnotationName() throws NoSuchFieldException {
        return Location.class.getDeclaredField("weatherConditions").getAnnotation(DbColumnBoolean.class).name();
    }

    public static String getTimezoneAnnotationName() throws NoSuchFieldException {
        return Location.class.getDeclaredField("timezone").getAnnotation(DbColumnNumber.class).name();
    }

    public Integer getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(Integer idLocation) {
        this.idLocation = idLocation;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Boolean getWeatherConditions() {
        return weatherConditions;
    }

    public void setWeatherConditions(Boolean weatherConditions) {
        this.weatherConditions = weatherConditions;
    }

    public Integer getTimezone() {
        return timezone;
    }

    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
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
