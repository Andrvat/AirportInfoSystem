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

    @DbColumnBoolean(name = "sex", constrains = @DbConstrains(isAllowedNull = false), type = DbColumnBoolean.BooleanValueType.MAN_WOMAN)
    private Boolean sex;
    @DbColumnDate(name = "birth_date", constrains = @DbConstrains(isAllowedNull = false), type = TimeCalendar.TimeCalendarType.DATE_ONLY)
    private TimeCalendar birthDate;
    @DbColumnVarchar(name = "passport", value = 20, constrains = @DbConstrains(isAllowedNull = false, isUnique = true))
    private String passport;

    @DbColumnVarchar(name = "international_passport", value = 20, constrains = @DbConstrains(isUnique = true))
    private String internationalPassport;

    @DbColumnBoolean(name = "custom_control", type = DbColumnBoolean.BooleanValueType.YES_NO)
    private Boolean isCustomControlPassed;

    @DbColumnBoolean(name = "cargo", type = DbColumnBoolean.BooleanValueType.YES_NO)
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
    public String[][] getAllRows(OracleDbProvider provider) throws SQLException, IllegalAccessException {
        return AbstractComponent.getAllFrom(Passenger.class, this, provider, this.getTableName());
    }

    public void deleteRowByPrimaryKey(OracleDbProvider provider) throws NoSuchFieldException, SQLException {
        AbstractComponent.deleteFrom(this.getTableName(), provider,
                new HashMap<>() {{
                    put(Passenger.getIdPassengerAnnotationName(), String.valueOf(idPassenger));
                }});
    }

    @Override
    public void updateRow(OracleDbProvider provider) throws SQLException, IllegalAccessException, NoSuchFieldException {
        AbstractComponent.updateTo(Passenger.class, this, provider, this.getTableName(),
                new HashMap<>() {{
                    put(Passenger.getIdPassengerAnnotationName(), String.valueOf(idPassenger));
                }});
    }
}
