package UserManagement;

import Main.Menu;

import java.sql.SQLException;

public class User {
    private static String userName = Menu.getUName();
    private static String firstName;
    private static String lastName;
    private static String password;
    private static ManageContact manageContact;

    static {
        try {
            manageContact = new ManageContact();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User() {
    }

    public static void addPerson(Person person) throws SQLException {
        manageContact.addContactPersonTo(userName, person);
    }

    public static void addCompany(Company company) throws SQLException {
        manageContact.addContactCompanyTo(userName, company);
    }

    public static String getUserName() {
        return userName;
    }
    public static void setUserName(String userName) {
        User.userName = userName;
    }

    public static String getFirstName() {
        return firstName;
    }
    public static void setFirstName(String firstName) {
        User.firstName = firstName;
    }

    public static String getLastName() {
        return lastName;
    }
    public static void setLastName(String lastName) {
        User.lastName = lastName;
    }

    public static String getPassword() {
        return password;
    }
    public static void setPassword(String password) {
        User.password = password;
    }
}
