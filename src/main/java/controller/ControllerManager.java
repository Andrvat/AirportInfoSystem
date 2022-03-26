package controller;

import annotations.DbColumnBoolean;
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
        } else if (component instanceof Passenger) {
            Passenger passenger = new Passenger();
            passenger.setSurname(keyValues.get(Passenger.getSurnameAnnotationName()));
            passenger.setName(keyValues.get(Passenger.getPassengerNameAnnotationName()));
            passenger.setPatronymic(keyValues.get(Passenger.getPatronymicAnnotationName()));
            passenger.setPassportNumber(Integer.valueOf(keyValues.get(Passenger.getPassportAnnotationName())));
            passenger.setInternationalPassportNumber(Integer.valueOf(keyValues.get(Passenger.getInternationalPassportAnnotationName())));
            passenger.setCustomControlPassed("Y".equals(keyValues.get(Passenger.getCustomControlAnnotationName())));

            passenger.saveValues(this.provider);
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
