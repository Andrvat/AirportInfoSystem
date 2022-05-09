package entities;

import annotations.DbColumnNumber;
import annotations.DbColumnVarchar;
import annotations.DbConstrains;
import annotations.DbTable;
import dbConnection.OracleDbProvider;

import java.sql.SQLException;

@DbTable(name = "DEPARTURE_STATUS")
public class DepartureStatus extends AbstractComponent {
    @DbColumnNumber(name = "departure_status_id", constrains = @DbConstrains(isPrimaryKey = true, isAllowedNull = false))
    private Integer idDepartureStatus;

    @DbColumnVarchar(name = "description", value = 255, constrains = @DbConstrains(isAllowedNull = false, isUnique = true))
    private String description;

    @DbColumnVarchar(name = "reason", value = 255)
    private String reason;

    public DepartureStatus() {
        super(DepartureStatus.class.getAnnotation(DbTable.class).name());
    }

    public static String getIdDepartureStatusAnnotationName() throws NoSuchFieldException {
        return DepartureStatus.class.getDeclaredField("idDepartureStatus").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getDescriptionAnnotationName() throws NoSuchFieldException {
        return DepartureStatus.class.getDeclaredField("description").getAnnotation(DbColumnVarchar.class).name();
    }

    public static String getReasonAnnotationName() throws NoSuchFieldException {
        return DepartureStatus.class.getDeclaredField("reason").getAnnotation(DbColumnVarchar.class).name();
    }

    public Integer getIdDepartureStatus() {
        return idDepartureStatus;
    }

    public void setIdDepartureStatus(Integer idDepartureStatus) {
        this.idDepartureStatus = idDepartureStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public void saveValues(OracleDbProvider provider) throws IllegalAccessException, SQLException {
        AbstractComponent.saveTo(DepartureStatus.class, this, provider, this.getTableName());
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
