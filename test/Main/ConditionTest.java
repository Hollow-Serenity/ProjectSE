package Main;

import Medical.Condition;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ConditionTest {
    Database db = Database.getDatabase();
    JFXPanel fxPanel = new JFXPanel();
}
