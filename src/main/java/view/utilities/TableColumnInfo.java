package view.utilities;

import lombok.Builder;

@Builder
public class TableColumnInfo {
    private String name;
    private String typeValue;

    public String getName() {
        return name;
    }

    public String getTypeValue() {
        return typeValue;
    }
}
