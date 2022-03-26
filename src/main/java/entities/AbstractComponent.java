package entities;

import dbConnection.OracleDbProvider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;

public abstract class AbstractComponent {
    private final String tableName;

    public AbstractComponent(String tableName) {
        this.tableName = tableName;
    }

    public int getRowsNumber(OracleDbProvider provider) throws SQLException {
        String query = "SELECT COUNT(*) AS amount FROM " + this.tableName;
        ResultSet resultSet = provider.getStringsQueryResultSet(query, Collections.emptyList());
        resultSet.next();
        return resultSet.getInt(1);
    }

    public String getTableName() {
        return tableName;
    }

    public abstract void saveValues(OracleDbProvider provider) throws IllegalAccessException, SQLException;

    public abstract String[][] getAllRows(OracleDbProvider provider) throws SQLException;

    public abstract void deleteRowById(OracleDbProvider provider) throws NoSuchFieldException, SQLException;
}
