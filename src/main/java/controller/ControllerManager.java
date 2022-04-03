package controller;

import annotations.DbColumnBoolean;
import annotations.DbColumnDate;
import annotations.DbColumnNumber;
import annotations.DbColumnVarchar;
import dbConnection.OracleDbProvider;
import entities.AbstractComponent;
import entities.Passenger;
import entities.Ticket;
import lombok.Builder;
import model.DbModel;
import view.utilities.TableColumnInfo;
import view.utilities.TableColumnRequestOption;
import view.utilities.TableRecordBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
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

    public void insertValueIntoTable(String tableName, Map<String, String> keyValues) throws Exception {
        AbstractComponent component = (AbstractComponent) this.model.getEntityClassByKey(tableName)
                .getConstructor()
                .newInstance();
        if (component instanceof Ticket) {
            Ticket ticket = TableRecordBuilder.buildTicket(keyValues);
            ticket.saveValues(this.provider);
        } else if (component instanceof Passenger) {
            Passenger passenger = TableRecordBuilder.buildPassenger(keyValues);
            passenger.saveValues(this.provider);
        }
    }

    public void updateTableRecordById(String tableName, Map<String, String> keyValues) throws Exception {
        AbstractComponent component = (AbstractComponent) this.model.getEntityClassByKey(tableName)
                .getConstructor()
                .newInstance();
        if (component instanceof Ticket) {
            Ticket ticket = TableRecordBuilder.buildTicket(keyValues);
            ticket.setIdTicket(Integer.valueOf(keyValues.get(Ticket.getIdTicketAnnotationName())));
            ticket.updateRow(this.provider);
        } else if (component instanceof Passenger) {
            Passenger passenger = TableRecordBuilder.buildPassenger(keyValues);
            passenger.setIdPassenger(Integer.valueOf(keyValues.get(Passenger.getIdPassengerAnnotationName())));
            passenger.updateRow(this.provider);
        }
    }

    public void deleteTableRowById(String tableName, String idValue) throws Exception {
        AbstractComponent component = (AbstractComponent) this.model.getEntityClassByKey(tableName)
                .getConstructor()
                .newInstance();
        if (component instanceof Ticket) {
            Ticket ticket = new Ticket();
            ticket.setIdTicket(Integer.valueOf(idValue));

            ticket.deleteRowById(this.provider);
        } else if (component instanceof Passenger) {
            Passenger passenger = new Passenger();
            passenger.setIdPassenger(Integer.valueOf(idValue));

            passenger.deleteRowById(this.provider);
        }
    }

    public String[] getTableNames() {
        HashMap<String, Class<?>> entitiesClasses = this.model.getEntitiesClasses();
        return entitiesClasses.keySet().toArray(new String[0]);
    }

    public int getTableRowsNumber(String tableName) throws Exception {
        AbstractComponent component = (AbstractComponent) this.model.getEntityClassByKey(tableName)
                .getConstructor()
                .newInstance();
        return component.getRowsNumber(this.provider);
    }

    public String[][] getAllRowsValues(String tableName) throws Exception {
        AbstractComponent component = (AbstractComponent) this.model.getEntityClassByKey(tableName)
                .getConstructor()
                .newInstance();
        if (component instanceof Ticket ticket) {
            return ticket.getAllRows(this.provider);
        } else if (component instanceof Passenger passenger) {
            return passenger.getAllRows(this.provider);
        }
        return null;
    }
}
