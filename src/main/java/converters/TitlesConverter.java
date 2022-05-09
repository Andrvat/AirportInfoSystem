package converters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class TitlesConverter {
    public static String makeTitleFrom(String s) {
        var words = s.split("_");
        var titles = new ArrayList<>();
        for (var word : words) {
            titles.add(word.substring(0, 1).toUpperCase(Locale.ROOT) + word.substring(1).toLowerCase(Locale.ROOT));
        }
        StringBuilder fullTitle = new StringBuilder();
        for (var title : titles) {
            fullTitle.append(title);
        }
        return fullTitle.toString();
    }
}
