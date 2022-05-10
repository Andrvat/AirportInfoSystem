package entities;

import annotations.DbColumnNumber;
import annotations.DbColumnVarchar;
import annotations.DbConstrains;
import annotations.DbTable;
import dbConnection.OracleDbProvider;

import java.sql.SQLException;
import java.util.HashMap;

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
        AbstractComponent.saveTo(Department.class, this, provider, this.getTableName());
    }

    @Override
    public String[][] getAllRows(OracleDbProvider provider) throws SQLException, IllegalAccessException {
        return AbstractComponent.getAllFrom(Department.class, this, provider, this.getTableName());
    }

    @Override
    public void deleteRowByPrimaryKey(OracleDbProvider provider) throws NoSuchFieldException, SQLException {
        AbstractComponent.deleteFrom(this.getTableName(), provider,
                new HashMap<>() {{
                    put(Department.getIdDepartmentAnnotationName(), String.valueOf(idDepartment));

                }});
    }

    @Override
    public void updateRow(OracleDbProvider provider) throws SQLException, IllegalAccessException, NoSuchFieldException {
        AbstractComponent.updateTo(Department.class, this, provider, this.getTableName(),
                new HashMap<>() {{
                    put(Department.getIdDepartmentAnnotationName(), String.valueOf(idDepartment));
                }});
    }
}
