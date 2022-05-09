package entities;

import annotations.*;
import dbConnection.OracleDbProvider;
import model.support.TimeCalendar;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.List;

@DbTable(name = "PASSENGER")
public class Passenger extends AbstractComponent {
    @DbColumnNumber(name = "passenger_id", constrains = @DbConstrains(isPrimaryKey = true, isAllowedNull = false))
    private Integer idPassenger;

    @DbColumnVarchar(name = "surname", value = 255, constrains = @DbConstrains(isAllowedNull = false))
    private String surname;

    @DbColumnVarchar(name = "name", value = 255, constrains = @DbConstrains(isAllowedNull = false))
    private String name;

    @DbColumnVarchar(name = "patronymic", value = 255)
    private String patronymic;

    @DbColumnBoolean(name = "sex", constrains = @DbConstrains(isAllowedNull = false))
    private Boolean sex;
    @DbColumnDate(name = "birth_date", constrains = @DbConstrains(isAllowedNull = false))
    private TimeCalendar birthDate;
    @DbColumnVarchar(name = "passport", value = 20, constrains = @DbConstrains(isAllowedNull = false, isUnique = true))
    private String passport;

    @DbColumnVarchar(name = "international_passport", value = 20, constrains = @DbConstrains(isUnique = true))
    private String internationalPassport;

    @DbColumnBoolean(name = "custom_control")
    private Boolean isCustomControlPassed;

    @DbColumnBoolean(name = "cargo")
    private Boolean isHavingCargo;


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

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public TimeCalendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(TimeCalendar birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getInternationalPassport() {
        return internationalPassport;
    }

    public void setInternationalPassport(String internationalPassport) {
        this.internationalPassport = internationalPassport;
    }

    public Boolean getCustomControlPassed() {
        return isCustomControlPassed;
    }

    public void setCustomControlPassed(Boolean customControlPassed) {
        isCustomControlPassed = customControlPassed;
    }

    public Boolean getHavingCargo() {
        return isHavingCargo;
    }

    public void setHavingCargo(Boolean havingCargo) {
        isHavingCargo = havingCargo;
    }

    public static String getIdPassengerAnnotationName() throws NoSuchFieldException {
        return Passenger.class.getDeclaredField("idPassenger").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getSurnameAnnotationName() throws NoSuchFieldException {
        return Passenger.class.getDeclaredField("surname").getAnnotation(DbColumnVarchar.class).name();
    }

    public static String getNameAnnotationName() throws NoSuchFieldException {
        return Passenger.class.getDeclaredField("name").getAnnotation(DbColumnVarchar.class).name();
    }

    public static String getPatronymicAnnotationName() throws NoSuchFieldException {
        return Passenger.class.getDeclaredField("patronymic").getAnnotation(DbColumnVarchar.class).name();
    }

    public static String getSexAnnotationName() throws NoSuchFieldException {
        return Passenger.class.getDeclaredField("sex").getAnnotation(DbColumnBoolean.class).name();
    }

    public static String getBirthDateAnnotationName() throws NoSuchFieldException {
        return Passenger.class.getDeclaredField("birthDate").getAnnotation(DbColumnDate.class).name();
    }

    public static String getPassportAnnotationName() throws NoSuchFieldException {
        return Passenger.class.getDeclaredField("passport").getAnnotation(DbColumnVarchar.class).name();
    }

    public static String getInternationalPassportAnnotationName() throws NoSuchFieldException {
        return Passenger.class.getDeclaredField("internationalPassport").getAnnotation(DbColumnVarchar.class).name();
    }

    public static String getCustomControlAnnotationName() throws NoSuchFieldException {
        return Passenger.class.getDeclaredField("isCustomControlPassed").getAnnotation(DbColumnBoolean.class).name();
    }

    public static String getHavingCargoAnnotationName() throws NoSuchFieldException {
        return Passenger.class.getDeclaredField("isHavingCargo").getAnnotation(DbColumnBoolean.class).name();
    }

    @Override
    public void saveValues(OracleDbProvider provider) throws IllegalAccessException, SQLException {
        AbstractComponent.saveTo(Passenger.class, this, provider, this.getTableName());
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
            this.sex = "Y".equals(resultSet.getString(5));
            this.birthDate = new TimeCalendar(resultSet.getDate(6));
            this.passport = resultSet.getString(7);
            this.internationalPassport = resultSet.getString(8);
            this.isCustomControlPassed = "Y".equals(resultSet.getString(9));
            this.isHavingCargo = "Y".equals(resultSet.getString(10));
            List<String> row = new ArrayList<>() {{
                add(String.valueOf(idPassenger));
                add(surname);
                add(name);
                add(patronymic);
                add(String.valueOf(sex));
                add(birthDate.toString());
                add(passport);
                add(internationalPassport);
                add(String.valueOf(isCustomControlPassed));
                add(String.valueOf(isHavingCargo));
            }};
            allRows.add(row.toArray(new String[0]));
        }
        return allRows.toArray(new String[0][]);
    }

    public void deleteRowByPrimaryKey(OracleDbProvider provider) throws NoSuchFieldException, SQLException {
        String query = "DELETE FROM " + Passenger.class.getAnnotation(DbTable.class).name()
                + " WHERE " + Passenger.getIdPassengerAnnotationName() + " = ?";
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
                } else if (annotation instanceof DbColumnDate dbColumnDate) {
                    query.append(dbColumnDate.name())
                            .append(" = ")
                            .append(((TimeCalendar) value).toSqlStringDate());
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
