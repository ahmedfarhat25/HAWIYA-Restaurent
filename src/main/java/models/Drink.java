package models;

public class Drink {
    private int drinkId;
    private String name;
    private double price;
    private String description;

    public Drink(int drinkId, String name, double price, String description) {
        this.drinkId = drinkId;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public int getDrinkId() {
        return drinkId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setDrinkId(int drinkId) {
        this.drinkId = drinkId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return name + " (EGP " + price + ")";
    }
}
