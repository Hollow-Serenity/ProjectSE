package Main;

import Main.AddProducts.LiveStock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class AddProductsTest {
    LiveStock cow1 = new LiveStock(1, 1, "05-05-2020", "Cows", 10, 10.00);
    LiveStock chicken1 = new AddProducts.LiveStock(2, 1, "05-05-2020", "Chickens", 10, 10.00);
    LiveStock pig1 = new AddProducts.LiveStock(3, 1, "05-05-2020", "Pigs", 10, 10.00);

    @Test
    void testGetDetails() {
            assertEquals("Cows", cow1.getDetails(), "Added");
            assertEquals("Chickens", chicken1.getDetails(), "Added");
            assertEquals("Pigs", pig1.getDetails(), "Added");
    }

    @Test
    void testGetQuantity() {
        assertEquals(1, cow1.getQuantity(), "Found it!");
        assertEquals(1, chicken1.getQuantity(), "Found it!");
        assertEquals(1, pig1.getQuantity(), "Found it!");
    }

    @Test
    void testGetDateAdded() {
        assertEquals("05-05-2020", cow1.getDateAdded(), "Found it!");
        assertEquals("05-05-2020", chicken1.getDateAdded(), "Found it!");
        assertEquals("05-05-2020", pig1.getDateAdded(), "Found it!");
    }

    @Test
    void testGetWeight(){
        assertEquals(10, cow1.getWeight(), "Weight has been found");
        assertEquals(10, chicken1.getWeight(), "Weight has been found");
        assertEquals(10, pig1.getWeight(), "Weight has been found");
    }

    @Test
    void testGetPrice() {
        assertEquals(10.00, cow1.getPrice(), "Price has been added");
        assertEquals(10.00, chicken1.getPrice(), "Price has been added");
        assertEquals(10.00, pig1.getPrice(), "Price has been added");
    }
}