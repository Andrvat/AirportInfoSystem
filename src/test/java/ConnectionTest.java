import dbConnection.OracleDbProvider;
import org.junit.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionTest {
    private static final String TEST_URL = "jdbc:oracle:thin:@localhost:1521:";
    private static final String TEST_USER_LOGIN = "TEST_USER";
    private static final String TEST_USER_PASSWORD = "sun";

    private static OracleDbProvider provider;

    @Before
    public void init() throws SQLException {
        provider = new OracleDbProvider(TEST_URL, TEST_USER_LOGIN, TEST_USER_PASSWORD);
    }

    @After
    public void close() {
        provider.close();
    }

    @Test
    public void getJdbcConnection() throws SQLException {
        Assert.assertTrue(provider.isValid(1));
    }

    @Test
    public void executeSelectOperation() throws SQLException {
        Statement statement = provider.getCreatedStatement();
        boolean hasResult = statement.execute("SELECT * FROM teacher");
        Assert.assertTrue(hasResult);

        ResultSet resultSet = statement.getResultSet();
        Assert.assertEquals(3, resultSet.getMetaData().getColumnCount());
    }
}
