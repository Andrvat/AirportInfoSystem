package entities;

import annotations.*;
import dbConnection.OracleDbProvider;
import model.support.TimeCalendar;

import java.sql.SQLException;
import java.util.HashMap;

@DbTable(name = "PILOT_MEDICAL_CHECKUP_HISTORY", pkNumber = 2)
public class PilotMedicalCheckupHistory extends AbstractComponent {
    @DbColumnNumber(name = "pilot_id", constrains = @DbConstrains(isPrimaryKey = true, isAllowedNull = false))
    private Integer idPilot;

    @DbColumnDate(name = "medical_checkup_date", constrains = @DbConstrains(isPrimaryKey = true, isAllowedNull = false), type = TimeCalendar.TimeCalendarType.DATE_ONLY)
    private TimeCalendar medicalCheckupDate;

    @DbColumnBoolean(name = "medical_checkup_result", constrains = @DbConstrains(isAllowedNull = false), type = DbColumnBoolean.BooleanValueType.GOOD_BAD)
    private Boolean medicalCheckupResult;

    public PilotMedicalCheckupHistory() {
        super(PilotMedicalCheckupHistory.class.getAnnotation(DbTable.class).name());
    }

    public static String getIdPilotAnnotationName() throws NoSuchFieldException {
        return PilotMedicalCheckupHistory.class.getDeclaredField("idPilot").getAnnotation(DbColumnNumber.class).name();
    }

    public static String getMedicalCheckupDateAnnotationName() throws NoSuchFieldException {
        return PilotMedicalCheckupHistory.class.getDeclaredField("medicalCheckupDate").getAnnotation(DbColumnDate.class).name();
    }

    public static String getMedicalCheckupResultAnnotationName() throws NoSuchFieldException {
        return PilotMedicalCheckupHistory.class.getDeclaredField("medicalCheckupResult").getAnnotation(DbColumnBoolean.class).name();
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

    public Boolean getMedicalCheckupResult() {
        return medicalCheckupResult;
    }

    public void setMedicalCheckupResult(Boolean medicalCheckupResult) {
        this.medicalCheckupResult = medicalCheckupResult;
    }

    @Override
    public void saveValues(OracleDbProvider provider) throws IllegalAccessException, SQLException {
        AbstractComponent.saveTo(PilotMedicalCheckupHistory.class, this, provider, this.getTableName());
    }

    @Override
    public String[][] getAllRows(OracleDbProvider provider) throws SQLException, IllegalAccessException {
        return AbstractComponent.getAllFrom(PilotMedicalCheckupHistory.class, this, provider, this.getTableName());
    }

    @Override
    public void deleteRowByPrimaryKey(OracleDbProvider provider) throws NoSuchFieldException, SQLException {
        AbstractComponent.deleteFrom(this.getTableName(), provider,
                new HashMap<>() {{
                    put(PilotMedicalCheckupHistory.getIdPilotAnnotationName(), String.valueOf(idPilot));
                    put(PilotMedicalCheckupHistory.getMedicalCheckupDateAnnotationName(), String.valueOf(medicalCheckupDate));

                }});
    }

    @Override
    public void updateRow(OracleDbProvider provider) throws SQLException, IllegalAccessException, NoSuchFieldException {
        AbstractComponent.updateTo(PilotMedicalCheckupHistory.class, this, provider, this.getTableName(),
                new HashMap<>() {{
                    put(PilotMedicalCheckupHistory.getIdPilotAnnotationName(), String.valueOf(idPilot));
                    put(PilotMedicalCheckupHistory.getMedicalCheckupDateAnnotationName(), String.valueOf(medicalCheckupDate));
                }});
    }
}
