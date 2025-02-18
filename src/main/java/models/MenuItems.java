package models;

import jdk.jfr.Category;

public abstract class MenuItems {
    protected int itemID;
    protected String name;
    protected Category category;
    protected double price;
    protected String itemName;

    public MenuItems(int itemID, String name, Category category, double price, String itemName) {
        this.itemID = itemID;
        this.name = name;
        this.category = category;
        this.price = price;
        this.itemName = itemName;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}