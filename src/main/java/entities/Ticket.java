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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@DbTable(name = "TICKET")
public class Ticket extends AbstractComponent {
    @DbColumnNumber(name = "ID_TICKET", constrains = @DbConstrains(isPrimaryKey = true, isAllowedNull = false))
    private Integer idTicket;

    @DbColumnVarchar(name = "FIRST_NAME", value = 50, constrains = @DbConstrains(isAllowedNull = false))
    private String firstName;

    @DbColumnVarchar(name = "LAST_NAME", value = 50, constrains = @DbConstrains(isAllowedNull = false))
    private String lastName;

    @DbColumnVarchar(name = "PATRONYMIC", value = 50, constrains = @DbConstrains(isAllowedNull = false))
    private String patronymic;

    @DbColumnNumber(name = "SEAT_NUMBER", constrains = @DbConstrains(isAllowedNull = false))
    private Integer seatNumber;

    public Ticket() {
        super(Ticket.class.getAnnotation(DbTable.class).name());
    }

    public Integer getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public static String getIdTicketAnnotationName() throws NoSuchFieldException {
        return Ticket.class.getDeclaredField("idTicket").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getFirstNameAnnotationName() throws NoSuchFieldException {
        return Ticket.class.getDeclaredField("firstName").getAnnotation(DbColumnVarchar.class).name();
    }

    public static String getLastNameAnnotationName() throws NoSuchFieldException {
        return Ticket.class.getDeclaredField("lastName").getAnnotation(DbColumnVarchar.class).name();
    }

    public static String getPatronymicAnnotationName() throws NoSuchFieldException {
        return Ticket.class.getDeclaredField("patronymic").getAnnotation(DbColumnVarchar.class).name();
    }

    public static String getSeatNumberAnnotationName() throws NoSuchFieldException {
        return Ticket.class.getDeclaredField("seatNumber").getAnnotation(DbColumnNumber.class).name();
    }

    @Override
    public void saveValues(OracleDbProvider provider) throws IllegalAccessException, SQLException {
        StringBuilder query = new StringBuilder()
                .append("INSERT INTO ")
                .append(this.getTableName())
                .append(" (");
        for (Field field : Ticket.class.getDeclaredFields()) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            if (annotations.length != 0) {
                Annotation annotation = annotations[0];
                if (annotation instanceof DbColumnNumber columnNumber) {
                    query.append(columnNumber.name());
                } else if (annotation instanceof DbColumnVarchar columnVarchar) {
                    query.append(columnVarchar.name());
                }
                query.append(", ");
            }
        }
        query.delete(query.length() - 2, query.length());
        query.append(") VALUES (");
        for (Field field : Ticket.class.getDeclaredFields()) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            if (annotations.length != 0) {
                field.setAccessible(true);
                Object value = field.get(this);
                Annotation annotation = annotations[0];
                if (annotation instanceof DbColumnNumber) {
                    query.append(value);
                } else if (annotation instanceof DbColumnVarchar) {
                    query.append("'").append(value).append("'");
                }
                query.append(", ");
            }
        }
        query.delete(query.length() - 2, query.length());
        query.append(")");

        Statement statement = provider.getCreatedStatement();
        statement.execute(query.toString());
    }

    @Override
    public String[][] getAllRows(OracleDbProvider provider) throws SQLException {
        String query = "SELECT * FROM " + Ticket.class.getAnnotation(DbTable.class).name();
        ResultSet resultSet = provider.getStringsQueryResultSet(query, Collections.emptyList());
        List<String[]> allRows = new ArrayList<>();
        while (resultSet.next()) {
            this.idTicket = resultSet.getInt(1);
            this.firstName = resultSet.getString(2);
            this.lastName = resultSet.getString(3);
            this.patronymic = resultSet.getString(4);
            this.seatNumber = resultSet.getInt(5);
            List<String> row = new ArrayList<>() {{
                add(String.valueOf(idTicket));
                add(firstName);
                add(lastName);
                add(patronymic);
                add(String.valueOf(seatNumber));
            }};
            allRows.add(row.toArray(new String[0]));
        }
        return allRows.toArray(new String[0][]);
    }

    @Override
    public void deleteRowById(OracleDbProvider provider) throws NoSuchFieldException, SQLException {
        String query = "DELETE FROM " + Ticket.class.getAnnotation(DbTable.class).name()
                + " WHERE " + Ticket.getIdTicketAnnotationName() + " = ?";
        provider.getStringsQueryResultSet(query, Collections.singletonList(String.valueOf(this.idTicket)));
    }
}
