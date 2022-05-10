package entities;

import annotations.DbColumnNumber;
import annotations.DbColumnVarchar;
import annotations.DbConstrains;
import annotations.DbTable;
import dbConnection.OracleDbProvider;

import java.sql.SQLException;
import java.util.HashMap;

@DbTable(name = "SECURITY_WORK_DIRECTION")
public class SecurityWorkDirection extends AbstractComponent {
    @DbColumnNumber(name = "work_direction_id", constrains = @DbConstrains(isPrimaryKey = true, isAllowedNull = false))
    private Integer idWorkDirection;

    @DbColumnVarchar(name = "direction_name", value = 255, constrains = @DbConstrains(isAllowedNull = false, isUnique = true))
    private String directionName;

    public SecurityWorkDirection() {
        super(SecurityWorkDirection.class.getAnnotation(DbTable.class).name());
    }

    public static String getIdWorkDirectionAnnotationName() throws NoSuchFieldException {
        return SecurityWorkDirection.class.getDeclaredField("idWorkDirection").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getDirectionNameAnnotationName() throws NoSuchFieldException {
        return SecurityWorkDirection.class.getDeclaredField("directionName").getAnnotation(DbColumnVarchar.class).name();
    }

    public Integer getIdWorkDirection() {
        return idWorkDirection;
    }

    public void setIdWorkDirection(Integer idWorkDirection) {
        this.idWorkDirection = idWorkDirection;
    }

    public String getDirectionName() {
        return directionName;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }

    @Override
    public void saveValues(OracleDbProvider provider) throws IllegalAccessException, SQLException {
        AbstractComponent.saveTo(SecurityWorkDirection.class, this, provider, this.getTableName());
    }

    @Override
    public String[][] getAllRows(OracleDbProvider provider) throws SQLException, IllegalAccessException {
        return AbstractComponent.getAllFrom(SecurityWorkDirection.class, this, provider, this.getTableName());
    }

    @Override
    public void deleteRowByPrimaryKey(OracleDbProvider provider) throws NoSuchFieldException, SQLException {
        AbstractComponent.deleteFrom(this.getTableName(), provider,
                new HashMap<>() {{
                    put(SecurityOfficer.getIdSecurityOfficerAnnotationName(), String.valueOf(idWorkDirection));

                }});
    }

    @Override
    public void updateRow(OracleDbProvider provider) throws SQLException, IllegalAccessException, NoSuchFieldException {
        AbstractComponent.updateTo(SecurityWorkDirection.class, this, provider, this.getTableName(),
                new HashMap<>() {{
                    put(SecurityOfficer.getIdSecurityOfficerAnnotationName(), String.valueOf(idWorkDirection));
                }});
    }
}
