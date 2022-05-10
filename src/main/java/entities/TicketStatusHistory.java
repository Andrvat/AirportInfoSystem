package entities;

import annotations.DbColumnDate;
import annotations.DbColumnNumber;
import annotations.DbConstrains;
import annotations.DbTable;
import dbConnection.OracleDbProvider;
import model.support.TimeCalendar;

import java.sql.SQLException;
import java.util.HashMap;

@DbTable(name = "TICKET_STATUS_HISTORY")
public class TicketStatusHistory extends AbstractComponent {
    @DbColumnNumber(name = "record_id", constrains = @DbConstrains(isPrimaryKey = true, isAllowedNull = false))
    private Integer idRecord;

    @DbColumnNumber(name = "departure_id", constrains = @DbConstrains(isAllowedNull = false))
    private Integer departureId;

    @DbColumnNumber(name = "seat", constrains = @DbConstrains(isAllowedNull = false))
    private Integer seat;

    @DbColumnNumber(name = "ticket_status_id", constrains = @DbConstrains(isAllowedNull = false))
    private Integer ticketStatusId;

    @DbColumnDate(name = "status_set_date", constrains = @DbConstrains(isAllowedNull = false))
    private TimeCalendar statusSetDate;

    @DbColumnNumber(name = "passenger_id")
    private Integer passengerId;

    public TicketStatusHistory() {
        super(TicketStatusHistory.class.getAnnotation(DbTable.class).name());
    }

    public static String getIdRecordAnnotationName() throws NoSuchFieldException {
        return TicketStatusHistory.class.getDeclaredField("idRecord").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getDepartureIdAnnotationName() throws NoSuchFieldException {
        return TicketStatusHistory.class.getDeclaredField("departureId").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getSeatAnnotationName() throws NoSuchFieldException {
        return TicketStatusHistory.class.getDeclaredField("seat").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getTicketStatusIdAnnotationName() throws NoSuchFieldException {
        return TicketStatusHistory.class.getDeclaredField("ticketStatusId").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getStatusSetDateAnnotationName() throws NoSuchFieldException {
        return TicketStatusHistory.class.getDeclaredField("statusSetDate").getAnnotation(DbColumnDate.class).name();
    }

    public static String getPassengerIdAnnotationName() throws NoSuchFieldException {
        return TicketStatusHistory.class.getDeclaredField("passengerId").getAnnotation(DbColumnNumber.class).name();
    }

    public Integer getIdRecord() {
        return idRecord;
    }

    public void setIdRecord(Integer idRecord) {
        this.idRecord = idRecord;
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

    public TimeCalendar getStatusSetDate() {
        return statusSetDate;
    }

    public void setStatusSetDate(TimeCalendar statusSetDate) {
        this.statusSetDate = statusSetDate;
    }

    public Integer getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Integer passengerId) {
        this.passengerId = passengerId;
    }

    @Override
    public void saveValues(OracleDbProvider provider) throws IllegalAccessException, SQLException {
        AbstractComponent.saveTo(TicketStatusHistory.class, this, provider, this.getTableName());
    }

    @Override
    public String[][] getAllRows(OracleDbProvider provider) throws SQLException, IllegalAccessException {
        return AbstractComponent.getAllFrom(TicketStatusHistory.class, this, provider, this.getTableName());
    }

    @Override
    public void deleteRowByPrimaryKey(OracleDbProvider provider) throws NoSuchFieldException, SQLException {
        AbstractComponent.deleteFrom(this.getTableName(), provider,
                new HashMap<>() {{
                    put(TicketStatusHistory.getIdRecordAnnotationName(), String.valueOf(idRecord));
                }});
    }

    @Override
    public void updateRow(OracleDbProvider provider) throws SQLException, IllegalAccessException, NoSuchFieldException {
        AbstractComponent.updateTo(TicketStatusHistory.class, this, provider, this.getTableName(),
                new HashMap<>() {{
                    put(TicketStatusHistory.getIdRecordAnnotationName(), String.valueOf(idRecord));
                }});
    }
}
