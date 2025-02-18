package control;

import models.Bill;
import models.Customer;
import models.Dessert;
import models.Drink;
import models.MainDish;

public class OrderController {
    private Bill currentBill;

    public OrderController(Customer customer) {
        // In a real system, we might generate an ID from a sequence or DB
        this.currentBill = new Bill(1001, customer);
    }

    public Bill getCurrentBill() {
        return currentBill;
    }

    public void addMainDishToOrder(MainDish dish) {
        if (dish != null) {
            currentBill.addItem(dish);
            currentBill.calculateTotal();
        }
    }

    public void addDrinkToOrder(Drink drink) {
        if (drink != null) {
            currentBill.addItem(drink);
            currentBill.calculateTotal();
        }
    }

    public void addDessertToOrder(Dessert dessert) {
        if (dessert != null) {
            currentBill.addItem(dessert);
            currentBill.calculateTotal();
        }
    }

    public void removeItemFromOrder(Object item) {
        currentBill.removeItem(item);
        currentBill.calculateTotal();
    }

    public double getTotalPrice() {
        return currentBill.getTotalPrice();
    }

    public double getFinalPrice() {
        return currentBill.getFinalPrice();
    }

    public void applyDiscount(double discount) {
        currentBill.applyDiscount(discount);
    }
}
