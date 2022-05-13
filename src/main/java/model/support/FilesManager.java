package model.support;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FilesManager {
    public static List<String> getQueriesFilenames(String path) {
        List<String> verifiedFileNames = new ArrayList<>();
        File folder = new File(path);
        File[] filesList = folder.listFiles();
        for (int i = 0; i < Objects.requireNonNull(filesList).length; ++i) {
            if (filesList[i].isFile()) {
                verifiedFileNames.add(filesList[i].getName());
            }
        }
        return verifiedFileNames;
    }
}
