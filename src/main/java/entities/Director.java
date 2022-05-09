package entities;

import annotations.DbColumnNumber;
import annotations.DbColumnVarchar;
import annotations.DbConstrains;
import annotations.DbTable;
import dbConnection.OracleDbProvider;

import java.sql.SQLException;

@DbTable(name = "DIRECTOR")
public class Director extends AbstractComponent {
    @DbColumnNumber(name = "director_id", constrains = @DbConstrains(isPrimaryKey = true, isAllowedNull = false))
    private Integer idDirector;

    @DbColumnVarchar(name = "eng_level_id", constrains = @DbConstrains(isAllowedNull = false))
    private String engLevelId;

    public Director() {
        super(Director.class.getAnnotation(DbTable.class).name());
    }

    public static String getIdDirectorAnnotationName() throws NoSuchFieldException {
        return Director.class.getDeclaredField("idDirector").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getEngLevelIdIdAnnotationName() throws NoSuchFieldException {
        return Director.class.getDeclaredField("engLevelId").getAnnotation(DbColumnVarchar.class).name();
    }

    public Integer getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(Integer idDirector) {
        this.idDirector = idDirector;
    }

    public String getEngLevelId() {
        return engLevelId;
    }

    public void setEngLevelId(String engLevelId) {
        this.engLevelId = engLevelId;
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
