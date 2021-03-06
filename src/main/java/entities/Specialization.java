package entities;

import annotations.DbColumnNumber;
import annotations.DbColumnVarchar;
import annotations.DbConstrains;
import annotations.DbTable;
import dbConnection.OracleDbProvider;

import java.sql.SQLException;
import java.util.HashMap;

@DbTable(name = "SPECIALIZATION")
public class Specialization extends AbstractComponent {
    @DbColumnNumber(name = "specialty_id", constrains = @DbConstrains(isPrimaryKey = true, isAllowedNull = false))
    private Integer idSpecialty;

    @DbColumnVarchar(name = "specialty_name", value = 255, constrains = @DbConstrains(isAllowedNull = false, isUnique = true))
    private String specialtyName;

    public Specialization() {
        super(Specialization.class.getAnnotation(DbTable.class).name());
    }

    public static String getIdSpecialtyAnnotationName() throws NoSuchFieldException {
        return Specialization.class.getDeclaredField("idSpecialty").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getSpecialtyNameAnnotationName() throws NoSuchFieldException {
        return Specialization.class.getDeclaredField("specialtyName").getAnnotation(DbColumnVarchar.class).name();
    }

    public Integer getIdSpecialty() {
        return idSpecialty;
    }

    public void setIdSpecialty(Integer idSpecialty) {
        this.idSpecialty = idSpecialty;
    }

    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }

    @Override
    public void saveValues(OracleDbProvider provider) throws IllegalAccessException, SQLException {
        AbstractComponent.saveTo(Specialization.class, this, provider, this.getTableName());
    }

    @Override
    public String[][] getAllRows(OracleDbProvider provider) throws SQLException, IllegalAccessException {
        return AbstractComponent.getAllFrom(Specialization.class, this, provider, this.getTableName());
    }

    @Override
    public void deleteRowByPrimaryKey(OracleDbProvider provider) throws NoSuchFieldException, SQLException {
        AbstractComponent.deleteFrom(this.getTableName(), provider,
                new HashMap<>() {{
                    put(Specialization.getIdSpecialtyAnnotationName(), String.valueOf(idSpecialty));

                }});
    }

    @Override
    public void updateRow(OracleDbProvider provider) throws SQLException, IllegalAccessException, NoSuchFieldException {
        AbstractComponent.updateTo(Specialization.class, this, provider, this.getTableName(),
                new HashMap<>() {{
                    put(Specialization.getIdSpecialtyAnnotationName(), String.valueOf(idSpecialty));
                }});
    }
}
