package Main;

import java.sql.*;

public class DbUtil {
    private static final String HOST = "jdbc:mysql://localhost:3306/zeroxess" + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String UN = "root";
    private static final String PW = "d1i2n3o4";
    private static Connection connection;

    public static void dbConnect() throws SQLException {
        try {
            connection = DriverManager.getConnection(HOST, UN, PW);
        } catch(SQLException e) {
            System.out.println("Connection failed");
            throw e;
        }
    }

    public static void dbDisconnect() throws SQLException {
        try {
            if(connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch(Exception e) {
            throw e;
        }
    }

    public static void dbExecuteQuery(String sqlStatement) throws SQLException {
        Statement statement = null;
        try {
            dbConnect();
            statement = connection.createStatement();
            statement.executeUpdate(sqlStatement);
        } catch(SQLException e) {
            System.out.println("Problem occurred at dbExecuteQuery");
            throw e;
        } finally {
            if(statement != null) {
                statement.close();
            }
            dbDisconnect();
        }
    }

    //Retrieves records from database
    public static ResultSet dbExecute(String sqlQuery) throws SQLException {
        dbConnect();
        Statement statement = connection.createStatement();

        return statement.executeQuery(sqlQuery);
    }

    //
    public static Connection getConnection() {
        return connection;
    }
}
