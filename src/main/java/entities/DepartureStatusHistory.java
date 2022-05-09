package entities;

import annotations.DbColumnDate;
import annotations.DbColumnNumber;
import annotations.DbConstrains;
import annotations.DbTable;
import dbConnection.OracleDbProvider;
import model.support.TimeCalendar;

import java.sql.SQLException;
import java.util.HashMap;

@DbTable(name = "DEPARTURE_STATUS_HISTORY")
public class DepartureStatusHistory extends AbstractComponent {
    @DbColumnNumber(name = "record_id", constrains = @DbConstrains(isPrimaryKey = true, isAllowedNull = false))
    private Integer idRecord;

    @DbColumnNumber(name = "departure_id", constrains = @DbConstrains(isAllowedNull = false))
    private Integer departureId;

    @DbColumnNumber(name = "status_id", constrains = @DbConstrains(isAllowedNull = false))
    private Integer statusId;

    @DbColumnDate(name = "status_set_date", constrains = @DbConstrains(isAllowedNull = false))
    private TimeCalendar statusSetDate;

    public DepartureStatusHistory() {
        super(DepartureStatusHistory.class.getAnnotation(DbTable.class).name());
    }

    public static String getIdRecordAnnotationName() throws NoSuchFieldException {
        return DepartureStatusHistory.class.getDeclaredField("idRecord").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getDepartureIdAnnotationName() throws NoSuchFieldException {
        return DepartureStatusHistory.class.getDeclaredField("departureId").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getStatusIdAnnotationName() throws NoSuchFieldException {
        return DepartureStatusHistory.class.getDeclaredField("statusId").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getStatusSetDateAnnotationName() throws NoSuchFieldException {
        return DepartureStatusHistory.class.getDeclaredField("statusSetDate").getAnnotation(DbColumnDate.class).name();
    }

    public Integer getIdRecord() {
        return idRecord;
    }

    public void setIdRecord(Integer idRecord) {
        this.idRecord = idRecord;
    }

    public Integer getDepartureId() {
        return departureId;
    }

    public void setDepartureId(Integer departureId) {
        this.departureId = departureId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public TimeCalendar getStatusSetDate() {
        return statusSetDate;
    }

    public void setStatusSetDate(TimeCalendar statusSetDate) {
        this.statusSetDate = statusSetDate;
    }

    @Override
    public void saveValues(OracleDbProvider provider) throws IllegalAccessException, SQLException {
        AbstractComponent.saveTo(DepartureStatusHistory.class, this, provider, this.getTableName());
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
        AbstractComponent.updateTo(DepartureStatusHistory.class, this, provider, this.getTableName(),
                new HashMap<>() {{
                    put(DepartureStatusHistory.getIdRecordAnnotationName(), String.valueOf(idRecord));
                }});
    }
}
