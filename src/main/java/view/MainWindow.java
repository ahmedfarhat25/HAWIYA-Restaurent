package view;

import control.MainController;
import models.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main Window for HAWIYA restaurant system.
 */
public class MainWindow extends JFrame {
    private MainController mainController;

    public MainWindow() {
        super("HAWIYA - Main Window");
        this.mainController = new MainController();
        initUI();
    }

    private void initUI() {
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Branding label
        JLabel titleLabel = new JLabel("Welcome to HAWIYA Restaurant System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Buttons
        JButton menuButton = new JButton("View Menu");
        JButton orderButton = new JButton("Create Order");
        JButton checkoutButton = new JButton("Checkout");

        // Create a default Customer to use in this example
        Customer defaultCustomer = new Customer(2, "john_doe", "pass", "john@example.com", "1234567890", "Cairo, Egypt");
        mainController.setCurrentCustomer(defaultCustomer);

        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuPage menuPage = new MenuPage();
                menuPage.setVisible(true);
            }
        });

        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderPage orderPage = new OrderPage(mainController.getCurrentCustomer());
                orderPage.setVisible(true);
            }
        });

        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckoutPage checkoutPage = new CheckoutPage(mainController.getCurrentCustomer());
                checkoutPage.setVisible(true);
            }
        });

        // Layout
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.add(titleLabel);
        panel.add(menuButton);
        panel.add(orderButton);
        panel.add(checkoutButton);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow window = new MainWindow();
            window.setVisible(true);
        });
    }
}
