package view;

import control.OrderController;
import control.MenuController;
import models.Customer;
import models.Dessert;
import models.Drink;
import models.MainDish;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Allows user to create an order by adding items from the menu.
 */
public class OrderPage extends JFrame {
    private OrderController orderController;
    private MenuController menuController;
    private JTextArea orderTextArea;

    public OrderPage(Customer customer) {
        super("HAWIYA - Order");
        this.menuController = new MenuController();
        this.orderController = new OrderController(customer);
        initUI();
    }

    private void initUI() {
        setSize(700, 500);
        setLocationRelativeTo(null);

        // Panels
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());
        orderTextArea = new JTextArea();
        orderTextArea.setEditable(false);

        // Buttons for adding items
        JButton addMainDishBtn = new JButton("Add Main Dish");
        JButton addDrinkBtn = new JButton("Add Drink");
        JButton addDessertBtn = new JButton("Add Dessert");
        JButton removeItemBtn = new JButton("Remove Last Item");
        JButton refreshBtn = new JButton("Refresh Order");

        addMainDishBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // For simplicity, add the first main dish
                if (!menuController.getAllMainDishes().isEmpty()) {
                    MainDish dish = menuController.getAllMainDishes().get(0);
                    orderController.addMainDishToOrder(dish);
                    updateOrderText();
                }
            }
        });

        addDrinkBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // For simplicity, add the first drink
                if (!menuController.getAllDrinks().isEmpty()) {
                    Drink drink = menuController.getAllDrinks().get(0);
                    orderController.addDrinkToOrder(drink);
                    updateOrderText();
                }
            }
        });

        addDessertBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // For simplicity, add the first dessert
                if (!menuController.getAllDesserts().isEmpty()) {
                    Dessert dessert = menuController.getAllDesserts().get(0);
                    orderController.addDessertToOrder(dessert);
                    updateOrderText();
                }
            }
        });

        removeItemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove last item from the bill if any
                if (!orderController.getCurrentBill().getItems().isEmpty()) {
                    Object lastItem = orderController.getCurrentBill().getItems()
                            .get(orderController.getCurrentBill().getItems().size() - 1);
                    orderController.removeItemFromOrder(lastItem);
                    updateOrderText();
                }
            }
        });

        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateOrderText();
            }
        });

        buttonPanel.add(addMainDishBtn);
        buttonPanel.add(addDrinkBtn);
        buttonPanel.add(addDessertBtn);
        buttonPanel.add(removeItemBtn);
        buttonPanel.add(refreshBtn);

        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(orderTextArea), BorderLayout.CENTER);

        add(mainPanel);
        updateOrderText();
    }

    private void updateOrderText() {
        StringBuilder sb = new StringBuilder();
        sb.append("Current Order:\n");
        if (orderController.getCurrentBill().getItems().isEmpty()) {
            sb.append("No items.\n");
        } else {
            for (Object item : orderController.getCurrentBill().getItems()) {
                sb.append(" - ").append(item.toString()).append("\n");
            }
        }
        sb.append("\nTotal: EGP ").append(orderController.getTotalPrice());
        sb.append("\nFinal (after discount): EGP ").append(orderController.getFinalPrice());
        orderTextArea.setText(sb.toString());
    }
}
