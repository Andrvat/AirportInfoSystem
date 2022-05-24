package entities;

import annotations.*;
import dbConnection.OracleDbProvider;
import model.support.TimeCalendar;

import java.sql.SQLException;
import java.util.HashMap;

@DbTable(name = "PILOT")
public class Pilot extends AbstractComponent {
    @DbColumnNumber(name = "pilot_id", constrains = @DbConstrains(isPrimaryKey = true, isAllowedNull = false))
    private Integer idPilot;

    @DbColumnDate(name = "medical_checkup_date", constrains = @DbConstrains(isAllowedNull = false), type = TimeCalendar.TimeCalendarType.DATE_ONLY)
    private TimeCalendar medicalCheckupDate;

    @DbColumnBoolean(name = "professional_aptitude", constrains = @DbConstrains(isAllowedNull = false), type = DbColumnBoolean.BooleanValueType.YES_NO)
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
        AbstractComponent.saveTo(Pilot.class, this, provider, this.getTableName());
    }

    @Override
    public String[][] getAllRows(OracleDbProvider provider) throws SQLException, IllegalAccessException {
        return AbstractComponent.getAllFrom(Pilot.class, this, provider, this.getTableName());
    }

    @Override
    public void deleteRowByPrimaryKey(OracleDbProvider provider) throws NoSuchFieldException, SQLException {
        AbstractComponent.deleteFrom(this.getTableName(), provider,
                new HashMap<>() {{
                    put(Pilot.getIdPilotAnnotationName(), String.valueOf(idPilot));

                }});
    }

    @Override
    public void updateRow(OracleDbProvider provider) throws SQLException, IllegalAccessException, NoSuchFieldException {
        AbstractComponent.updateTo(Pilot.class, this, provider, this.getTableName(),
                new HashMap<>() {{
                    put(Pilot.getIdPilotAnnotationName(), String.valueOf(idPilot));
                }});
    }
}
