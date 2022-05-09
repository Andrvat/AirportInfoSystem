package entities;

import annotations.DbColumnNumber;
import annotations.DbConstrains;
import annotations.DbTable;
import dbConnection.OracleDbProvider;

import java.sql.SQLException;

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