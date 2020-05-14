import java.sql.*;

public class Main {
    private static final String USRNM = "root";
    private static final String PSSWRD = "Vlekkie15";
    private static final String CNN_STRNG = "jdbc:mysql://localhost:3306/zeroxess";

    public static void main(String[] args) {
        registration("test", "test", "test", "female", "test", 0655555);
        login("test", "test");
    }

    private static void login(String UN, String PW) {
        Connection conn;
        String PWDB = null;
        try {
            conn = DriverManager.getConnection(CNN_STRNG, USRNM, PSSWRD);
            System.out.println("Connected!");
            PreparedStatement statement = conn.prepareStatement("select * from users where userName = ?");
            statement.setString(1, UN);
            ResultSet res = statement.executeQuery();
            while(res.next()) {
                PWDB = res.getString("password");
            }
        }
        catch (SQLException e) {
            System.err.println("error");
        }
        if (PW.equals(PWDB)) {
            System.out.println("login");
            //Login
        }
        else {
            System.out.println("wrong");
            //Wrong password-username combination
        }
    }

    private static void registration(String fName, String lName, String pw, String gender, String uName, Integer phNumber) {
        Connection conn;
        try {
            conn = DriverManager.getConnection(CNN_STRNG, USRNM, PSSWRD);
            System.out.println("Connected!");
            Statement stmt = conn.createStatement();
            String insert = "INSERT INTO zeroxess.users(firstName, lastName, password, gender, userName, phoneNumber) VALUES ('"+ fName +"', '"+ lName +"', '"+ pw +"', '"+ gender +"', '"+ uName +"', '"+ phNumber + "');";
            stmt.executeUpdate(insert);
        }
        catch (SQLException e) {
            System.err.println("error");
        }
    }
}
