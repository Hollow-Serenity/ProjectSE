package Main;

import UserManagement.Login;
import UserManagement.Registration;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegistrationTest {
    Database db = Database.getDatabase();
    JFXPanel fxPanel = new JFXPanel();
    Registration R = new Registration("TesterFirst", "TesterLast", "TesterUName", "TesterPW", "TesterPW");

    @Test
    @Order(1)
    void testDatabaseInsert() throws SQLException {
        R.databaseInsert();
        Menu.setUName(R.checkCurrentUName());
        R.resetTextFields();
        R.showAccountDetails();
        assertEquals("TesterUName", R.checkCurrentUName(), "checks out");
    }

    @Test
    @Order(2)
    void testUpdateAccountDetails() throws SQLException {
        R.updateTextFields();
        R.updateAccountDetails();
        R.resetTextFields();
        R.showAccountDetails();
        assertEquals("UNameUpdate", R.checkCurrentUName(), "checks out");
    }

    @Test
    @Order(3)
    void testCheckUName() throws SQLException {
        assertEquals(false, R.checkUName("UNameUpdate"));
        assertEquals(true, R.checkUName("TesterUName"));
    }

    @Test
    @Order(4)
    void testCheckPasswordFault() {
        R.checkPasswordFault();
        assertEquals("Your password doesn't match", R.getStatus());

        R.setPW("123456");
        R.checkPasswordFault();
        assertEquals("PW must be between 7 and 20 characters", R.getStatus());

        R.setPW("123456789101112131415");
        R.checkPasswordFault();
        assertEquals("PW must be between 7 and 20 characters", R.getStatus());

        R.setPW("12345678910");
        R.checkPasswordFault();
        assertEquals("Please fill in all fields", R.getStatus());
    }

    @Test
    @Order(5)
    void testCheckPassword() {
        R.setPW("123456789");
        assertEquals(true, R.checkPassword());
    }

    @Test
    @Order(6)
    void testDeleteAccount() throws SQLException {
        R.deleteAccount();
        Menu.setUName(R.checkCurrentUName());
        R.resetTextFields();
        R.showAccountDetails();
        assertEquals("", R.checkCurrentUName(), "checks out");
    }

}
