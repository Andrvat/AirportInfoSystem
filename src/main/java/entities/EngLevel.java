package entities;

import annotations.DbColumnVarchar;
import annotations.DbConstrains;
import annotations.DbTable;
import dbConnection.OracleDbProvider;

import java.sql.SQLException;
import java.util.HashMap;

@DbTable(name = "ENG_LEVEL")
public class EngLevel extends AbstractComponent {
    @DbColumnVarchar(name = "eng_level_id", constrains = @DbConstrains(isPrimaryKey = true, isAllowedNull = false))
    private String idEngLevel;

    public EngLevel() {
        super(EngLevel.class.getAnnotation(DbTable.class).name());
    }

    public static String getIdEngLevelAnnotationName() throws NoSuchFieldException {
        return EngLevel.class.getDeclaredField("idEngLevel").getAnnotation(DbColumnVarchar.class).name();
    }

    public String getIdEngLevel() {
        return idEngLevel;
    }

    public void setIdEngLevel(String idEngLevel) {
        this.idEngLevel = idEngLevel;
    }

    @Override
    public void saveValues(OracleDbProvider provider) throws IllegalAccessException, SQLException {
        AbstractComponent.saveTo(EngLevel.class, this, provider, this.getTableName());
    }

    @Override
    public String[][] getAllRows(OracleDbProvider provider) throws SQLException, IllegalAccessException {
        return AbstractComponent.getAllFrom(EngLevel.class, this, provider, this.getTableName());
    }

    @Override
    public void deleteRowByPrimaryKey(OracleDbProvider provider) throws NoSuchFieldException, SQLException {
        AbstractComponent.deleteFrom(this.getTableName(), provider,
                new HashMap<>() {{
                    put(EngLevel.getIdEngLevelAnnotationName(), String.valueOf(idEngLevel));

                }});
    }

    @Override
    public void updateRow(OracleDbProvider provider) throws SQLException, IllegalAccessException, NoSuchFieldException {
        AbstractComponent.updateTo(EngLevel.class, this, provider, this.getTableName(),
                new HashMap<>() {{
                    put(EngLevel.getIdEngLevelAnnotationName(), String.valueOf(idEngLevel));
                }});
    }
}
