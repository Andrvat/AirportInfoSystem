package entities;

import annotations.DbColumnNumber;
import annotations.DbConstrains;
import annotations.DbTable;
import dbConnection.OracleDbProvider;

import java.sql.SQLException;
import java.util.HashMap;

@DbTable(name = "CASHIER")
public class Cashier extends AbstractComponent {
    @DbColumnNumber(name = "cashier_id", constrains = @DbConstrains(isPrimaryKey = true, isAllowedNull = false))
    private Integer idCashier;

    @DbColumnNumber(name = "cashier_work_direction_id", constrains = @DbConstrains(isAllowedNull = false))
    private Integer cashierWorkDirectionId;

    public Cashier() {
        super(Cashier.class.getAnnotation(DbTable.class).name());
    }

    public static String getIdCashierAnnotationName() throws NoSuchFieldException {
        return Cashier.class.getDeclaredField("idCashier").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getCashierWorkDirectionIdAnnotationName() throws NoSuchFieldException {
        return Cashier.class.getDeclaredField("cashierWorkDirectionId").getAnnotation(DbColumnNumber.class).name();
    }

    public Integer getIdCashier() {
        return idCashier;
    }

    public void setIdCashier(Integer idCashier) {
        this.idCashier = idCashier;
    }

    public Integer getCashierWorkDirectionId() {
        return cashierWorkDirectionId;
    }

    public void setCashierWorkDirectionId(Integer cashierWorkDirectionId) {
        this.cashierWorkDirectionId = cashierWorkDirectionId;
    }

    @Override
    public void saveValues(OracleDbProvider provider) throws IllegalAccessException, SQLException {
        AbstractComponent.saveTo(Cashier.class, this, provider, this.getTableName());
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
        AbstractComponent.updateTo(Cashier.class, this, provider, this.getTableName(),
                new HashMap<>() {{
                    put(Cashier.getIdCashierAnnotationName(), String.valueOf(idCashier));
                }});
    }
}
