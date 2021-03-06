package entities;

import annotations.DbColumnNumber;
import annotations.DbColumnVarchar;
import annotations.DbConstrains;
import annotations.DbTable;
import dbConnection.OracleDbProvider;
import forms.RequestResultPackage;
import model.DbModel;

import java.sql.SQLException;
import java.util.HashMap;

@DbTable(name = "AIRLINE")
public class Airline extends AbstractComponent {
    @DbColumnNumber(name = "airline_id", constrains = @DbConstrains(isPrimaryKey = true, isAllowedNull = false))
    private Integer idAirline;

    @DbColumnVarchar(name = "airline_name", value = 255, constrains = @DbConstrains(isAllowedNull = false))
    private String airlineName;

    public Airline() {
        super(Airline.class.getAnnotation(DbTable.class).name());
    }

    public static String getIdAirlineAnnotationName() throws NoSuchFieldException {
        return Airline.class.getDeclaredField("idAirline").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getAirlineNameAnnotationName() throws NoSuchFieldException {
        return Airline.class.getDeclaredField("airlineName").getAnnotation(DbColumnVarchar.class).name();
    }

    public Integer getIdAirline() {
        return idAirline;
    }

    public void setIdAirline(Integer idAirline) {
        this.idAirline = idAirline;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    @Override
    public void saveValues(OracleDbProvider provider) throws IllegalAccessException, SQLException {
        AbstractComponent.saveTo(Airline.class, this, provider, this.getTableName());
    }

    @Override
    public String[][] getAllRows(OracleDbProvider provider) throws SQLException, IllegalAccessException {
        return AbstractComponent.getAllFrom(Airline.class, this, provider, this.getTableName());
    }

    @Override
    public void deleteRowByPrimaryKey(OracleDbProvider provider) throws NoSuchFieldException, SQLException {
        AbstractComponent.deleteFrom(this.getTableName(), provider,
                new HashMap<>() {{
                    put(Airline.getIdAirlineAnnotationName(), String.valueOf(idAirline));
                }});
    }

    @Override
    public void updateRow(OracleDbProvider provider) throws SQLException, IllegalAccessException, NoSuchFieldException {
        AbstractComponent.updateTo(Airline.class, this, provider, this.getTableName(),
                new HashMap<>() {{
                    put(Airline.getIdAirlineAnnotationName(), String.valueOf(idAirline));
                }});
    }

    @Override
    public RequestResultPackage getPrettyViewingResultPackage(OracleDbProvider provider) throws SQLException, IllegalAccessException {
        var resultPackage = new RequestResultPackage();
        resultPackage.setTableName(this.getTableName());
        resultPackage.setColumnNames(new String[]{"???????????????? ????????????????????????"});
        resultPackage.setResultRows(DbModel.getAllRowsWithoutId(this.getAllRows(provider)));
        return resultPackage;
    }
}
