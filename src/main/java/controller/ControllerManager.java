package controller;

import dbConnection.OracleDbConnector;
import lombok.Builder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;

@Builder
public class ControllerManager {

    private final OracleDbConnector connector;

    public int getRowsNumberByName(String tableName) throws SQLException {
        String query = "SELECT COUNT(*) AS amount FROM " + tableName;
        ResultSet resultSet = this.connector.getStringsQueryResultSet(query, Collections.emptyList());
        resultSet.next();
        return resultSet.getInt(1);

    }
}
