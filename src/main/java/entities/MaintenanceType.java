package entities;

import annotations.DbColumnNumber;
import annotations.DbColumnVarchar;
import annotations.DbConstrains;
import annotations.DbTable;
import dbConnection.OracleDbProvider;
import org.apache.log4j.chainsaw.Main;

import java.sql.SQLException;

@DbTable(name = "MAINTENANCE_TYPE")
public class MaintenanceType extends AbstractComponent {
    @DbColumnNumber(name = "maintenance_type_id", constrains = @DbConstrains(isPrimaryKey = true, isAllowedNull = false))
    private Integer idMaintenanceType;

    @DbColumnVarchar(name = "description", value = 255, constrains = @DbConstrains(isAllowedNull = false, isUnique = true))
    private String description;

    public MaintenanceType() {
        super(MaintenanceType.class.getAnnotation(DbTable.class).name());
    }

    public static String getIdMaintenanceTypeAnnotationName() throws NoSuchFieldException {
        return MaintenanceType.class.getDeclaredField("idMaintenanceType").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getDescriptionAnnotationName() throws NoSuchFieldException {
        return MaintenanceType.class.getDeclaredField("description").getAnnotation(DbColumnVarchar.class).name();
    }

    public Integer getIdMaintenanceType() {
        return idMaintenanceType;
    }

    public void setIdMaintenanceType(Integer idMaintenanceType) {
        this.idMaintenanceType = idMaintenanceType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void saveValues(OracleDbProvider provider) throws IllegalAccessException, SQLException {
        AbstractComponent.saveTo(MaintenanceType.class, this, provider, this.getTableName());
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
