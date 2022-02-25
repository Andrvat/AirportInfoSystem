package controller;

import dbConnection.OracleDbConnector;
import lombok.Builder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Builder
public class ControllerManager {

    private final OracleDbConnector connector;

    public int getRowsNumberByName(String tableName) throws SQLException {
        String query = "SELECT COUNT(*) AS amount FROM " + tableName;
        ResultSet resultSet = this.connector.getStringsQueryResultSet(query, Collections.emptyList());
        resultSet.next();
        return resultSet.getInt(1);

    }

    public List<AbstractMap.SimpleEntry<String, String>> getColumnsInfoByName(String tableName) throws SQLException {
        String query = """
                SELECT column_name AS name, data_type AS type
                FROM USER_TAB_COLUMNS
                WHERE table_name =\040""" + tableName;
        ResultSet resultSet = this.connector.getStringsQueryResultSet(query, Collections.emptyList());
        List<AbstractMap.SimpleEntry<String, String>> columnsInfo = new ArrayList<>();
        while (resultSet.next()) {
            columnsInfo.add(new AbstractMap.SimpleEntry<>(
                    resultSet.getString("name"),
                    resultSet.getString("type")));
        }
        return columnsInfo;
    }
}
