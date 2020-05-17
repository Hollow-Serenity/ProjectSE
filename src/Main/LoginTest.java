package Main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;

class TestClass {
    Database db = new Database();
    Registration R = new Registration();

    TestClass() throws Exception {
    }

    @Test
    public void testDBEntry() throws Exception {
        try {
            db.prestatement = db.Connect.prepareStatement("INSERT INTO users VALUES(?,?,?,?)");
            db.prestatement.setString(1, "Tester");
            db.prestatement.setString(2, "testF");
            db.prestatement.setString(3, "testL");
            db.prestatement.setString(4, "Password");
            db.prestatement.executeUpdate();
        }
        catch (SQLException e1) {
            System.out.println("Error while adding user");
        }
    }

    @Test
    public void testCheckUN () {
        assertEquals(false, R.checkUName(db, "Tester"));
        assertEquals(true, R.checkUName(db, "Tester1"));
    }
}