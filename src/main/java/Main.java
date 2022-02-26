import argsParsing.CmdArgsParser;
import controller.ControllerManager;
import dbConnection.OracleDbProvider;
import entities.Ticket;
import model.DbModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import view.MainDisplay;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            new Ticket();
            CmdArgsParser cmdArgsParser = new CmdArgsParser();
            cmdArgsParser.parseArguments(args);

            OracleDbProvider provider = new OracleDbProvider(
                    cmdArgsParser.getDbUrl(),
                    cmdArgsParser.getUserLogin(),
                    cmdArgsParser.getUserPassword());
            DbModel model = new DbModel();
            ControllerManager controllerManager = ControllerManager.builder()
                    .provider(provider)
                    .model(model)
                    .build();
            new MainDisplay(controllerManager);
        } catch (Exception exception) {
            logger.error(exception.getMessage());
            exception.printStackTrace();
        }
    }
}
