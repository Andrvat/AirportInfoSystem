package entities;

import annotations.*;
import dbConnection.OracleDbProvider;
import model.support.TimeCalendar;

import java.sql.SQLException;
import java.util.HashMap;

@DbTable(name = "AIRPLANE_SERVICE_HISTORY")
public class AirplaneServiceHistory extends AbstractComponent {
    @DbColumnNumber(name = "record_id", constrains = @DbConstrains(isPrimaryKey = true, isAllowedNull = false))
    private Integer idRecord;

    @DbColumnNumber(name = "airplane_id", constrains = @DbConstrains(isAllowedNull = false))
    private Integer airplaneId;

    @DbColumnBoolean(name = "salon_cleaning", constrains = @DbConstrains(isAllowedNull = false))
    private Boolean salonCleaning;

    @DbColumnBoolean(name = "products_stock_refilling", constrains = @DbConstrains(isAllowedNull = false))
    private Boolean productsStockRefilling;

    @DbColumnDate(name = "service_date", constrains = @DbConstrains(isAllowedNull = false))
    private TimeCalendar serviceDate;

    public AirplaneServiceHistory() {
        super(AirplaneServiceHistory.class.getAnnotation(DbTable.class).name());
    }

    public static String getIdRecordAnnotationName() throws NoSuchFieldException {
        return AirplaneServiceHistory.class.getDeclaredField("idRecord").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getAirplaneIdAnnotationName() throws NoSuchFieldException {
        return AirplaneServiceHistory.class.getDeclaredField("airplaneId").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getSalonCleaningAnnotationName() throws NoSuchFieldException {
        return AirplaneServiceHistory.class.getDeclaredField("salonCleaning").getAnnotation(DbColumnBoolean.class).name();
    }

    public static String getProductsStockRefillingAnnotationName() throws NoSuchFieldException {
        return AirplaneServiceHistory.class.getDeclaredField("productsStockRefilling").getAnnotation(DbColumnBoolean.class).name();
    }

    public static String getServiceDateAnnotationName() throws NoSuchFieldException {
        return AirplaneServiceHistory.class.getDeclaredField("serviceDate").getAnnotation(DbColumnDate.class).name();
    }

    @Override
    public void saveValues(OracleDbProvider provider) throws IllegalAccessException, SQLException {
        AbstractComponent.saveTo(AirplaneServiceHistory.class, this, provider, this.getTableName());
    }

    @Override
    public String[][] getAllRows(OracleDbProvider provider) throws SQLException, IllegalAccessException {
        return AbstractComponent.getAllFrom(AirplaneServiceHistory.class, this, provider, this.getTableName());
    }

    @Override
    public void deleteRowByPrimaryKey(OracleDbProvider provider) throws NoSuchFieldException, SQLException {
        AbstractComponent.deleteFrom(this.getTableName(), provider,
                new HashMap<>() {{
                    put(AirplaneServiceHistory.getIdRecordAnnotationName(), String.valueOf(idRecord));

                }});
    }

    @Override
    public void updateRow(OracleDbProvider provider) throws SQLException, IllegalAccessException, NoSuchFieldException {
        AbstractComponent.updateTo(AirplaneServiceHistory.class, this, provider, this.getTableName(),
                new HashMap<>() {{
                    put(AirplaneServiceHistory.getIdRecordAnnotationName(), String.valueOf(idRecord));
                }});
    }
}
