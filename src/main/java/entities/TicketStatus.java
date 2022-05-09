package entities;

import annotations.DbColumnNumber;
import annotations.DbColumnVarchar;
import annotations.DbConstrains;
import annotations.DbTable;
import dbConnection.OracleDbProvider;

import java.sql.SQLException;
import java.util.HashMap;

@DbTable(name = "TICKET_STATUS")
public class TicketStatus extends AbstractComponent {
    @DbColumnNumber(name = "ticket_status_id", constrains = @DbConstrains(isPrimaryKey = true, isAllowedNull = false))
    private Integer idTicketStatus;

    @DbColumnVarchar(name = "description", value = 255, constrains = @DbConstrains(isAllowedNull = false, isUnique = true))
    private String description;

    public TicketStatus() {
        super(TicketStatus.class.getAnnotation(DbTable.class).name());
    }

    public static String getIdTicketStatusAnnotationName() throws NoSuchFieldException {
        return TicketStatus.class.getDeclaredField("idTicketStatus").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getDescriptionAnnotationName() throws NoSuchFieldException {
        return TicketStatus.class.getDeclaredField("description").getAnnotation(DbColumnVarchar.class).name();
    }

    public Integer getIdTicketStatus() {
        return idTicketStatus;
    }

    public void setIdTicketStatus(Integer idTicketStatus) {
        this.idTicketStatus = idTicketStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void saveValues(OracleDbProvider provider) throws IllegalAccessException, SQLException {
        AbstractComponent.saveTo(TicketStatus.class, this, provider, this.getTableName());
    }

    @Override
    public String[][] getAllRows(OracleDbProvider provider) throws SQLException {
        return new String[0][];
    }

    @Override
    public void deleteRowByPrimaryKey(OracleDbProvider provider) throws NoSuchFieldException, SQLException {

    }

    @Override
    public void updateRow(OracleDbProvider provider) throws SQLException, IllegalAccessException, NoSuchFieldException {
        AbstractComponent.updateTo(TicketStatus.class, this, provider, this.getTableName(),
                new HashMap<>() {{
                    put(TicketStatus.getIdTicketStatusAnnotationName(), String.valueOf(idTicketStatus));
                }});
    }
}
