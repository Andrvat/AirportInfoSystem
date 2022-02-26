package controller;

import dbConnection.OracleDbProvider;
import lombok.Builder;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@Builder
public class ControllerManager {

    private final OracleDbProvider connector;

    public void initRequiredTables(String propertiesPath) throws IOException, SQLException {
        Properties scriptProperties = new Properties();
        scriptProperties.load(new FileInputStream(propertiesPath));
        for (String key : scriptProperties.stringPropertyNames()) {
            try {
                this.getRowsNumberByName(key);
            } catch (SQLException exception) {
                String value = scriptProperties.getProperty(key);
                Statement statement = this.connector.getCreatedStatement();
                statement.execute(value);
            }
        }
    }

    public int getRowsNumberByName(String tableName) throws SQLException {
        String query = "SELECT COUNT(*) AS amount FROM " + tableName.toUpperCase(Locale.ROOT);
        ResultSet resultSet = this.connector.getStringsQueryResultSet(query, Collections.emptyList());
        resultSet.next();
        return resultSet.getInt(1);

    }

    public List<AbstractMap.SimpleEntry<String, String>> getColumnsInfoByName(String tableName) throws SQLException {
        String query = """
                SELECT column_name AS name, data_type AS type
                FROM USER_TAB_COLUMNS
                WHERE table_name =\040""" + "'" + tableName.toUpperCase(Locale.ROOT) + "'";
        ResultSet resultSet = this.connector.getStringsQueryResultSet(query, Collections.emptyList());
        List<AbstractMap.SimpleEntry<String, String>> columnsNames = new ArrayList<>();
        if (!resultSet.next()) {
            throw new SQLDataException("Invalid table name");
        } else {
            do {
                columnsNames.add(new AbstractMap.SimpleEntry<>(
                        resultSet.getString("name"),
                        resultSet.getString("type")
                ));
            } while (resultSet.next());
        }
        return columnsNames;
    }

    public void insertValuesIntoTable(String tableName, Map<String, String> namedValues) throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ").append(tableName).append(" (");
        for (Map.Entry<String, String> entry : namedValues.entrySet()) {
            query.append(entry.getKey()).append(", ");
        }
        query.delete(query.length() - 2, query.length());
        query.append(") VALUES (");
        for (Map.Entry<String, String> entry : namedValues.entrySet()) {
            query.append(entry.getValue()).append(", ");
        }
        query.delete(query.length() - 2, query.length());
        query.append(")");

        Statement statement = this.connector.getCreatedStatement();
        statement.execute(query.toString());
    }
}
