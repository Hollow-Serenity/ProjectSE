package Main;

import java.sql.*;

public class Database {
    private static Database singleton = null;
    private static Connection Connect = null;
    private static PreparedStatement prestatement = null;
    private static ResultSet resultSet = null;
    private final static String CONNECTION_URL = "jdbc:mysql://localhost:3306/zeroxess";

    //private final static String CONNECTION_URL = "jdbc:mysql://localhost/zeroxess?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private Database() {
        try {
            Connect = DriverManager.getConnection(CONNECTION_URL, "root", "19064373");
        } catch (SQLException e) {
            System.out.println("Cannot Connect to the Database:  " + e.getMessage());
        }
    }

    public static Database getDatabase() {
        if(singleton == null) {
            singleton = new Database();
        }
        return singleton;
    }

    public static Connection getConnection() {
        return Connect;
    }
    public static PreparedStatement getPrestatement() {
        return prestatement;
    }
    public static ResultSet getResultSet() {
        return resultSet;
    }
}
