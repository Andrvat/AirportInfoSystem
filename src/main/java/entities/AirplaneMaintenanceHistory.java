package entities;

import annotations.*;
import dbConnection.OracleDbProvider;
import model.support.TimeCalendar;

import java.sql.SQLException;
import java.util.HashMap;

@DbTable(name = "AIRPLANE_MAINTENANCE_HISTORY")
public class AirplaneMaintenanceHistory extends AbstractComponent {
    @DbColumnNumber(name = "record_id", constrains = @DbConstrains(isPrimaryKey = true, isAllowedNull = false))
    private Integer idRecord;

    @DbColumnNumber(name = "airplane_id", constrains = @DbConstrains(isAllowedNull = false))
    private Integer airplaneId;

    @DbColumnNumber(name = "crew_id", constrains = @DbConstrains(isAllowedNull = false))
    private Integer crewId;

    @DbColumnNumber(name = "maintenance_type_id", constrains = @DbConstrains(isAllowedNull = false))
    private Integer maintenanceTypeId;

    @DbColumnDate(name = "maintenance_date", constrains = @DbConstrains(isAllowedNull = false))
    private TimeCalendar maintenanceDate;

    @DbColumnBoolean(name = "final_serviceability", constrains = @DbConstrains(isAllowedNull = false))
    private Boolean finalServiceability;

    @DbColumnNumber(name = "fuel_filled_amount", constrains = @DbConstrains(isAllowedNull = false))
    private Float fuelFilledAmount;

    public AirplaneMaintenanceHistory() {
        super(AirplaneMaintenanceHistory.class.getAnnotation(DbTable.class).name());
    }

    public static String getIdRecordAnnotationName() throws NoSuchFieldException {
        return AirplaneMaintenanceHistory.class.getDeclaredField("idRecord").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getAirplaneIdAnnotationName() throws NoSuchFieldException {
        return AirplaneMaintenanceHistory.class.getDeclaredField("airplaneId").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getCrewIdAnnotationName() throws NoSuchFieldException {
        return AirplaneMaintenanceHistory.class.getDeclaredField("crewId").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getMaintenanceTypeIdAnnotationName() throws NoSuchFieldException {
        return AirplaneMaintenanceHistory.class.getDeclaredField("maintenanceTypeId").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getMaintenanceDateAnnotationName() throws NoSuchFieldException {
        return AirplaneMaintenanceHistory.class.getDeclaredField("maintenanceDate").getAnnotation(DbColumnDate.class).name();
    }

    public static String getFinalServiceabilityAnnotationName() throws NoSuchFieldException {
        return AirplaneMaintenanceHistory.class.getDeclaredField("finalServiceability").getAnnotation(DbColumnBoolean.class).name();
    }

    public static String getFuelFilledAmountAnnotationName() throws NoSuchFieldException {
        return AirplaneMaintenanceHistory.class.getDeclaredField("fuelFilledAmount").getAnnotation(DbColumnNumber.class).name();
    }

    @Override
    public void saveValues(OracleDbProvider provider) throws IllegalAccessException, SQLException {
        AbstractComponent.saveTo(AirplaneMaintenanceHistory.class, this, provider, this.getTableName());
    }

    @Override
    public String[][] getAllRows(OracleDbProvider provider) throws SQLException, IllegalAccessException {
        return AbstractComponent.getAllFrom(AirplaneMaintenanceHistory.class, this, provider, this.getTableName());
    }

    @Override
    public void deleteRowByPrimaryKey(OracleDbProvider provider) throws NoSuchFieldException, SQLException {
        AbstractComponent.deleteFrom(this.getTableName(), provider,
                new HashMap<>() {{
                    put(AirplaneMaintenanceHistory.getIdRecordAnnotationName(), String.valueOf(idRecord));

                }});
    }

    @Override
    public void updateRow(OracleDbProvider provider) throws SQLException, IllegalAccessException, NoSuchFieldException {
        AbstractComponent.updateTo(AirplaneMaintenanceHistory.class, this, provider, this.getTableName(),
                new HashMap<>() {{
                    put(AirplaneMaintenanceHistory.getIdRecordAnnotationName(), String.valueOf(idRecord));
                }});
    }
}
