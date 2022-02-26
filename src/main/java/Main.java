import argsParsing.CmdArgsParser;
import controller.ControllerManager;
import dbConnection.OracleDbProvider;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import view.MainDisplay;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            CmdArgsParser cmdArgsParser = new CmdArgsParser();
            cmdArgsParser.parseArguments(args);

            OracleDbProvider provider = new OracleDbProvider(
                    cmdArgsParser.getDbUrl(),
                    cmdArgsParser.getUserLogin(),
                    cmdArgsParser.getUserPassword());
            ControllerManager controllerManager = ControllerManager.builder()
                    .connector(provider).build();
            controllerManager.initRequiredTables(cmdArgsParser.getTableScriptsPath());
            new MainDisplay(controllerManager);
        } catch (Exception exception) {
            logger.error(exception.getMessage());
            exception.printStackTrace();
        }
    }
}
