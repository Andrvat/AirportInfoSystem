package argsParsing;

import org.apache.commons.cli.*;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CmdArgsParser {
    private static final String URL_MAP_KEY = "dbUrl";
    private static final String USER_LOGIN_MAP_KEY = "userLogin";
    private static final String USER_PASSWORD_MAP_KEY = "userPassword";
    private static final String TABLE_SCRIPTS_MAP_KEY = "scripts";

    private final Options cmdOptions = new Options();

    private final HashMap<String, String> dbConnectionParameters = new HashMap<>();

    public CmdArgsParser() {
        OptionSettings dbUrlSettings = OptionSettings.builder()
                .opt("u")
                .longOpt("url")
                .hasArg(true)
                .description("Oracle database connection address")
                .build();
        OptionSettings dbUserSettings = OptionSettings.builder()
                .opt("l")
                .longOpt("login")
                .hasArg(true)
                .description("Oracle database user's login")
                .build();
        OptionSettings dbUPasswordSettings = OptionSettings.builder()
                .opt("p")
                .longOpt("password")
                .hasArg(true)
                .description("Oracle database user's password")
                .build();
        OptionSettings dbScriptSettings = OptionSettings.builder()
                .opt("s")
                .longOpt("scriptPath")
                .hasArg(true)
                .description("Path to scripts witch create necessary tables")
                .build();
        this.addAllSettingsToOptions(new ArrayList<>() {{
            add(dbUrlSettings);
            add(dbUserSettings);
            add(dbUPasswordSettings);
            add(dbScriptSettings);
        }});
    }

    private void addAllSettingsToOptions(List<OptionSettings> optionSettings) {
        for (OptionSettings option : optionSettings) {
            cmdOptions.addOption(option.getOpt(),
                    option.getLongOpt(),
                    option.getHasArg(),
                    option.getDescription());
        }
    }

    public void parseArguments(String[] args) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine = parser.parse(cmdOptions, args);

        this.dbConnectionParameters.put(URL_MAP_KEY, commandLine.getOptionValue("u"));
        this.dbConnectionParameters.put(USER_LOGIN_MAP_KEY, commandLine.getOptionValue("l"));
        this.dbConnectionParameters.put(USER_PASSWORD_MAP_KEY, commandLine.getOptionValue("p"));
        this.dbConnectionParameters.put(TABLE_SCRIPTS_MAP_KEY, commandLine.getOptionValue("s"));

        if (this.dbConnectionParameters.containsValue(null)) {
            throw new InvalidParameterException("Some cmd required argument is null");
        }
    }

    public String getDbUrl() {
        return this.dbConnectionParameters.get(URL_MAP_KEY);
    }

    public String getUserLogin() {
        return this.dbConnectionParameters.get(USER_LOGIN_MAP_KEY);
    }

    public String getUserPassword() {
        return this.dbConnectionParameters.get(USER_PASSWORD_MAP_KEY);
    }

    public String getTableScriptsPath() {
        return this.dbConnectionParameters.get(TABLE_SCRIPTS_MAP_KEY);
    }
}
