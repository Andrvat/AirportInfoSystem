package entities;

import annotations.DbColumnNumber;
import annotations.DbColumnVarchar;
import annotations.DbConstrains;
import annotations.DbTable;
import dbConnection.OracleDbProvider;

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
    public String[][] getAllRows(OracleDbProvider provider) throws SQLException {
        String query = "SELECT * FROM " + Ticket.class.getAnnotation(DbTable.class).name();
        ResultSet resultSet = provider.getStringsQueryResultSet(query, Collections.emptyList());
        List<String[]> allRows = new ArrayList<>();
        while (resultSet.next()) {
            this.departureId = resultSet.getInt(1);
            this.seat = resultSet.getInt(2);
            this.ticketStatusId = resultSet.getInt(3);
            this.bagMaxCapacity = resultSet.getInt(4);
            List<String> row = new ArrayList<>() {{
                add(String.valueOf(departureId));
                add(String.valueOf(seat));
                add(String.valueOf(ticketStatusId));
                add(String.valueOf(bagMaxCapacity));
            }};
            allRows.add(row.toArray(new String[0]));
        }
        return allRows.toArray(new String[0][]);
    }

    @Override
    public void deleteRowByPrimaryKey(OracleDbProvider provider) throws NoSuchFieldException, SQLException {
        String query =
                "DELETE FROM " + Ticket.class.getAnnotation(DbTable.class).name()
                        + " WHERE " + Ticket.getDepartureIdAnnotationName() + " = ?" +
                        " AND " +
                        Ticket.getSeatAnnotationName() + " = ?";
        provider.getStringsQueryResultSet(query, new ArrayList<>(Arrays.asList(
                String.valueOf(this.departureId),
                String.valueOf(this.seat)
        )));
    }

    @Override
    public void updateRow(OracleDbProvider provider) throws SQLException, IllegalAccessException, NoSuchFieldException {
        AbstractComponent.updateTo(Ticket.class, this, provider, this.getTableName(),
                new HashMap<>() {{
                    put(Ticket.getDepartureIdAnnotationName(), String.valueOf(departureId));
                    put(Ticket.getSeatAnnotationName(), String.valueOf(seat));
                }});
    }
}
