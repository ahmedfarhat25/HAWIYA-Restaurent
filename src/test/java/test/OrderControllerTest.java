package test;

import control.OrderController;
import models.Customer;
import models.Dessert;
import models.Drink;
import models.MainDish;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderControllerTest {

    private OrderController orderController;
    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer(2, "john_doe", "pass", "john@example.com", "1234567890", "Cairo");
        orderController = new OrderController(customer);
    }

    @Test
    public void testAddMainDish() {
        MainDish dish = new MainDish(1, "Koshari", 35.0, "Egyptian Dish");
        orderController.addMainDishToOrder(dish);
        Assertions.assertEquals(1, orderController.getCurrentBill().getItems().size());
        Assertions.assertEquals(35.0, orderController.getTotalPrice(), 0.01);
    }

    @Test
    public void testAddDrink() {
        Drink drink = new Drink(1, "Hibiscus", 15.0, "Egyptian Drink");
        orderController.addDrinkToOrder(drink);
        Assertions.assertEquals(1, orderController.getCurrentBill().getItems().size());
        Assertions.assertEquals(15.0, orderController.getTotalPrice(), 0.01);
    }

    @Test
    public void testAddDessert() {
        Dessert dessert = new Dessert(1, "Basbousa", 20.0, "Egyptian Dessert");
        orderController.addDessertToOrder(dessert);
        Assertions.assertEquals(1, orderController.getCurrentBill().getItems().size());
        Assertions.assertEquals(20.0, orderController.getTotalPrice(), 0.01);
    }

    @Test
    public void testRemoveItem() {
        MainDish dish = new MainDish(1, "Koshari", 35.0, "Egyptian Dish");
        orderController.addMainDishToOrder(dish);
        orderController.removeItemFromOrder(dish);
        Assertions.assertEquals(0, orderController.getCurrentBill().getItems().size());
    }

    @Test
    public void testApplyDiscount() {
        MainDish dish = new MainDish(1, "Koshari", 35.0, "Egyptian Dish");
        orderController.addMainDishToOrder(dish);
        orderController.applyDiscount(5.0);
        Assertions.assertEquals(30.0, orderController.getFinalPrice(), 0.01);
    }

    @Test
    public void testMultipleItemsAndDiscount() {
        MainDish dish1 = new MainDish(1, "Koshari", 35.0, "Dish 1");
        Drink drink1 = new Drink(1, "Hibiscus", 15.0, "Drink 1");
        Dessert dessert1 = new Dessert(1, "Basbousa", 20.0, "Dessert 1");
        orderController.addMainDishToOrder(dish1);
        orderController.addDrinkToOrder(drink1);
        orderController.addDessertToOrder(dessert1);
        Assertions.assertEquals(3, orderController.getCurrentBill().getItems().size());
        double total = dish1.getPrice() + drink1.getPrice() + dessert1.getPrice();
        Assertions.assertEquals(total, orderController.getTotalPrice(), 0.01);
        // Edge case: discount equals total so final price becomes zero.
        orderController.applyDiscount(total);
        Assertions.assertEquals(0.0, orderController.getFinalPrice(), 0.01);
    }

    @Test
    public void testRemoveItemEdgeCase() {
        // Removing an item that was never added should not throw an exception.
        try {
            orderController.removeItemFromOrder(new MainDish(99, "Non-existent", 50.0, "Not in order"));
        } catch (Exception e) {
            Assertions.fail("Removing a non-existent item should not throw an exception");
        }
    }
}
