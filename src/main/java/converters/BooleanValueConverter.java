package converters;

import annotations.DbColumnBoolean;

public class BooleanValueConverter {
    public static String convertByType(DbColumnBoolean.BooleanValueType type, Boolean value) {
        switch (type) {
            case REAL_BOOL -> {
                return String.valueOf(value);
            }
            case YES_NO -> {
                return value ? "Да" : "Нет";
            }
            case MAN_WOMAN -> {
                return value ? "Муж." : "Жен.";
            }
            case GOOD_BAD -> {
                return value ? "Хорошо" : "Плохо";
            }
        }
        return "";
    }
}
