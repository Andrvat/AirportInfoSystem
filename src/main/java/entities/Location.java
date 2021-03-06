package entities;

import annotations.*;
import dbConnection.OracleDbProvider;
import forms.RequestResultPackage;
import model.DbModel;

import java.sql.SQLException;
import java.util.*;

@DbTable(name = "LOCATION")
public class Location extends AbstractComponent {
    @DbColumnNumber(name = "location_id", constrains = @DbConstrains(isPrimaryKey = true, isAllowedNull = false))
    private Integer idLocation;

    @DbColumnVarchar(name = "country", value = 255, constrains = @DbConstrains(isAllowedNull = false))
    private String country;

    @DbColumnVarchar(name = "location_name", value = 255, constrains = @DbConstrains(isAllowedNull = false))
    private String locationName;

    @DbColumnBoolean(name = "weather_conditions", constrains = @DbConstrains(isAllowedNull = false), type = DbColumnBoolean.BooleanValueType.GOOD_BAD)
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
        AbstractComponent.saveTo(Location.class, this, provider, this.getTableName());
    }

    @Override
    public String[][] getAllRows(OracleDbProvider provider) throws SQLException, IllegalAccessException {
        return AbstractComponent.getAllFrom(Location.class, this, provider, this.getTableName());
    }

    @Override
    public void deleteRowByPrimaryKey(OracleDbProvider provider) throws NoSuchFieldException, SQLException {
        AbstractComponent.deleteFrom(this.getTableName(), provider,
                new HashMap<>() {{
                    put(Location.getIdLocationAnnotationName(), String.valueOf(idLocation));

                }});
    }

    @Override
    public void updateRow(OracleDbProvider provider) throws SQLException, IllegalAccessException, NoSuchFieldException {
        AbstractComponent.updateTo(Location.class, this, provider, this.getTableName(),
                new HashMap<>() {{
                    put(Location.getIdLocationAnnotationName(), String.valueOf(idLocation));
                }});
    }

    @Override
    public RequestResultPackage getPrettyViewingResultPackage(OracleDbProvider provider) throws SQLException, IllegalAccessException {
        var resultPackage = new RequestResultPackage();
        resultPackage.setTableName(this.getTableName());
        resultPackage.setColumnNames(new String[]{"????????????", "??????????", "???????????????? ??????????????", "?????????? ??????. ??????"});
        resultPackage.setResultRows(DbModel.getAllRowsWithoutId(this.getAllRows(provider)));
        return resultPackage;
    }
}
