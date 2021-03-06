package forms;

import java.util.List;

public class RequestResultPackage {

    private String tableName;
    private String[] columnNames;

    private String[][] resultRows;

    public String[] getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }

    public String[][] getResultRows() {
        return resultRows;
    }

    public void setResultRows(String[][] resultRows) {
        this.resultRows = resultRows;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
