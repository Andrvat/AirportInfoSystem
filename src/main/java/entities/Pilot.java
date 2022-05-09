package entities;

import annotations.*;
import dbConnection.OracleDbProvider;
import model.support.TimeCalendar;

import java.sql.SQLException;

@DbTable(name = "PILOT")
public class Pilot extends AbstractComponent {
    @DbColumnNumber(name = "pilot_id", constrains = @DbConstrains(isPrimaryKey = true, isAllowedNull = false))
    private Integer idPilot;

    @DbColumnDate(name = "medical_checkup_date", constrains = @DbConstrains(isAllowedNull = false))
    private TimeCalendar medicalCheckupDate;

    @DbColumnBoolean(name = "professional_aptitude", constrains = @DbConstrains(isAllowedNull = false))
    private Boolean professionalAptitude;

    @DbColumnNumber(name = "flight_hours", constrains = @DbConstrains(isAllowedNull = false))
    private Integer flightHours;

    @DbColumnVarchar(name = "eng_level_id", constrains = @DbConstrains(isAllowedNull = false))
    private String engLevelId;

    public Pilot() {
        super(Pilot.class.getAnnotation(DbTable.class).name());
    }

    public static String getIdPilotAnnotationName() throws NoSuchFieldException {
        return Pilot.class.getDeclaredField("idPilot").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getMedicalCheckupDateAnnotationName() throws NoSuchFieldException {
        return Pilot.class.getDeclaredField("medicalCheckupDate").getAnnotation(DbColumnDate.class).name();
    }

    public static String getProfessionalAptitudeAnnotationName() throws NoSuchFieldException {
        return Pilot.class.getDeclaredField("professionalAptitude").getAnnotation(DbColumnBoolean.class).name();
    }

    public static String getFlightHoursAnnotationName() throws NoSuchFieldException {
        return Pilot.class.getDeclaredField("flightHours").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getEngLevelIdAnnotationName() throws NoSuchFieldException {
        return Pilot.class.getDeclaredField("engLevelId").getAnnotation(DbColumnVarchar.class).name();
    }

    public Integer getIdPilot() {
        return idPilot;
    }

    public void setIdPilot(Integer idPilot) {
        this.idPilot = idPilot;
    }

    public TimeCalendar getMedicalCheckupDate() {
        return medicalCheckupDate;
    }

    public void setMedicalCheckupDate(TimeCalendar medicalCheckupDate) {
        this.medicalCheckupDate = medicalCheckupDate;
    }

    public Boolean getProfessionalAptitude() {
        return professionalAptitude;
    }

    public void setProfessionalAptitude(Boolean professionalAptitude) {
        this.professionalAptitude = professionalAptitude;
    }

    public Integer getFlightHours() {
        return flightHours;
    }

    public void setFlightHours(Integer flightHours) {
        this.flightHours = flightHours;
    }

    public String getEngLevelId() {
        return engLevelId;
    }

    public void setEngLevelId(String engLevelId) {
        this.engLevelId = engLevelId;
    }

    @Override
    public void saveValues(OracleDbProvider provider) throws IllegalAccessException, SQLException {

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

    }
}
