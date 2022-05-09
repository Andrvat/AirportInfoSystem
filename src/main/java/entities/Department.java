package entities;

import annotations.DbColumnNumber;
import annotations.DbColumnVarchar;
import annotations.DbConstrains;
import annotations.DbTable;
import dbConnection.OracleDbProvider;

import java.sql.SQLException;

@DbTable(name = "DEPARTMENT")
public class Department extends AbstractComponent {
    @DbColumnNumber(name = "department_id", constrains = @DbConstrains(isPrimaryKey = true, isAllowedNull = false))
    private Integer idDepartment;

    @DbColumnVarchar(name = "department_name", value = 255, constrains = @DbConstrains(isAllowedNull = false))
    private String departmentName;

    @DbColumnNumber(name = "director_id", constrains = @DbConstrains(isAllowedNull = false, isUnique = true))
    private Integer directorId;

    public Department() {
        super(Department.class.getAnnotation(DbTable.class).name());
    }

    public static String getIdDepartmentAnnotationName() throws NoSuchFieldException {
        return Department.class.getDeclaredField("idDepartment").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getDepartmentNameAnnotationName() throws NoSuchFieldException {
        return Department.class.getDeclaredField("departmentName").getAnnotation(DbColumnVarchar.class).name();
    }

    public static String getDirectorIdAnnotationName() throws NoSuchFieldException {
        return Department.class.getDeclaredField("directorId").getAnnotation(DbColumnNumber.class).name();
    }

    public Integer getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(Integer idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Integer directorId) {
        this.directorId = directorId;
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
