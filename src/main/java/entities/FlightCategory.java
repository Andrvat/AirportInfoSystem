package entities;

import annotations.DbColumnNumber;
import annotations.DbColumnVarchar;
import annotations.DbConstrains;
import annotations.DbTable;
import dbConnection.OracleDbProvider;

import java.sql.SQLException;

@DbTable(name = "FLIGHT_CATEGORY")
public class FlightCategory extends AbstractComponent {
    @DbColumnNumber(name = "flight_category_id", constrains = @DbConstrains(isPrimaryKey = true, isAllowedNull = false))
    private Integer idFlightCategory;

    @DbColumnVarchar(name = "category_name", value = 255, constrains = @DbConstrains(isAllowedNull = false, isUnique = true))
    private String categoryName;

    public FlightCategory() {
        super(FlightCategory.class.getAnnotation(DbTable.class).name());
    }

    public static String getIdFlightCategoryAnnotationName() throws NoSuchFieldException {
        return FlightCategory.class.getDeclaredField("idFlightCategory").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getCategoryNameAnnotationName() throws NoSuchFieldException {
        return FlightCategory.class.getDeclaredField("categoryName").getAnnotation(DbColumnVarchar.class).name();
    }

    public Integer getIdFlightCategory() {
        return idFlightCategory;
    }

    public void setIdFlightCategory(Integer idFlightCategory) {
        this.idFlightCategory = idFlightCategory;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
