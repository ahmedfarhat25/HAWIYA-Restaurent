package control;

import models.Dessert;
import models.Drink;
import models.MainDish;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages menu items for HAWIYA Restaurant.
 * Using in-memory lists (no actual DB).
 */
public class MenuController {

    private List<MainDish> mainDishes;
    private List<Drink> drinks;
    private List<Dessert> desserts;

    public MenuController() {
        mainDishes = new ArrayList<>();
        drinks = new ArrayList<>();
        desserts = new ArrayList<>();
        initializeMenu();
    }

    // Populate with some Egyptian foods, drinks, desserts
    private void initializeMenu() {
        // Main Dishes
        mainDishes.add(new MainDish(1, "Koshari", 35.0, "A mix of rice, pasta, lentils, and tomato sauce."));
        mainDishes.add(new MainDish(2, "Ful Medames", 25.0, "Fava beans with spices and oil."));
        mainDishes.add(new MainDish(3, "Molokhia", 40.0, "Green soup made from jute leaves."));
        mainDishes.add(new MainDish(4, "Hawawshi", 45.0, "Spiced ground beef in pita bread."));

        // Drinks
        drinks.add(new Drink(1, "Hibiscus", 15.0, "Karkadeh, a refreshing hibiscus drink."));
        drinks.add(new Drink(2, "Sugarcane Juice", 10.0, "Fresh pressed sugarcane."));
        drinks.add(new Drink(3, "Egyptian Tea", 5.0, "Strong black tea."));

        // Desserts
        desserts.add(new Dessert(1, "Basbousa", 20.0, "Semolina cake soaked in syrup."));
        desserts.add(new Dessert(2, "Kunafa", 25.0, "Shredded filo pastry with sweet filling."));
        desserts.add(new Dessert(3, "Baklava", 30.0, "Layers of filo pastry filled with nuts and honey."));
    }

    public List<MainDish> getAllMainDishes() {
        return mainDishes;
    }

    public List<Drink> getAllDrinks() {
        return drinks;
    }

    public List<Dessert> getAllDesserts() {
        return desserts;
    }

    // Optionally, add methods to add/remove items
    public void addMainDish(MainDish dish) {
        mainDishes.add(dish);
    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public void addDessert(Dessert dessert) {
        desserts.add(dessert);
    }
}
