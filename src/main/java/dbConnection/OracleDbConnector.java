package dbConnection;

import oracle.jdbc.OracleDriver;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TimeZone;

public class OracleDbConnector implements Closeable {
    private static final Logger logger = LogManager.getLogger(OracleDbConnector.class);

    private static final String DB_TIME_ZONE = "Asia/Novosibirsk";

    private final String dbUrl;
    private final String userLogin;
    private final String userPassword;
    private final Connection connection;

    public OracleDbConnector(String dbUrl, String userLogin, String userPassword) throws SQLException {
        this.dbUrl = dbUrl;
        this.userLogin = userLogin;
        this.userPassword = userPassword;

        TimeZone dbTimeZone = TimeZone.getTimeZone(DB_TIME_ZONE);
        TimeZone.setDefault(dbTimeZone);

        DriverManager.registerDriver(new OracleDriver());
        this.connection = DriverManager.getConnection(this.dbUrl, this.userLogin, this.userPassword);
        logger.info("Driver manager successfully connected to " + this.dbUrl + " via user " + this.userLogin);
    }

    @Override
    public void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            logger.error("Could not close Oracle database connection. \n" +
                    "Message: " + e.getMessage());
        }
    }

    public boolean isValid(int timeoutSeconds) throws SQLException {
        return this.connection.isValid(timeoutSeconds);
    }

    public Statement getCreatedStatement() throws SQLException {
        Statement statement = this.connection.createStatement();
        logger.debug("New statement " + statement + " was successfully created");
        return statement;
    }
}