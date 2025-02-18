package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Bill {
    private int billId;
    private List<Object> items; // Could store Drinks, MainDishes, Desserts as Objects or a common interface
    private double totalPrice;
    private double discount;
    private double finalPrice;
    private LocalDateTime date;
    private Customer customer;
    private Payment payment;

    public Bill(int billId, Customer customer) {
        this.billId = billId;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.date = LocalDateTime.now();
    }

    public int getBillId() {
        return billId;
    }

    public List<Object> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Payment getPayment() {
        return payment;
    }

    public void addItem(Object item) {
        items.add(item);
    }

    public void removeItem(Object item) {
        items.remove(item);
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void calculateTotal() {
        double sum = 0.0;
        for (Object item : items) {
            if (item instanceof MainDish) {
                sum += ((MainDish) item).getPrice();
            } else if (item instanceof Drink) {
                sum += ((Drink) item).getPrice();
            } else if (item instanceof Dessert) {
                sum += ((Dessert) item).getPrice();
            }
        }
        this.totalPrice = sum;
        this.finalPrice = totalPrice - discount;
    }

    public void applyDiscount(double discount) {
        this.discount = discount;
        this.finalPrice = totalPrice - discount;
    }
}
