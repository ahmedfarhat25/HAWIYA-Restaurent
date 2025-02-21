package test;

import control.MenuController;
import models.Dessert;
import models.Drink;
import models.MainDish;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MenuControllerTest {

    private MenuController menuController;

    @BeforeEach
    public void setUp() {
        menuController = new MenuController();
    }

    @Test
    public void testInitializeMenu() {
        // Ensure the initial lists are not empty.
        List<MainDish> mainDishes = menuController.getAllMainDishes();
        List<Drink> drinks = menuController.getAllDrinks();
        List<Dessert> desserts = menuController.getAllDesserts();

        Assertions.assertFalse(mainDishes.isEmpty(), "Main Dishes should not be empty");
        Assertions.assertFalse(drinks.isEmpty(), "Drinks should not be empty");
        Assertions.assertFalse(desserts.isEmpty(), "Desserts should not be empty");
    }

    @Test
    public void testAddMainDish() {
        int initialSize = menuController.getAllMainDishes().size();
        MainDish newDish = new MainDish(10, "Test Dish", 50.0, "Test Description");
        menuController.addMainDish(newDish);
        Assertions.assertEquals(initialSize + 1, menuController.getAllMainDishes().size());
        Assertions.assertTrue(menuController.getAllMainDishes().contains(newDish));
    }

    @Test
    public void testAddDrink() {
        int initialSize = menuController.getAllDrinks().size();
        Drink newDrink = new Drink(10, "Test Drink", 20.0, "Test Description");
        menuController.addDrink(newDrink);
        Assertions.assertEquals(initialSize + 1, menuController.getAllDrinks().size());
        Assertions.assertTrue(menuController.getAllDrinks().contains(newDrink));
    }

    @Test
    public void testAddDessert() {
        int initialSize = menuController.getAllDesserts().size();
        Dessert newDessert = new Dessert(10, "Test Dessert", 30.0, "Test Description");
        menuController.addDessert(newDessert);
        Assertions.assertEquals(initialSize + 1, menuController.getAllDesserts().size());
        Assertions.assertTrue(menuController.getAllDesserts().contains(newDessert));
    }
}
