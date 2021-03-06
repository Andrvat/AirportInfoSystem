package controller;

import annotations.*;
import dbConnection.OracleDbProvider;
import entities.AbstractComponent;
import forms.RequestResultPackage;
import lombok.Builder;
import model.DbModel;
import view.utilities.TableColumnInfo;
import view.utilities.TableColumnRequestOption;
import view.utilities.TableRecordBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@Builder
public class ControllerManager {
    private final OracleDbProvider provider;

    private final DbModel model;

    public OracleDbProvider getProvider() {
        return provider;
    }

    public List<TableColumnInfo> getTableColumnInfos(String tableName, TableColumnRequestOption option) {
        Class<?> componentClass = this.model.getEntityClassByKey(tableName);
        List<TableColumnInfo> columnNames = new ArrayList<>();
        for (Field field : componentClass.getDeclaredFields()) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            if (annotations.length != 0) {
                Annotation annotation = annotations[0];
                if (annotation instanceof DbColumnNumber dbColumnNumber) {
                    if (field.getName().startsWith("id") && TableColumnRequestOption.INSERT.equals(option)) {
                        continue;
                    }
                    columnNames.add(TableColumnInfo.builder()
                            .name(dbColumnNumber.name())
                            .typeValue("number")
                            .build());
                } else if (annotation instanceof DbColumnVarchar dbColumnVarchar) {
                    columnNames.add(TableColumnInfo.builder()
                            .name(dbColumnVarchar.name())
                            .typeValue("varchar")
                            .build());
                } else if (annotation instanceof DbColumnBoolean dbColumnBoolean) {
                    columnNames.add(TableColumnInfo.builder()
                            .name(dbColumnBoolean.name())
                            .typeValue("boolean")
                            .build());
                } else if (annotation instanceof DbColumnDate dbColumnDate) {
                    columnNames.add(TableColumnInfo.builder()
                            .name(dbColumnDate.name())
                            .typeValue("date")
                            .build());
                }
            }
        }
        return columnNames;
    }

    public void insertValuesInto(String tableName, Map<String, String> keyValues) throws Exception {
        AbstractComponent component = (AbstractComponent) this.model.getEntityClassByKey(tableName)
                .getConstructor()
                .newInstance();
        TableRecordBuilder.buildByValues(component, keyValues);
        component.saveValues(this.provider);
        this.provider.commitChanges();
    }

    public void updateTableRecordById(String tableName, Map<String, String> keyValues) throws Exception {
        AbstractComponent component = (AbstractComponent) this.model.getEntityClassByKey(tableName)
                .getConstructor()
                .newInstance();
        TableRecordBuilder.buildByValues(component, keyValues);
        component.updateRow(this.provider);
        this.provider.commitChanges();
    }

    public void deleteRowByPrimaryKey(String tableName, Map<String, String> primaryKey) throws Exception {
        AbstractComponent component = (AbstractComponent) this.model.getEntityClassByKey(tableName)
                .getConstructor()
                .newInstance();
        TableRecordBuilder.buildByValues(component, primaryKey);
        component.deleteRowByPrimaryKey(this.provider);
        this.provider.commitChanges();
    }

    public String[] getTableNames() {
        HashMap<String, Class<?>> entitiesClasses = this.model.getEntitiesClasses();
        return entitiesClasses.keySet().toArray(new String[0]);
    }

    public String[] getPrettyViewingTableNames() {
        var names = this.model.getPrettyViewingTables();
        return names.toArray(new String[0]);
    }

    public String[][] getAllRowsValues(String tableName) throws Exception {
        AbstractComponent component = (AbstractComponent) this.model.getEntityClassByKey(tableName)
                .getConstructor()
                .newInstance();
        return component.getAllRows(this.provider);
    }

    public RequestResultPackage getPrettyViewingRowsValues(String tableName) throws Exception {
        AbstractComponent component = (AbstractComponent) this.model.getEntityClassByKey(tableName)
                .getConstructor()
                .newInstance();
        return component.getPrettyViewingResultPackage(this.provider);
    }

    public void registerNewAccount(String login, String password, String role) throws SQLException {
        Statement statement = this.provider.getCreatedStatement();
        statement.execute("INSERT INTO APPLICATION_ACCOUNT (LOGIN, PASSWORD, ROLE) VALUES ('" + login + "', '" + password + "', '" + role + "')");
        this.provider.commitChanges();
    }

    public void changeTicketStatus(Integer passengerId, Integer departureId, Integer seat, Integer actionType) throws SQLException {
        CallableStatement statement = this.provider.getCreatedCallableStatement("{CALL change_ticket_status_by_id(?, ?, ?, ?)}",
                new ArrayList<>(Arrays.asList(passengerId, departureId, seat, actionType)));
        statement.execute();
        statement.close();
        this.provider.commitChanges();
    }

    public void generateDepartures(Integer nextDays) throws SQLException {
        CallableStatement statement = this.provider.getCreatedCallableStatement("{CALL generate_departures(?)}", nextDays);
        statement.execute();
        statement.close();
        this.provider.commitChanges();
    }
}
