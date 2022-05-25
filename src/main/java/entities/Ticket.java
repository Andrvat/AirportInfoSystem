package entities;

import annotations.DbColumnNumber;
import annotations.DbColumnVarchar;
import annotations.DbConstrains;
import annotations.DbTable;
import dbConnection.OracleDbProvider;
import forms.RequestResultPackage;

import javax.naming.spi.DirStateFactory;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@DbTable(name = "TICKET", pkNumber = 2)
public class Ticket extends AbstractComponent {
    @DbColumnNumber(name = "departure_id", constrains = @DbConstrains(isPrimaryKey = true, isAllowedNull = false))
    private Integer departureId;

    @DbColumnNumber(name = "seat", constrains = @DbConstrains(isPrimaryKey = true, isAllowedNull = false))
    private Integer seat;

    @DbColumnNumber(name = "ticket_status_id", constrains = @DbConstrains(isAllowedNull = false))
    private Integer ticketStatusId;

    @DbColumnNumber(name = "bag_max_capacity")
    private Integer bagMaxCapacity;

    public Ticket() {
        super(Ticket.class.getAnnotation(DbTable.class).name());
    }

    public Integer getDepartureId() {
        return departureId;
    }

    public void setDepartureId(Integer departureId) {
        this.departureId = departureId;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    public Integer getTicketStatusId() {
        return ticketStatusId;
    }

    public void setTicketStatusId(Integer ticketStatusId) {
        this.ticketStatusId = ticketStatusId;
    }

    public Integer getBagMaxCapacity() {
        return bagMaxCapacity;
    }

    public void setBagMaxCapacity(Integer bagMaxCapacity) {
        this.bagMaxCapacity = bagMaxCapacity;
    }

    public static String getDepartureIdAnnotationName() throws NoSuchFieldException {
        return Ticket.class.getDeclaredField("departureId").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getSeatAnnotationName() throws NoSuchFieldException {
        return Ticket.class.getDeclaredField("seat").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getTicketStatusIdAnnotationName() throws NoSuchFieldException {
        return Ticket.class.getDeclaredField("ticketStatusId").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getBagMaxCapacityAnnotationName() throws NoSuchFieldException {
        return Ticket.class.getDeclaredField("bagMaxCapacity").getAnnotation(DbColumnNumber.class).name();
    }


    @Override
    public void saveValues(OracleDbProvider provider) throws IllegalAccessException, SQLException {
        AbstractComponent.saveTo(Ticket.class, this, provider, this.getTableName());
    }

    @Override
    public String[][] getAllRows(OracleDbProvider provider) throws SQLException, IllegalAccessException {
        return AbstractComponent.getAllFrom(Ticket.class, this, provider, this.getTableName());
    }

    @Override
    public void deleteRowByPrimaryKey(OracleDbProvider provider) throws NoSuchFieldException, SQLException {
        AbstractComponent.deleteFrom(this.getTableName(), provider,
                new HashMap<>() {{
                    put(Ticket.getDepartureIdAnnotationName(), String.valueOf(departureId));
                    put(Ticket.getSeatAnnotationName(), String.valueOf(seat));
                }});
    }

    @Override
    public void updateRow(OracleDbProvider provider) throws SQLException, IllegalAccessException, NoSuchFieldException {
        AbstractComponent.updateTo(Ticket.class, this, provider, this.getTableName(),
                new HashMap<>() {{
                    put(Ticket.getDepartureIdAnnotationName(), String.valueOf(departureId));
                    put(Ticket.getSeatAnnotationName(), String.valueOf(seat));
                }});
    }

    @Override
    public RequestResultPackage getPrettyViewingResultPackage(OracleDbProvider provider) throws SQLException {
        var resultPackage = new RequestResultPackage();
        resultPackage.setTableName(this.getTableName());
        var resultSet = provider.getStringsQueryResultSet(
                "SELECT departure_id, seat, description, bag_max_capacity " +
                        "FROM TICKET " +
                        "LEFT JOIN TICKET_STATUS USING (ticket_status_id) " +
                        "ORDER BY departure_id, seat",
                Collections.emptyList());
        resultPackage.setColumnNames(new String[]{"Номер вылета", "Место", "Статус", "Макс. вместимость багажа"});
        List<String[]> allRows = new ArrayList<>();
        while (resultSet.next()) {
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(resultSet.getInt(1)));
            row.add(String.valueOf(resultSet.getInt(2)));
            row.add(resultSet.getString(3));
            row.add(String.valueOf(resultSet.getInt(4)));
            allRows.add(row.toArray(new String[0]));
        }
        resultPackage.setResultRows(allRows.toArray(new String[0][]));
        return resultPackage;
    }
}
