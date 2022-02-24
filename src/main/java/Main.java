import argsParsing.CmdArgsParser;
import dbConnection.OracleDbConnector;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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
        } catch (Exception exception) {
            logger.error(exception.getMessage());
            exception.printStackTrace();
        }
    }
}
