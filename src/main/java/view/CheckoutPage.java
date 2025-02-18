package view;

import control.CheckoutController;
import control.OrderController;
import models.Bill;
import models.Customer;
import models.PaymentMethod;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Allows user to select a payment method and finalize the order.
 * In a real system, you'd pass in the existing Bill or OrderController.
 */
public class CheckoutPage extends JFrame {
    private CheckoutController checkoutController;
    private OrderController orderController;
    private JComboBox<PaymentMethod> paymentMethodCombo;
    private JLabel statusLabel;

    public CheckoutPage(Customer customer) {
        super("HAWIYA - Checkout");
        // For simplicity, create a new order or retrieve from somewhere
        // Typically you'd share an existing OrderController.
        this.orderController = new OrderController(customer);
        // For demonstration, let's just pretend the order already has items:
        // (No items means total=0, which won't pass validation.)
        // this.orderController.addMainDishToOrder(...);

        this.checkoutController = new CheckoutController();
        initUI();
    }

    private void initUI() {
        setSize(400, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        JLabel label = new JLabel("Select Payment Method", SwingConstants.CENTER);

        paymentMethodCombo = new JComboBox<>(PaymentMethod.values());
        JButton payButton = new JButton("Pay");
        statusLabel = new JLabel("", SwingConstants.CENTER);

        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Bill bill = orderController.getCurrentBill();
                if (bill.getItems().isEmpty()) {
                    statusLabel.setText("Error: No items in the order!");
                    return;
                }
                try {
                    checkoutController.processPayment(bill, (PaymentMethod) paymentMethodCombo.getSelectedItem());
                    statusLabel.setText("Payment Successful. Status: " + bill.getPayment().getStatus());
                } catch (IllegalArgumentException ex) {
                    statusLabel.setText("Error: " + ex.getMessage());
                }
            }
        });

        panel.add(label);
        panel.add(paymentMethodCombo);
        panel.add(payButton);

        add(panel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
    }
}
