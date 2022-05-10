package entities;

import annotations.DbColumnNumber;
import annotations.DbColumnVarchar;
import annotations.DbConstrains;
import annotations.DbTable;
import dbConnection.OracleDbProvider;

import java.sql.SQLException;
import java.util.HashMap;

@DbTable(name = "AIRPLANE_TYPE")
public class AirplaneType extends AbstractComponent {
    @DbColumnNumber(name = "airplane_type_id", constrains = @DbConstrains(isPrimaryKey = true, isAllowedNull = false))
    private Integer idAirplaneType;

    @DbColumnVarchar(name = "type_name", value = 255, constrains = @DbConstrains(isAllowedNull = false, isUnique = true))
    private String typeName;

    @DbColumnNumber(name = "people_capacity", constrains = @DbConstrains(isAllowedNull = false))
    private Integer peopleCapacity;

    @DbColumnNumber(name = "cargo_capacity", constrains = @DbConstrains(isAllowedNull = false))
    private Float cargoCapacity;

    public AirplaneType() {
        super(AirplaneType.class.getAnnotation(DbTable.class).name());
    }

    public static String getIdAirplaneTypeAnnotationName() throws NoSuchFieldException {
        return AirplaneType.class.getDeclaredField("idAirplaneType").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getTypeNameAnnotationName() throws NoSuchFieldException {
        return AirplaneType.class.getDeclaredField("typeName").getAnnotation(DbColumnVarchar.class).name();
    }

    public static String getPeopleCapacityAnnotationName() throws NoSuchFieldException {
        return AirplaneType.class.getDeclaredField("peopleCapacity").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getCargoCapacityAnnotationName() throws NoSuchFieldException {
        return AirplaneType.class.getDeclaredField("cargoCapacity").getAnnotation(DbColumnNumber.class).name();
    }

    public Integer getIdAirplaneType() {
        return idAirplaneType;
    }

    public void setIdAirplaneType(Integer idAirplaneType) {
        this.idAirplaneType = idAirplaneType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getPeopleCapacity() {
        return peopleCapacity;
    }

    public void setPeopleCapacity(Integer peopleCapacity) {
        this.peopleCapacity = peopleCapacity;
    }

    public Float getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(Float cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    @Override
    public void saveValues(OracleDbProvider provider) throws IllegalAccessException, SQLException {
        AbstractComponent.saveTo(AirplaneType.class, this, provider, this.getTableName());
    }

    @Override
    public String[][] getAllRows(OracleDbProvider provider) throws SQLException, IllegalAccessException {
        return AbstractComponent.getAllFrom(AirplaneType.class, this, provider, this.getTableName());
    }

    @Override
    public void deleteRowByPrimaryKey(OracleDbProvider provider) throws NoSuchFieldException, SQLException {
        AbstractComponent.deleteFrom(this.getTableName(), provider,
                new HashMap<>() {{
                    put(AirplaneType.getIdAirplaneTypeAnnotationName(), String.valueOf(idAirplaneType));

                }});
    }

    @Override
    public void updateRow(OracleDbProvider provider) throws SQLException, IllegalAccessException, NoSuchFieldException {
        AbstractComponent.updateTo(AirplaneType.class, this, provider, this.getTableName(),
                new HashMap<>() {{
                    put(AirplaneType.getIdAirplaneTypeAnnotationName(), String.valueOf(idAirplaneType));
                }});
    }
}
