package entities;

import annotations.DbColumnNumber;
import annotations.DbConstrains;
import annotations.DbTable;
import dbConnection.OracleDbProvider;

import java.sql.SQLException;
import java.util.HashMap;

@DbTable(name = "OPERATOR")
public class Operator extends AbstractComponent {
    @DbColumnNumber(name = "operator_Id", constrains = @DbConstrains(isPrimaryKey = true, isAllowedNull = false))
    private Integer idOperator;

    @DbColumnNumber(name = "operator_work_direction_id", constrains = @DbConstrains(isAllowedNull = false))
    private Integer operatorWorkDirectionId;

    public Operator() {
        super(Operator.class.getAnnotation(DbTable.class).name());
    }

    public static String getIdOperatorAnnotationName() throws NoSuchFieldException {
        return Operator.class.getDeclaredField("idOperator").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getOperatorWorkDirectionIdIdAnnotationName() throws NoSuchFieldException {
        return Operator.class.getDeclaredField("operatorWorkDirectionId").getAnnotation(DbColumnNumber.class).name();
    }

    public Integer getIdOperator() {
        return idOperator;
    }

    public void setIdOperator(Integer idOperator) {
        this.idOperator = idOperator;
    }

    public Integer getOperatorWorkDirectionId() {
        return operatorWorkDirectionId;
    }

    public void setOperatorWorkDirectionId(Integer operatorWorkDirectionId) {
        this.operatorWorkDirectionId = operatorWorkDirectionId;
    }

    @Override
    public void saveValues(OracleDbProvider provider) throws IllegalAccessException, SQLException {
        AbstractComponent.saveTo(Operator.class, this, provider, this.getTableName());
    }

    @Override
    public String[][] getAllRows(OracleDbProvider provider) throws SQLException, IllegalAccessException {
        return AbstractComponent.getAllFrom(Operator.class, this, provider, this.getTableName());
    }

    @Override
    public void deleteRowByPrimaryKey(OracleDbProvider provider) throws NoSuchFieldException, SQLException {
        AbstractComponent.deleteFrom(this.getTableName(), provider,
                new HashMap<>() {{
                    put(Operator.getIdOperatorAnnotationName(), String.valueOf(idOperator));

                }});
    }

    @Override
    public void updateRow(OracleDbProvider provider) throws SQLException, IllegalAccessException, NoSuchFieldException {
        AbstractComponent.updateTo(Operator.class, this, provider, this.getTableName(),
                new HashMap<>() {{
                    put(Operator.getIdOperatorAnnotationName(), String.valueOf(idOperator));
                }});
    }
}
