
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class SILab2Test {

    @Test
    public void test_NullItemList_ThrowsException() {
        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(null, "1234567890123456");
        });
        assertEquals("allItems list can't be null!", ex.getMessage());
    }

    @Test
    public void test_InvalidItemName_ThrowsException() {
        List<Item> items = List.of(new Item("", 1, 100, 0.0));
        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(items, "1234567890123456");
        });
        assertEquals("Invalid item!", ex.getMessage());
    }

    @Test
    public void test_DiscountedItem_TriggersPenalty() {
        List<Item> items = List.of(new Item("Laptop", 11, 400, 0.1));
        double result = SILab2.checkCart(items, "1234567890123456");
        double expected = (400 * (1 - 0.1) * 11) - 30;
        assertEquals(expected, result);
    }

    @Test
    public void test_ItemWithoutDiscount() {
        List<Item> items = List.of(new Item("Mouse", 2, 50, 0.0));
        double result = SILab2.checkCart(items, "1234567890123456");
        assertEquals(100.0, result);
    }

    @Test
    public void test_InvalidCardNumber_Length() {
        List<Item> items = List.of(new Item("Keyboard", 1, 100, 0.0));
        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(items, "12345678");  // too short
        });
        assertEquals("Invalid card number!", ex.getMessage());
    }

    @Test
    public void test_InvalidCardNumber_Character() {
        List<Item> items = List.of(new Item("Monitor", 1, 100, 0.0));
        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(items, "12345678901234a6");  // invalid character 'a'
        });
        assertEquals("Invalid character in card number!", ex.getMessage());
    }

    // Multiple Condition combinations (for if (price > 300 || discount > 0 || quantity > 10))
    // price > 300: false, discount > 0: false, quantity > 10: false
    @Test
    public void test_Condition_FalseFalseFalse() {
        List<Item> items = List.of(new Item("Item", 5, 100, 0.0));
        double result = SILab2.checkCart(items, "1234567890123456");
        assertEquals(500.0, result);  // no penalty, no discount
    }

    // price > 300: false, discount > 0: false, quantity > 10: true
    @Test
    public void test_Condition_FalseFalseTrue() {
        List<Item> items = List.of(new Item("Item", 11, 100, 0.0));
        double result = SILab2.checkCart(items, "1234567890123456");
        double expected = (100 * 11) - 30;
        assertEquals(expected, result);
    }

    // price > 300: false, discount > 0: true, quantity > 10: false
    @Test
    public void test_Condition_FalseTrueFalse() {
        List<Item> items = List.of(new Item("Item", 5, 100, 0.1));
        double result = SILab2.checkCart(items, "1234567890123456");
        double expected = (100 * 0.9 * 5) - 30;
        assertEquals(expected, result);
    }

    // price > 300: false, discount > 0: true, quantity > 10: true
    @Test
    public void test_Condition_FalseTrueTrue() {
        List<Item> items = List.of(new Item("Item", 15, 100, 0.2));
        double result = SILab2.checkCart(items, "1234567890123456");
        double expected = (100 * 0.8 * 15) - 30;
        assertEquals(expected, result);
    }

    // price > 300: true, discount > 0: false, quantity > 10: false
    @Test
    public void test_Condition_TrueFalseFalse() {
        List<Item> items = List.of(new Item("Item", 5, 400, 0.0));
        double result = SILab2.checkCart(items, "1234567890123456");
        double expected = (400 * 5) - 30;
        assertEquals(expected, result);
    }

    // price > 300: true, discount > 0: false, quantity > 10: true
    @Test
    public void test_Condition_TrueFalseTrue() {
        List<Item> items = List.of(new Item("Item", 15, 400, 0.0));
        double result = SILab2.checkCart(items, "1234567890123456");
        double expected = (400 * 15) - 30;
        assertEquals(expected, result);
    }

    // price > 300: true, discount > 0: true, quantity > 10: false
    @Test
    public void test_Condition_TrueTrueFalse() {
        List<Item> items = List.of(new Item("Item", 5, 400, 0.1));
        double result = SILab2.checkCart(items, "1234567890123456");
        double expected = (400 * 0.9 * 5) - 30;
        assertEquals(expected, result);
    }

    // price > 300: true, discount > 0: true, quantity > 10: true
    @Test
    public void test_Condition_TrueTrueTrue() {
        List<Item> items = List.of(new Item("Item", 20, 500, 0.2));
        double result = SILab2.checkCart(items, "1234567890123456");
        double expected = (500 * 0.8 * 20) - 30;
        assertEquals(expected, result);
    }
}
