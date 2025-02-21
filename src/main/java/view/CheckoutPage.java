package view;

import control.CheckoutController;
import models.Bill;
import models.PaymentMethod;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Allows user to select a payment method and finalize the order.
 * Now it accepts an existing Bill (with items already added).
 */
public class CheckoutPage extends JFrame {
    private CheckoutController checkoutController;
    private Bill bill;
    private JComboBox<PaymentMethod> paymentMethodCombo;
    private JLabel statusLabel;

    public CheckoutPage(Bill bill) {
        super("HAWIYA - Checkout");
        this.checkoutController = new CheckoutController();
        this.bill = bill;
        initUI();
    }

    private void initUI() {
        setSize(400, 250);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        JLabel titleLabel = new JLabel("Checkout - HAWIYA", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel infoLabel = new JLabel(
                "Order Total: EGP " + bill.getTotalPrice()
                        + " | Final: EGP " + bill.getFinalPrice(),
                SwingConstants.CENTER
        );

        paymentMethodCombo = new JComboBox<>(PaymentMethod.values());
        JButton payButton = new JButton("Pay");
        statusLabel = new JLabel("", SwingConstants.CENTER);

        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

        panel.add(titleLabel);
        panel.add(infoLabel);
        panel.add(paymentMethodCombo);
        panel.add(payButton);

        add(panel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
    }
}
