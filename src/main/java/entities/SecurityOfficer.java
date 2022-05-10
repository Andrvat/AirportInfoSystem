package entities;

import annotations.DbColumnNumber;
import annotations.DbConstrains;
import annotations.DbTable;
import dbConnection.OracleDbProvider;

import java.sql.SQLException;
import java.util.HashMap;

@DbTable(name = "SECURITY_OFFICER")
public class SecurityOfficer extends AbstractComponent {
    @DbColumnNumber(name = "security_officer_id", constrains = @DbConstrains(isPrimaryKey = true, isAllowedNull = false))
    private Integer idSecurityOfficer;

    @DbColumnNumber(name = "security_work_direction_id", constrains = @DbConstrains(isAllowedNull = false))
    private Integer securityWorkDirectionId;

    public SecurityOfficer() {
        super(SecurityOfficer.class.getAnnotation(DbTable.class).name());
    }

    public static String getIdSecurityOfficerAnnotationName() throws NoSuchFieldException {
        return SecurityOfficer.class.getDeclaredField("idSecurityOfficer").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getSecurityWorkDirectionIdAnnotationName() throws NoSuchFieldException {
        return SecurityOfficer.class.getDeclaredField("securityWorkDirectionId").getAnnotation(DbColumnNumber.class).name();
    }

    public Integer getIdSecurityOfficer() {
        return idSecurityOfficer;
    }

    public void setIdSecurityOfficer(Integer idSecurityOfficer) {
        this.idSecurityOfficer = idSecurityOfficer;
    }

    public Integer getSecurityWorkDirectionId() {
        return securityWorkDirectionId;
    }

    public void setSecurityWorkDirectionId(Integer securityWorkDirectionId) {
        this.securityWorkDirectionId = securityWorkDirectionId;
    }

    @Override
    public void saveValues(OracleDbProvider provider) throws IllegalAccessException, SQLException {
        AbstractComponent.saveTo(SecurityOfficer.class, this, provider, this.getTableName());
    }

    @Override
    public String[][] getAllRows(OracleDbProvider provider) throws SQLException {
        return new String[0][];
    }

    @Override
    public void deleteRowByPrimaryKey(OracleDbProvider provider) throws NoSuchFieldException, SQLException {
        AbstractComponent.deleteFrom(this.getTableName(), provider,
                new HashMap<>() {{
                    put(SecurityOfficer.getIdSecurityOfficerAnnotationName(), String.valueOf(idSecurityOfficer));

                }});
    }

    @Override
    public void updateRow(OracleDbProvider provider) throws SQLException, IllegalAccessException, NoSuchFieldException {
        AbstractComponent.updateTo(SecurityOfficer.class, this, provider, this.getTableName(),
                new HashMap<>() {{
                    put(SecurityOfficer.getIdSecurityOfficerAnnotationName(), String.valueOf(idSecurityOfficer));
                }});
    }
}
