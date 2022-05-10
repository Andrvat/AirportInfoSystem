package entities;

import annotations.DbColumnNumber;
import annotations.DbConstrains;
import annotations.DbTable;
import dbConnection.OracleDbProvider;

import java.sql.SQLException;
import java.util.HashMap;

@DbTable(name = "TECHNICIAN")
public class Technician extends AbstractComponent {
    @DbColumnNumber(name = "technician_id", constrains = @DbConstrains(isPrimaryKey = true, isAllowedNull = false))
    private Integer idTechnician;

    @DbColumnNumber(name = "technician_work_direction_id", constrains = @DbConstrains(isAllowedNull = false))
    private Integer technicianWorkDirectionId;

    public Technician() {
        super(Technician.class.getAnnotation(DbTable.class).name());
    }

    public static String getIdTechnicianAnnotationName() throws NoSuchFieldException {
        return Technician.class.getDeclaredField("idTechnician").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getTechnicianWorkDirectionIdAnnotationName() throws NoSuchFieldException {
        return Technician.class.getDeclaredField("technicianWorkDirectionId").getAnnotation(DbColumnNumber.class).name();
    }

    public Integer getIdTechnician() {
        return idTechnician;
    }

    public void setIdTechnician(Integer idTechnician) {
        this.idTechnician = idTechnician;
    }

    public Integer getTechnicianWorkDirectionId() {
        return technicianWorkDirectionId;
    }

    public void setTechnicianWorkDirectionId(Integer technicianWorkDirectionId) {
        this.technicianWorkDirectionId = technicianWorkDirectionId;
    }

    @Override
    public void saveValues(OracleDbProvider provider) throws IllegalAccessException, SQLException {
        AbstractComponent.saveTo(Technician.class, this, provider, this.getTableName());
    }

    @Override
    public String[][] getAllRows(OracleDbProvider provider) throws SQLException {
        return new String[0][];
    }

    @Override
    public void deleteRowByPrimaryKey(OracleDbProvider provider) throws NoSuchFieldException, SQLException {
        AbstractComponent.deleteFrom(this.getTableName(), provider,
                new HashMap<>() {{
                    put(Technician.getIdTechnicianAnnotationName(), String.valueOf(idTechnician));
                }});
    }

    @Override
    public void updateRow(OracleDbProvider provider) throws SQLException, IllegalAccessException, NoSuchFieldException {
        AbstractComponent.updateTo(Technician.class, this, provider, this.getTableName(),
                new HashMap<>() {{
                    put(Technician.getIdTechnicianAnnotationName(), String.valueOf(idTechnician));
                }});
    }
}
