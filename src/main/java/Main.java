import controller.ControllerManager;
import dbConnection.OracleDbProvider;
import model.DbModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import view.LoginDisplay;
import view.MainDisplay;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        if ("debug".equals(args[0])) {
            Toolkit.getDefaultToolkit()
                    .getSystemClipboard()
                    .setContents(new StringSelection("jdbc:oracle:thin:@localhost:1521:"), null);
        }
        try {
            OracleDbProvider provider = new OracleDbProvider();
            DbModel model = new DbModel();
            ControllerManager controllerManager = ControllerManager.builder()
                    .provider(provider)
                    .model(model)
                    .build();
            new LoginDisplay(controllerManager);
        } catch (Exception exception) {
            logger.error(exception.getMessage());
            exception.printStackTrace();
        }
    }
}
