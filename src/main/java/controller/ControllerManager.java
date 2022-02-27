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

    public List<String> getColumnsNamesByName(String tableName) {
        Class<?> componentClass = this.model.getEntityClassByKey(tableName);
        List<String> columnsNames = new ArrayList<>();
        for (Field field : componentClass.getDeclaredFields()) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            if (annotations.length == 0) {
                System.err.println("Field is not a db column");
            } else {
                Annotation annotation = annotations[0];
                if (annotation instanceof DbColumnNumber dbColumnNumber) {
                    columnsNames.add(dbColumnNumber.name());
                } else if (annotation instanceof DbColumnVarchar dbColumnVarchar) {
                    columnsNames.add(dbColumnVarchar.name());
                }
            }
        }
        return columnsNames;
    }

    public void insertValueIntoTable(String tableName, Map<String, String> namedValues) throws Exception {
        AbstractComponent component = (AbstractComponent) this.model.getEntityClassByKey(tableName)
                .getConstructor()
                .newInstance();
        if (component instanceof Ticket) {
            Ticket ticket = new Ticket();
            ticket.setIdTicket(Integer.valueOf(namedValues.get(Ticket.getIdTicketAnnotationName())));
            ticket.setFirstName(namedValues.get(Ticket.getFirstNameAnnotationName()));
            ticket.setLastName(namedValues.get(Ticket.getLastNameAnnotationName()));
            ticket.setPatronymic(namedValues.get(Ticket.getPatronymicAnnotationName()));
            ticket.setSeatNumber(Integer.valueOf(namedValues.get(Ticket.getSeatNumberAnnotationName())));
            ticket.saveValues(this.provider);
        }
    }

    public String[] getTableNames() {
        HashMap<String, Class<?>> entitiesClasses = this.model.getEntitiesClasses();
        return entitiesClasses.keySet().toArray(new String[0]);
    }

    public int getRowsNumberByName(String selectedTableName) throws Exception {
        AbstractComponent component = (AbstractComponent) this.model.getEntityClassByKey(selectedTableName)
                .getConstructor()
                .newInstance();
        return component.getRowsNumber(this.provider);
    }
}
