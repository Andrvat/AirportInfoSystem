package controller;

import annotations.DbColumnNumber;
import annotations.DbColumnVarchar;
import dbConnection.OracleDbProvider;
import entities.AbstractComponent;
import entities.Ticket;
import lombok.Builder;
import model.DbModel;

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

    public List<String> getTableColumnNames(String tableName) {
        Class<?> componentClass = this.model.getEntityClassByKey(tableName);
        List<String> columnNames = new ArrayList<>();
        for (Field field : componentClass.getDeclaredFields()) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            if (annotations.length != 0) {
                Annotation annotation = annotations[0];
                if (annotation instanceof DbColumnNumber dbColumnNumber) {
                    columnNames.add(dbColumnNumber.name());
                } else if (annotation instanceof DbColumnVarchar dbColumnVarchar) {
                    columnNames.add(dbColumnVarchar.name());
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
            Ticket ticket = new Ticket();
            ticket.setIdTicket(Integer.valueOf(keyValues.get(Ticket.getIdTicketAnnotationName())));
            ticket.setFirstName(keyValues.get(Ticket.getFirstNameAnnotationName()));
            ticket.setLastName(keyValues.get(Ticket.getLastNameAnnotationName()));
            ticket.setPatronymic(keyValues.get(Ticket.getPatronymicAnnotationName()));
            ticket.setSeatNumber(Integer.valueOf(keyValues.get(Ticket.getSeatNumberAnnotationName())));

            ticket.saveValues(this.provider);
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
}
