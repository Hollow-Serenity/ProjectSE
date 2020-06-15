package Main;

import java.sql.*;

public class Database {
    public Connection Connect = null;
    public PreparedStatement prestatement = null;
    public ResultSet resultSet = null;
    private final static String CONNECTION_URL = "jdbc:mysql://localhost:3306/zeroxess";

    //private final static String CONNECTION_URL = "jdbc:mysql://localhost/zeroxess?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public Database() {
        try {
            Connect = DriverManager.getConnection(CONNECTION_URL, "root", "DbPassword123");
        } catch (SQLException e) {
            System.out.println("Cannot Connect to the Database:  " + e.getMessage());
        }
    }
}
