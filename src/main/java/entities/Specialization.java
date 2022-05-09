package entities;

import annotations.DbColumnNumber;
import annotations.DbColumnVarchar;
import annotations.DbConstrains;
import annotations.DbTable;
import dbConnection.OracleDbProvider;

import java.sql.SQLException;

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
