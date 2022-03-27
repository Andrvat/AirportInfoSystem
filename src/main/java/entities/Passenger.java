package entities;

import annotations.*;
import dbConnection.OracleDbProvider;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@DbTable(name = "PASSENGER")
public class Passenger extends AbstractComponent {
    @DbColumnNumber(name = "id", constrains = @DbConstrains(isPrimaryKey = true, isAllowedNull = false))
    private Integer idPassenger;

    @DbColumnVarchar(name = "surname", value = 255, constrains = @DbConstrains(isAllowedNull = false))
    private String surname;

    @DbColumnVarchar(name = "name", value = 255, constrains = @DbConstrains(isAllowedNull = false))
    private String name;

    @DbColumnVarchar(name = "patronymic", value = 255, constrains = @DbConstrains(isAllowedNull = false))
    private String patronymic;

    @DbColumnNumber(name = "passport", constrains = @DbConstrains(isAllowedNull = false, isUnique = true))
    private Integer passportNumber;

    @DbColumnNumber(name = "international_passport", constrains = @DbConstrains(isUnique = true))
    private Integer internationalPassportNumber;

    @DbColumnBoolean(name = "custom_control", constrains = @DbConstrains())
    private Boolean isCustomControlPassed;

    public Passenger() {
        super(Passenger.class.getAnnotation(DbTable.class).name());
    }

    public Integer getIdPassenger() {
        return idPassenger;
    }

    public void setIdPassenger(Integer idPassenger) {
        this.idPassenger = idPassenger;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Integer getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(Integer passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Integer getInternationalPassportNumber() {
        return internationalPassportNumber;
    }

    public void setInternationalPassportNumber(Integer internationalPassportNumber) {
        this.internationalPassportNumber = internationalPassportNumber;
    }

    public Boolean getCustomControlPassed() {
        return isCustomControlPassed;
    }

    public void setCustomControlPassed(Boolean customControlPassed) {
        isCustomControlPassed = customControlPassed;
    }

    public static String getIdPassengerAnnotationName() throws NoSuchFieldException {
        return Passenger.class.getDeclaredField("idPassenger").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getSurnameAnnotationName() throws NoSuchFieldException {
        return Passenger.class.getDeclaredField("surname").getAnnotation(DbColumnVarchar.class).name();
    }

    public static String getPassengerNameAnnotationName() throws NoSuchFieldException {
        return Passenger.class.getDeclaredField("name").getAnnotation(DbColumnVarchar.class).name();
    }

    public static String getPatronymicAnnotationName() throws NoSuchFieldException {
        return Passenger.class.getDeclaredField("patronymic").getAnnotation(DbColumnVarchar.class).name();
    }

    public static String getPassportAnnotationName() throws NoSuchFieldException {
        return Passenger.class.getDeclaredField("passportNumber").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getInternationalPassportAnnotationName() throws NoSuchFieldException {
        return Passenger.class.getDeclaredField("internationalPassportNumber").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getCustomControlAnnotationName() throws NoSuchFieldException {
        return Passenger.class.getDeclaredField("isCustomControlPassed").getAnnotation(DbColumnBoolean.class).name();
    }


    @Override
    public void saveValues(OracleDbProvider provider) throws IllegalAccessException, SQLException {
        StringBuilder query = new StringBuilder()
                .append("INSERT INTO ")
                .append(this.getTableName())
                .append(" (");
        for (Field field : Passenger.class.getDeclaredFields()) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            if (annotations.length != 0) {
                Annotation annotation = annotations[0];
                if (annotation instanceof DbColumnNumber columnNumber) {
                    if (field.getName().startsWith("id")) {
                        continue;
                    }
                    query.append(columnNumber.name());
                } else if (annotation instanceof DbColumnVarchar columnVarchar) {
                    query.append(columnVarchar.name());
                } else if (annotation instanceof DbColumnBoolean columnBoolean) {
                    query.append(columnBoolean.name());
                }
                query.append(", ");
            }
        }
        query.delete(query.length() - 2, query.length());
        query.append(") VALUES (");
        for (Field field : Passenger.class.getDeclaredFields()) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            if (annotations.length != 0) {
                field.setAccessible(true);
                Object value = field.get(this);
                Annotation annotation = annotations[0];
                if (annotation instanceof DbColumnNumber) {
                    if (field.getName().startsWith("id")) {
                        continue;
                    }
                    query.append(value);
                } else if (annotation instanceof DbColumnVarchar) {
                    query.append("'").append(value).append("'");
                } else if (annotation instanceof DbColumnBoolean) {
                    query.append("'").append(((Boolean) value) ? "Y" : "N").append("'");
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
        String query = "SELECT * FROM " + Passenger.class.getAnnotation(DbTable.class).name();
        ResultSet resultSet = provider.getStringsQueryResultSet(query, Collections.emptyList());
        List<String[]> allRows = new ArrayList<>();
        while (resultSet.next()) {
            this.idPassenger = resultSet.getInt(1);
            this.surname = resultSet.getString(2);
            this.name = resultSet.getString(3);
            this.patronymic = resultSet.getString(4);
            this.passportNumber = resultSet.getInt(5);
            this.internationalPassportNumber = resultSet.getInt(6);
            this.isCustomControlPassed = "Y".equals(resultSet.getString(7));
            List<String> row = new ArrayList<>() {{
                add(String.valueOf(idPassenger));
                add(surname);
                add(name);
                add(patronymic);
                add(String.valueOf(passportNumber));
                add(String.valueOf(internationalPassportNumber));
                add(String.valueOf(isCustomControlPassed));
            }};
            allRows.add(row.toArray(new String[0]));
        }
        return allRows.toArray(new String[0][]);
    }

    public void deleteRowById(OracleDbProvider provider) throws NoSuchFieldException, SQLException {
        String query = "DELETE FROM " + Passenger.class.getAnnotation(DbTable.class).name()
                + " WHERE " + Passenger.getIdPassengerAnnotationName() + " = ";
        provider.getStringsQueryResultSet(query, Collections.singletonList(String.valueOf(this.idPassenger)));
    }

    @Override
    public void updateRow(OracleDbProvider provider) throws SQLException, IllegalAccessException, NoSuchFieldException {
        StringBuilder query = new StringBuilder()
                .append("UPDATE ")
                .append(this.getTableName())
                .append(" SET ");

        for (Field field : Passenger.class.getDeclaredFields()) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            if (annotations.length != 0) {
                field.setAccessible(true);
                Object value = field.get(this);
                Annotation annotation = annotations[0];
                if (annotation instanceof DbColumnNumber dbColumnNumber) {
                    if (field.getName().startsWith("id")) {
                        continue;
                    }
                    query.append(dbColumnNumber.name())
                            .append(" = ")
                            .append(value);
                } else if (annotation instanceof DbColumnVarchar dbColumnVarchar) {
                    query.append(dbColumnVarchar.name())
                            .append(" = ")
                            .append("'")
                            .append(value)
                            .append("'");
                } else if (annotation instanceof DbColumnBoolean dbColumnBoolean) {
                    query.append(dbColumnBoolean.name())
                            .append(" = ")
                            .append("'")
                            .append(((Boolean) value) ? "Y" : "N")
                            .append("'");
                }
                query.append(", ");
            }
        }

        query.delete(query.length() - 2, query.length());
        query.append(" WHERE ")
                .append(Passenger.getIdPassengerAnnotationName())
                .append(" = ")
                .append(this.idPassenger);

        Statement statement = provider.getCreatedStatement();
        statement.execute(query.toString());
    }
}
