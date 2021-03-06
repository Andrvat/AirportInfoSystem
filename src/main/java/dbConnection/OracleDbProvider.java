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

    private String role;

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

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
        this.connection.setAutoCommit(false);
    }

    public void commitChanges() throws SQLException {
        this.connection.commit();
    }

    public void rollbackChanges() throws SQLException {
        this.connection.rollback();
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

    public CallableStatement getCreatedCallableStatement(String query, Integer parameter) throws SQLException {
        CallableStatement statement = this.connection.prepareCall(query);
        statement.setInt(1, parameter);
        return statement;
    }

    public CallableStatement getCreatedCallableStatement(String query, List<Integer> parameters) throws SQLException {
        CallableStatement statement = this.connection.prepareCall(query);
        for (int i = 1; i <= parameters.size(); ++i) {
            if (parameters.get(i - 1) == null) {
                statement.setNull(i, Types.INTEGER);
            } else {
                statement.setInt(i, parameters.get(i - 1));
            }
        }
        return statement;
    }

    public ResultSet getStringsQueryResultSet(String query, List<String> parameters) throws SQLException {
        PreparedStatement preparedStatement = this.connection.prepareStatement(query);
        for (int i = 0; i < parameters.size(); ++i) {
            preparedStatement.setString(i + 1, parameters.get(i));
        }
        preparedStatement.execute();
        return preparedStatement.getResultSet();
    }

    public ResultSet getStringsQueryResultSet(String query, Integer first, Integer second) throws SQLException {
        PreparedStatement preparedStatement = this.connection.prepareStatement(query);
        preparedStatement.setInt(1, first);
        preparedStatement.setInt(2, second);
        preparedStatement.execute();
        return preparedStatement.getResultSet();
    }
}