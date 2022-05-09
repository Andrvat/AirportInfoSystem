package converters;

import java.util.Locale;

public class TitlesConverter {
    public static String makeTitleFrom(String s) {
        return s.substring(0, 1).toUpperCase(Locale.ROOT) + s.substring(1).toLowerCase(Locale.ROOT);
    }
}
