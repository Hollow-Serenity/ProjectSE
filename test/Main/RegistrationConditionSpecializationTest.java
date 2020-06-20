package Main;

import Medical.Condition;
import Medical.Specialization;
import UserManagement.Registration;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.ListView;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegistrationConditionSpecializationTest {
    Database db = Database.getDatabase();
    JFXPanel fxPanel = new JFXPanel();
    Registration R = new Registration("TesterFirst", "TesterLast", "TesterUName", "TesterPW", "TesterPW");

    //test if accounts can be created
    @Test
    @Order(1)
    void testDatabaseInsert() throws SQLException {
        R.databaseInsert();
        Menu.setUName(R.checkCurrentUName());
        R.resetTextFields();
        R.showAccountDetails();
        assertEquals("TesterUName", R.checkCurrentUName(), "checks out");
    }

    //test if accounts can be edited
    @Test
    @Order(2)
    void testUpdateAccountDetails() throws SQLException {
        R.updateTextFields();
        R.updateAccountDetails();
        R.resetTextFields();
        R.showAccountDetails();
        assertEquals("UNameUpdate", R.checkCurrentUName(), "checks out");
    }

    //test if the username checker works
    @Test
    @Order(3)
    void testCheckUName() throws SQLException {
        assertEquals(false, R.checkUName("UNameUpdate"));
        assertEquals(true, R.checkUName("TesterUName"));
    }

    //testing of the correct password fault is identified
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

    //test if valid passwords are accepted
    @Test
    @Order(5)
    void testCheckPassword() {
        R.setPW("123456789");
        assertEquals(true, R.checkPassword());
    }

    //test conditions
    //first we test the addCondition method
    //then we use the getConditions method to see if the addCondition worked
    //this way we test both methods at once
    @Test
    @Order(6)
    void testAddCondition() {
        Condition.setTF();
        Condition.addCondition();
        ListView test = new ListView();
        Condition.getConditions("UNameUpdate", test);
        assertEquals(1, test.getItems().size());
    }

    //test if removing the condition works
    @Test
    @Order(7)
    void testRemoveCondition() {
        Condition.removeCondition();
        ListView test = new ListView();
        Condition.getConditions("UNameUpdate", test);
        assertEquals(0, test.getItems().size());
    }

    //test if a specialization can be assigned to a user
    @Test
    @Order(8)
    void testAddSpecialization() {
        Specialization.setSpecializationBox();
        Specialization.addSpecialization("UNameUpdate");
        assertEquals("Cardiology", Specialization.getSpecialization("UNameUpdate"));
    }

    //test if a specialization can be removed
    @Test
    @Order(9)
    void testRemoveSpecialization() {
        Specialization.removeSpecialization("UNameUpdate");
        assertEquals("", Specialization.getSpecialization("UNameUpdate"));
    }

    //test account deletion
    @Test
    @Order(10)
    void testDeleteAccount() throws SQLException {
        R.deleteAccount();
        Menu.setUName(R.checkCurrentUName());
        R.resetTextFields();
        R.showAccountDetails();
        assertEquals("", R.checkCurrentUName(), "checks out");
    }

}
