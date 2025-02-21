package test;

import models.Bill;
import models.Customer;
import models.Dessert;
import models.Drink;
import models.MainDish;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BillTest {

    private Bill bill;
    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer(2, "john_doe", "pass", "john@example.com", "1234567890", "Cairo");
        bill = new Bill(1001, customer);
    }

    @Test
    public void testCalculateTotalWithNoItems() {
        bill.calculateTotal();
        Assertions.assertEquals(0.0, bill.getTotalPrice(), 0.01);
    }

    @Test
    public void testCalculateTotalWithItems() {
        MainDish dish = new MainDish(1, "Koshari", 35.0, "Egyptian Dish");
        Drink drink = new Drink(1, "Hibiscus", 15.0, "Egyptian Drink");
        Dessert dessert = new Dessert(1, "Basbousa", 20.0, "Egyptian Dessert");
        bill.addItem(dish);
        bill.addItem(drink);
        bill.addItem(dessert);
        bill.calculateTotal();
        double expectedTotal = dish.getPrice() + drink.getPrice() + dessert.getPrice();
        Assertions.assertEquals(expectedTotal, bill.getTotalPrice(), 0.01);
    }

    @Test
    public void testApplyDiscount() {
        MainDish dish = new MainDish(1, "Koshari", 35.0, "Egyptian Dish");
        bill.addItem(dish);
        bill.calculateTotal();
        bill.applyDiscount(5.0);
        Assertions.assertEquals(5.0, bill.getDiscount(), 0.01);
        Assertions.assertEquals(30.0, bill.getFinalPrice(), 0.01);
    }

    @Test
    public void testNegativeDiscount() {
        MainDish dish = new MainDish(1, "Koshari", 35.0, "Egyptian Dish");
        bill.addItem(dish);
        bill.calculateTotal();
        // Applying a negative discount results in a final price higher than the total.
        bill.applyDiscount(-5.0);
        Assertions.assertEquals(-5.0, bill.getDiscount(), 0.01);
        Assertions.assertEquals(40.0, bill.getFinalPrice(), 0.01);
    }

    @Test
    public void testDiscountEqualTotal() {
        MainDish dish = new MainDish(1, "Koshari", 35.0, "Egyptian Dish");
        bill.addItem(dish);
        bill.calculateTotal();
        bill.applyDiscount(35.0);
        Assertions.assertEquals(0.0, bill.getFinalPrice(), 0.01);
    }
}
