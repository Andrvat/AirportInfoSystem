import argsParsing.CmdArgsParser;
import controller.ControllerManager;
import dbConnection.OracleDbConnector;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import view.MainDisplay;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            CmdArgsParser cmdArgsParser = new CmdArgsParser();
            cmdArgsParser.parseArguments(args);

            OracleDbConnector connector = new OracleDbConnector(
                    cmdArgsParser.getDbUrl(),
                    cmdArgsParser.getUserLogin(),
                    cmdArgsParser.getUserPassword());
            ControllerManager controllerManager = ControllerManager.builder()
                    .connector(connector).build();
            MainDisplay mainDisplay = new MainDisplay(controllerManager);
        } catch (Exception exception) {
            logger.error(exception.getMessage());
            exception.printStackTrace();
        }
    }
}
