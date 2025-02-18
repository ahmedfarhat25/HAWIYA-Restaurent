package models;

public class Dessert {
    private int dessertId;
    private String name;
    private double price;
    private String description;

    public Dessert(int dessertId, String name, double price, String description) {
        this.dessertId = dessertId;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public int getDessertId() {
        return dessertId;
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

    public void setDessertId(int dessertId) {
        this.dessertId = dessertId;
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
