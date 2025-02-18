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

    @BeforeEach
    public void setUp() {
        Customer customer = new Customer(2, "john_doe", "pass", "john@example.com", "1234567890", "Cairo");
        orderController = new OrderController(customer);
    }

    @Test
    public void testAddMainDish() {
        MainDish dish = new MainDish(1, "Koshari", 35.0, "Egyptian Dish");
        orderController.addMainDishToOrder(dish);

        Assertions.assertEquals(1, orderController.getCurrentBill().getItems().size());
        Assertions.assertEquals(35.0, orderController.getTotalPrice());
    }

    @Test
    public void testAddDrink() {
        Drink drink = new Drink(1, "Hibiscus", 15.0, "Egyptian Drink");
        orderController.addDrinkToOrder(drink);

        Assertions.assertEquals(1, orderController.getCurrentBill().getItems().size());
        Assertions.assertEquals(15.0, orderController.getTotalPrice());
    }

    @Test
    public void testAddDessert() {
        Dessert dessert = new Dessert(1, "Basbousa", 20.0, "Egyptian Dessert");
        orderController.addDessertToOrder(dessert);

        Assertions.assertEquals(1, orderController.getCurrentBill().getItems().size());
        Assertions.assertEquals(20.0, orderController.getTotalPrice());
    }

    @Test
    public void testRemoveItem() {
        MainDish dish = new MainDish(1, "Koshari", 35.0, "Egyptian Dish");
        orderController.addMainDishToOrder(dish);
        orderController.removeItemFromOrder(dish);

        Assertions.assertEquals(0, orderController.getCurrentBill().getItems().size());
    }

    @Test
    public void testDiscount() {
        MainDish dish = new MainDish(1, "Koshari", 35.0, "Egyptian Dish");
        orderController.addMainDishToOrder(dish);
        orderController.applyDiscount(5.0);

        Assertions.assertEquals(30.0, orderController.getFinalPrice());
    }
}
