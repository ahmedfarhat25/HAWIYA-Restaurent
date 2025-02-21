package view;

import control.MenuController;
import control.OrderController;
import models.Customer;
import models.Dessert;
import models.Drink;
import models.MainDish;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OrderPageWithCheckBoxes extends JFrame {

    private MenuController menuController;
    private OrderController orderController;

    // Panels to hold checkboxes
    private JPanel mainDishPanel;
    private JPanel drinkPanel;
    private JPanel dessertPanel;

    // We’ll store checkbox references so we can see which are selected
    private java.util.List<JCheckBox> mainDishCheckBoxes;
    private java.util.List<JCheckBox> drinkCheckBoxes;
    private java.util.List<JCheckBox> dessertCheckBoxes;

    public OrderPageWithCheckBoxes(Customer customer) {
        super("HAWIYA - Order");
        this.menuController = new MenuController(); // fetch menu items
        this.orderController = new OrderController(customer); // create a new Bill for the customer

        mainDishCheckBoxes = new ArrayList<>();
        drinkCheckBoxes = new ArrayList<>();
        dessertCheckBoxes = new ArrayList<>();

        initUI();
    }

    private void initUI() {
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Select Items to Order (HAWIYA)", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        // Center panel with tabbed or split layout
        JTabbedPane tabbedPane = new JTabbedPane();

        // 1. Main Dishes
        mainDishPanel = new JPanel();
        mainDishPanel.setLayout(new BoxLayout(mainDishPanel, BoxLayout.Y_AXIS));
        for (MainDish dish : menuController.getAllMainDishes()) {
            JCheckBox checkBox = new JCheckBox(dish.getName() + " (EGP " + dish.getPrice() + ")");
            checkBox.putClientProperty("item", dish);
            mainDishPanel.add(checkBox);
            mainDishCheckBoxes.add(checkBox);
        }
        tabbedPane.add("Main Dishes", new JScrollPane(mainDishPanel));

        // 2. Drinks
        drinkPanel = new JPanel();
        drinkPanel.setLayout(new BoxLayout(drinkPanel, BoxLayout.Y_AXIS));
        for (Drink drink : menuController.getAllDrinks()) {
            JCheckBox checkBox = new JCheckBox(drink.getName() + " (EGP " + drink.getPrice() + ")");
            checkBox.putClientProperty("item", drink);
            drinkPanel.add(checkBox);
            drinkCheckBoxes.add(checkBox);
        }
        tabbedPane.add("Drinks", new JScrollPane(drinkPanel));

        // 3. Desserts
        dessertPanel = new JPanel();
        dessertPanel.setLayout(new BoxLayout(dessertPanel, BoxLayout.Y_AXIS));
        for (Dessert dessert : menuController.getAllDesserts()) {
            JCheckBox checkBox = new JCheckBox(dessert.getName() + " (EGP " + dessert.getPrice() + ")");
            checkBox.putClientProperty("item", dessert);
            dessertPanel.add(checkBox);
            dessertCheckBoxes.add(checkBox);
        }
        tabbedPane.add("Desserts", new JScrollPane(dessertPanel));

        add(tabbedPane, BorderLayout.CENTER);

        // South panel with buttons
        JPanel southPanel = new JPanel(new FlowLayout());

        JButton addSelectedButton = new JButton("Add Selected Items");
        addSelectedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSelectedItemsToOrder();
                JOptionPane.showMessageDialog(
                        OrderPageWithCheckBoxes.this,
                        "Selected items have been added to the order!"
                );
            }
        });

        JButton confirmOrderButton = new JButton("Confirm Order");
        confirmOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show confirm order page
                ConfirmOrderPage confirmPage = new ConfirmOrderPage(orderController);
                confirmPage.setVisible(true);
            }
        });

        southPanel.add(addSelectedButton);
        southPanel.add(confirmOrderButton);

        add(southPanel, BorderLayout.SOUTH);
    }

    private void addSelectedItemsToOrder() {
        // Add main dishes
        for (JCheckBox cb : mainDishCheckBoxes) {
            if (cb.isSelected()) {
                MainDish dish = (MainDish) cb.getClientProperty("item");
                orderController.addMainDishToOrder(dish);
                // Optionally uncheck after adding
                cb.setSelected(false);
            }
        }

        // Add drinks
        for (JCheckBox cb : drinkCheckBoxes) {
            if (cb.isSelected()) {
                Drink drink = (Drink) cb.getClientProperty("item");
                orderController.addDrinkToOrder(drink);
                cb.setSelected(false);
            }
        }

        // Add desserts
        for (JCheckBox cb : dessertCheckBoxes) {
            if (cb.isSelected()) {
                Dessert dessert = (Dessert) cb.getClientProperty("item");
                orderController.addDessertToOrder(dessert);
                cb.setSelected(false);
            }
        }
    }

    // Expose the orderController if needed
    public OrderController getOrderController() {
        return orderController;
    }
}
