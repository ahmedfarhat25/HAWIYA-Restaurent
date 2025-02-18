package models;

public class MainDish {
    private int dishId;
    private String name;
    private double price;
    private String description;

    public MainDish(int dishId, String name, double price, String description) {
        this.dishId = dishId;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public int getDishId() {
        return dishId;
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

    public void setDishId(int dishId) {
        this.dishId = dishId;
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
