package entities;

import annotations.DbColumnNumber;
import annotations.DbConstrains;
import annotations.DbTable;
import dbConnection.OracleDbProvider;

import java.sql.SQLException;

@DbTable(name = "CREW")
public class Crew extends AbstractComponent {
    @DbColumnNumber(name = "crew_id", constrains = @DbConstrains(isPrimaryKey = true, isAllowedNull = false))
    private Integer idCrew;

    public Crew() {
        super(Crew.class.getAnnotation(DbTable.class).name());
    }

    public static String getIdCrewAnnotationName() throws NoSuchFieldException {
        return Crew.class.getDeclaredField("idCrew").getAnnotation(DbColumnNumber.class).name();
    }

    public Integer getIdCrew() {
        return idCrew;
    }

    public void setIdCrew(Integer idCrew) {
        this.idCrew = idCrew;
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
