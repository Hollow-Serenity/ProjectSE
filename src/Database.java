import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private String host = "jdbc:mysql://localhost:3306/zeroxess";
    private String UN = "root";
    private String PW = "DbPassword123";

    private Connection connection = DriverManager.getConnection(host, UN, PW);

    public Database() throws SQLException {
    }

    public Connection getConnection() {
        return connection;
    }
}
