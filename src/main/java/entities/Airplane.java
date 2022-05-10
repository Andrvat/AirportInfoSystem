package entities;

import annotations.DbColumnNumber;
import annotations.DbConstrains;
import annotations.DbTable;
import dbConnection.OracleDbProvider;

import java.sql.SQLException;
import java.util.HashMap;

@DbTable(name = "AIRPLANE")
public class Airplane extends AbstractComponent {
    @DbColumnNumber(name = "airplane_id", constrains = @DbConstrains(isPrimaryKey = true, isAllowedNull = false))
    private Integer idAirplane;

    @DbColumnNumber(name = "airplane_type_id", constrains = @DbConstrains(isAllowedNull = false))
    private Integer airplaneTypeId;

    @DbColumnNumber(name = "manufacture_year", constrains = @DbConstrains(isAllowedNull = false))
    private Integer manufactureYear;

    @DbColumnNumber(name = "pilot_crew_id", constrains = @DbConstrains(isAllowedNull = false))
    private Integer pilotCrewId;

    @DbColumnNumber(name = "technical_crew_id", constrains = @DbConstrains(isAllowedNull = false))
    private Integer technicalCrewId;

    @DbColumnNumber(name = "service_crew_id", constrains = @DbConstrains(isAllowedNull = false))
    private Integer serviceCrewId;

    @DbColumnNumber(name = "airport_location_id", constrains = @DbConstrains(isAllowedNull = false))
    private Integer airportLocationId;

    public Airplane() {
        super(Airplane.class.getAnnotation(DbTable.class).name());
    }

    public static String getIdAirplaneAnnotationName() throws NoSuchFieldException {
        return Airplane.class.getDeclaredField("idAirplane").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getAirplaneTypeIdAnnotationName() throws NoSuchFieldException {
        return Airplane.class.getDeclaredField("airplaneTypeId").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getManufactureYearAnnotationName() throws NoSuchFieldException {
        return Airplane.class.getDeclaredField("manufactureYear").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getPilotCrewIdAnnotationName() throws NoSuchFieldException {
        return Airplane.class.getDeclaredField("pilotCrewId").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getTechnicalCrewIdAnnotationName() throws NoSuchFieldException {
        return Airplane.class.getDeclaredField("technicalCrewId").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getServiceCrewIdAnnotationName() throws NoSuchFieldException {
        return Airplane.class.getDeclaredField("serviceCrewId").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getAirportLocationIdeAnnotationName() throws NoSuchFieldException {
        return Airplane.class.getDeclaredField("airportLocationId").getAnnotation(DbColumnNumber.class).name();
    }

    public Integer getIdAirplane() {
        return idAirplane;
    }

    public void setIdAirplane(Integer idAirplane) {
        this.idAirplane = idAirplane;
    }

    public Integer getAirplaneTypeId() {
        return airplaneTypeId;
    }

    public void setAirplaneTypeId(Integer airplaneTypeId) {
        this.airplaneTypeId = airplaneTypeId;
    }

    public Integer getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(Integer manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public Integer getPilotCrewId() {
        return pilotCrewId;
    }

    public void setPilotCrewId(Integer pilotCrewId) {
        this.pilotCrewId = pilotCrewId;
    }

    public Integer getTechnicalCrewId() {
        return technicalCrewId;
    }

    public void setTechnicalCrewId(Integer technicalCrewId) {
        this.technicalCrewId = technicalCrewId;
    }

    public Integer getServiceCrewId() {
        return serviceCrewId;
    }

    public void setServiceCrewId(Integer serviceCrewId) {
        this.serviceCrewId = serviceCrewId;
    }

    public Integer getAirportLocationId() {
        return airportLocationId;
    }

    public void setAirportLocationId(Integer airportLocationId) {
        this.airportLocationId = airportLocationId;
    }

    @Override
    public void saveValues(OracleDbProvider provider) throws IllegalAccessException, SQLException {
        AbstractComponent.saveTo(Airplane.class, this, provider, this.getTableName());
    }

    @Override
    public String[][] getAllRows(OracleDbProvider provider) throws SQLException {
        return new String[0][];
    }

    @Override
    public void deleteRowByPrimaryKey(OracleDbProvider provider) throws NoSuchFieldException, SQLException {
        AbstractComponent.deleteFrom(this.getTableName(), provider,
                new HashMap<>() {{
                    put(Airplane.getIdAirplaneAnnotationName(), String.valueOf(idAirplane));

                }});
    }

    @Override
    public void updateRow(OracleDbProvider provider) throws SQLException, IllegalAccessException, NoSuchFieldException {
        AbstractComponent.updateTo(Airplane.class, this, provider, this.getTableName(),
                new HashMap<>() {{
                    put(Airplane.getIdAirplaneAnnotationName(), String.valueOf(idAirplane));
                }});
    }
}
