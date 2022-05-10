package entities;

import annotations.DbColumnNumber;
import annotations.DbConstrains;
import annotations.DbTable;
import dbConnection.OracleDbProvider;

import java.sql.SQLException;
import java.util.HashMap;

@DbTable(name = "DISPATCHER")
public class Dispatcher extends AbstractComponent {
    @DbColumnNumber(name = "dispatcher_id", constrains = @DbConstrains(isPrimaryKey = true, isAllowedNull = false))
    private Integer idDispatcher;

    @DbColumnNumber(name = "dispatcher_work_direction_id", constrains = @DbConstrains(isAllowedNull = false))
    private Integer dispatcherWorkDirectionId;

    public Dispatcher() {
        super(Dispatcher.class.getAnnotation(DbTable.class).name());
    }

    public static String getIdDispatcherAnnotationName() throws NoSuchFieldException {
        return Dispatcher.class.getDeclaredField("idDispatcher").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getDispatcherWorkDirectionIdAnnotationName() throws NoSuchFieldException {
        return Dispatcher.class.getDeclaredField("dispatcherWorkDirectionId").getAnnotation(DbColumnNumber.class).name();
    }

    public Integer getIdDispatcher() {
        return idDispatcher;
    }

    public void setIdDispatcher(Integer idDispatcher) {
        this.idDispatcher = idDispatcher;
    }

    public Integer getDispatcherWorkDirectionId() {
        return dispatcherWorkDirectionId;
    }

    public void setDispatcherWorkDirectionId(Integer dispatcherWorkDirectionId) {
        this.dispatcherWorkDirectionId = dispatcherWorkDirectionId;
    }

    @Override
    public void saveValues(OracleDbProvider provider) throws IllegalAccessException, SQLException {
        AbstractComponent.saveTo(Dispatcher.class, this, provider, this.getTableName());
    }

    @Override
    public String[][] getAllRows(OracleDbProvider provider) throws SQLException, IllegalAccessException {
        return AbstractComponent.getAllFrom(Dispatcher.class, this, provider, this.getTableName());
    }

    @Override
    public void deleteRowByPrimaryKey(OracleDbProvider provider) throws NoSuchFieldException, SQLException {
        AbstractComponent.deleteFrom(this.getTableName(), provider,
                new HashMap<>() {{
                    put(Dispatcher.getIdDispatcherAnnotationName(), String.valueOf(idDispatcher));

                }});
    }

    @Override
    public void updateRow(OracleDbProvider provider) throws SQLException, IllegalAccessException, NoSuchFieldException {
        AbstractComponent.updateTo(Dispatcher.class, this, provider, this.getTableName(),
                new HashMap<>() {{
                    put(Dispatcher.getIdDispatcherAnnotationName(), String.valueOf(idDispatcher));
                }});
    }
}
