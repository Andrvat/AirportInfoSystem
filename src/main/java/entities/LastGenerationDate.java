package entities;

import annotations.DbColumnDate;
import annotations.DbConstrains;
import annotations.DbTable;
import dbConnection.OracleDbProvider;
import model.support.TimeCalendar;

import java.sql.SQLException;

@DbTable(name = "LAST_GENERATION_DATE")
public class LastGenerationDate extends AbstractComponent {
    @DbColumnDate(name = "last_date")
    private TimeCalendar departureTime;

    public LastGenerationDate() {
        super(LastGenerationDate.class.getAnnotation(DbTable.class).name());
    }

    public TimeCalendar getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(TimeCalendar departureTime) {
        this.departureTime = departureTime;
    }

    @Override
    public void saveValues(OracleDbProvider provider) throws IllegalAccessException, SQLException {
    }

    @Override
    public String[][] getAllRows(OracleDbProvider provider) throws SQLException, IllegalAccessException {
        return AbstractComponent.getAllFrom(LastGenerationDate.class, this, provider, this.getTableName());
    }

    @Override
    public void deleteRowByPrimaryKey(OracleDbProvider provider) throws NoSuchFieldException, SQLException {
    }

    @Override
    public void updateRow(OracleDbProvider provider) throws SQLException, IllegalAccessException, NoSuchFieldException {
    }
}
