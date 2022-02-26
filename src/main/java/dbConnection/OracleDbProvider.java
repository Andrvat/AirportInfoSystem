package dbConnection;

import oracle.jdbc.OracleDriver;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.Closeable;
import java.sql.*;
import java.util.List;
import java.util.TimeZone;

public class OracleDbProvider implements Closeable {
    private static final Logger logger = LogManager.getLogger(OracleDbProvider.class);

    private static final String DB_TIME_ZONE = "Asia/Novosibirsk";

    private final String dbUrl;
    private final String userLogin;
    private final String userPassword;
    private final Connection connection;

    public OracleDbProvider(String dbUrl, String userLogin, String userPassword) throws SQLException {
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
        } catch (SQLException exception) {
            logger.error("Could not close Oracle database connection. \n" +
                    "Message: " + exception.getMessage());
        }
    }

    public Statement getCreatedStatement() throws SQLException {
        Statement statement = this.connection.createStatement();
        logger.debug("New statement " + statement + " was successfully created");
        return statement;
    }

    public ResultSet getStringsQueryResultSet(String query, List<String> parameters) throws SQLException {
        PreparedStatement preparedStatement = this.connection.prepareStatement(query);
        for (int i = 0; i < parameters.size(); ++i) {
            preparedStatement.setString(i + 1, parameters.get(i));
        }
        boolean hasResult = preparedStatement.execute();
        if (!hasResult) {
            throw new SQLSyntaxErrorException("Query " + query + " has no result");
        }
        return preparedStatement.getResultSet();
    }
}