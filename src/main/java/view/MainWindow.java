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
        // Optionally set the system look and feel (if available)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignore) {
            // If not available, ignore
        }

        this.mainController = new MainController();
        initUI();
    }

    private void initUI() {
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // -- Create a default Customer (example) --
        Customer defaultCustomer = new Customer(
                2,
                "john_doe",
                "pass",
                "john@example.com",
                "1234567890",
                "Cairo, Egypt"
        );
        mainController.setCurrentCustomer(defaultCustomer);

        // -- Menu Bar --
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About HAWIYA");
        aboutItem.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "HAWIYA Restaurant System\nVersion 1.0\nEnjoy your meal!",
                        "About",
                        JOptionPane.INFORMATION_MESSAGE
                )
        );
        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        // -- Main Content Panel --
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        JLabel titleLabel = new JLabel("Welcome to HAWIYA Restaurant System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titleLabel);

        // Buttons
        JButton menuButton = new JButton("View Menu");
        JButton orderButton = new JButton("Create Order (With Checkboxes)");
        // If you want a direct checkout button (skipping the confirm flow), keep this:
        // JButton checkoutButton = new JButton("Checkout");

        // "View Menu" -> open MenuPage
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuPage menuPage = new MenuPage();
                menuPage.setVisible(true);
            }
        });
        panel.add(menuButton);

        // "Create Order" -> open the checkbox-based order page
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // If you have a new checkbox-based OrderPage:
                OrderPageWithCheckBoxes orderPage = new OrderPageWithCheckBoxes(mainController.getCurrentCustomer());
                orderPage.setVisible(true);
            }
        });
        panel.add(orderButton);

        // Optional direct checkout button:
        /*
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Typically you'd need an existing Bill with items.
                // If no order has been created, this might be empty.
                CheckoutPage checkoutPage = new CheckoutPage(
                        // If you have an OrderController somewhere, pass its Bill:
                        someOrderController.getCurrentBill()
                );
                checkoutPage.setVisible(true);
            }
        });
        panel.add(checkoutButton);
        */

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow window = new MainWindow();
            window.setVisible(true);
        });
    }
}
