package dbConnection;

import oracle.jdbc.OracleDriver;

import java.io.Closeable;
import java.sql.*;
import java.util.List;
import java.util.TimeZone;

public class OracleDbProvider implements Closeable {
    private static final String DB_TIME_ZONE = "Asia/Novosibirsk";

    private String dbUrl;
    private String userLogin;
    private String userPassword;
    private Connection connection;

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void registerDbProvider() throws SQLException {
        TimeZone dbTimeZone = TimeZone.getTimeZone(DB_TIME_ZONE);
        TimeZone.setDefault(dbTimeZone);

        DriverManager.registerDriver(new OracleDriver());
        this.connection = DriverManager.getConnection(this.dbUrl, this.userLogin, this.userPassword);
    }

    @Override
    public void close() {
        try {
            this.connection.close();
        } catch (Exception ignored) {
        }
    }

    public Statement getCreatedStatement() throws SQLException {
        return this.connection.createStatement();
    }

    public ResultSet getStringsQueryResultSet(String query, List<String> parameters) throws SQLException {
        PreparedStatement preparedStatement = this.connection.prepareStatement(query);
        for (int i = 0; i < parameters.size(); ++i) {
            preparedStatement.setString(i + 1, parameters.get(i));
        }
        preparedStatement.execute();
        return preparedStatement.getResultSet();
    }
}