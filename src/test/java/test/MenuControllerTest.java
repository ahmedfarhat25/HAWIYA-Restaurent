package test;

import control.MenuController;
import models.Dessert;
import models.Drink;
import models.MainDish;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MenuControllerTest {

    @Test
    public void testInitializeMenu() {
        MenuController menuController = new MenuController();
        List<MainDish> mainDishes = menuController.getAllMainDishes();
        List<Drink> drinks = menuController.getAllDrinks();
        List<Dessert> desserts = menuController.getAllDesserts();

        Assertions.assertFalse(mainDishes.isEmpty(), "MainDishes should not be empty");
        Assertions.assertFalse(drinks.isEmpty(), "Drinks should not be empty");
        Assertions.assertFalse(desserts.isEmpty(), "Desserts should not be empty");
    }
}
