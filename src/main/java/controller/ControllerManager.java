package controller;

import dbConnection.OracleDbProvider;
import entities.AbstractComponent;
import lombok.Builder;
import model.DbModel;

import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@Builder
public class ControllerManager {

    private final OracleDbProvider provider;

    private final DbModel model;

//    public void initRequiredTables(String propertiesPath) throws IOException, SQLException {
//        Properties scriptProperties = new Properties();
//        scriptProperties.load(new FileInputStream(propertiesPath));
//        for (String key : scriptProperties.stringPropertyNames()) {
//            try {
//                this.getRowsNumberByName(key);
//            } catch (SQLException exception) {
//                String value = scriptProperties.getProperty(key);
//                Statement statement = this.provider.getCreatedStatement();
//                statement.execute(value);
//            }
//        }
//    }

    public List<AbstractMap.SimpleEntry<String, String>> getColumnsInfoByName(String tableName) throws SQLException {
        String query = """
                SELECT column_name AS name, data_type AS type
                FROM USER_TAB_COLUMNS
                WHERE table_name =\040""" + "'" + tableName.toUpperCase(Locale.ROOT) + "'";
        ResultSet resultSet = this.provider.getStringsQueryResultSet(query, Collections.emptyList());
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

        Statement statement = this.provider.getCreatedStatement();
        statement.execute(query.toString());
    }

    public String[] getTableNames() {
        HashMap<String, Class> entitiesClasses = this.model.getEntitiesClasses();
        return entitiesClasses.keySet().toArray(new String[0]);
    }

    public int getRowsNumberByName(String selectedTableName) throws Exception {
        AbstractComponent component = (AbstractComponent) this.model.getEntityClassByKey(selectedTableName)
                .getConstructor()
                .newInstance();
        return component.getRowsNumber(this.provider);
    }
}
