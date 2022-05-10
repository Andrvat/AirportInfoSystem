package entities;

import annotations.*;
import dbConnection.OracleDbProvider;
import model.support.TimeCalendar;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public abstract void deleteRowByPrimaryKey(OracleDbProvider provider) throws NoSuchFieldException, SQLException;

    public abstract void updateRow(OracleDbProvider provider) throws SQLException, IllegalAccessException, NoSuchFieldException;

    public static <T> void saveTo(Class<T> classType, T instance, OracleDbProvider provider, String tableName) throws SQLException, IllegalAccessException {
        StringBuilder query = new StringBuilder()
                .append("INSERT INTO ")
                .append(tableName)
                .append(" (");
        for (Field field : classType.getDeclaredFields()) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            if (annotations.length != 0) {
                Annotation annotation = annotations[0];
                if (annotation instanceof DbColumnNumber columnNumber) {
                    if (field.getName().startsWith("id")) {
                        continue;
                    }
                    query.append(columnNumber.name());
                } else if (annotation instanceof DbColumnVarchar columnVarchar) {
                    query.append(columnVarchar.name());
                } else if (annotation instanceof DbColumnBoolean columnBoolean) {
                    query.append(columnBoolean.name());
                } else if (annotation instanceof DbColumnDate columnDate) {
                    query.append(columnDate.name());
                }
                query.append(", ");
            }
        }
        query.delete(query.length() - 2, query.length());
        query.append(") VALUES (");
        for (Field field : classType.getDeclaredFields()) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            if (annotations.length != 0) {
                field.setAccessible(true);
                Object value = field.get(instance);
                Annotation annotation = annotations[0];
                if (annotation instanceof DbColumnNumber) {
                    if (field.getName().startsWith("id")) {
                        continue;
                    }
                    query.append(value);
                } else if (annotation instanceof DbColumnVarchar) {
                    query.append("'").append(value).append("'");
                } else if (annotation instanceof DbColumnBoolean) {
                    query.append("'").append(((Boolean) value) ? "Y" : "N").append("'");
                } else if (annotation instanceof DbColumnDate) {
                    query.append(((TimeCalendar) value).toSqlStringDate());
                }
                query.append(", ");
            }
        }
        query.delete(query.length() - 2, query.length());
        query.append(")");

        Statement statement = provider.getCreatedStatement();
        statement.execute(query.toString());
    }


    public static <T> void updateTo(Class<T> classType, T instance, OracleDbProvider provider,
                                    String tableName, Map<String, String> primaryKey) throws SQLException, IllegalAccessException {
        StringBuilder query = new StringBuilder()
                .append("UPDATE ")
                .append(tableName)
                .append(" SET ");
        for (Field field : classType.getDeclaredFields()) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            if (annotations.length != 0) {
                field.setAccessible(true);
                Object value = field.get(instance);
                Annotation annotation = annotations[0];
                if (annotation instanceof DbColumnNumber dbColumnNumber) {
                    if (field.getName().startsWith("id")) {
                        continue;
                    }
                    query.append(dbColumnNumber.name())
                            .append(" = ")
                            .append(value);
                } else if (annotation instanceof DbColumnVarchar dbColumnVarchar) {
                    query.append(dbColumnVarchar.name())
                            .append(" = ")
                            .append("'")
                            .append(value)
                            .append("'");
                } else if (annotation instanceof DbColumnBoolean dbColumnBoolean) {
                    query.append(dbColumnBoolean.name())
                            .append(" = ")
                            .append("'")
                            .append(((Boolean) value) ? "Y" : "N")
                            .append("'");
                } else if (annotation instanceof DbColumnDate dbColumnDate) {
                    query.append(dbColumnDate.name())
                            .append(" = ")
                            .append(((TimeCalendar) value).toSqlStringDate());
                }
                query.append(", ");
            }
        }

        query.delete(query.length() - 2, query.length());
        query.append(" WHERE ");
        int keysNumber = 0;
        for (var entry : primaryKey.entrySet()) {
            query.append(entry.getKey())
                    .append(" = ")
                    .append(entry.getValue());
            keysNumber++;
            if (keysNumber != primaryKey.size()) {
                query.append(" AND ");
            }
        }
        Statement statement = provider.getCreatedStatement();
        statement.execute(query.toString());
    }

    public static <T> void deleteFrom(String tableName, OracleDbProvider provider,
                                      Map<String, String> primaryKey) throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM ")
                .append(tableName)
                .append(" WHERE ");
        int keysNumber = 0;
        for (var entry : primaryKey.entrySet()) {
            query.append(entry.getKey())
                    .append(" = ?");
            keysNumber++;
            if (keysNumber != primaryKey.size()) {
                query.append(" AND ");
            }
        }
        List<String> primaryKeyValues = new ArrayList<>(primaryKey.values().stream().toList());
        provider.getStringsQueryResultSet(query.toString(), primaryKeyValues);
    }
}
