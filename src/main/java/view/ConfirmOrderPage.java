package view;

import control.OrderController;
import models.Bill;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Shows a summary of the current order and allows user to proceed to payment.
 */
public class ConfirmOrderPage extends JFrame {
    private OrderController orderController;
    private JTextArea orderSummaryArea;
    private JTextField discountField;

    public ConfirmOrderPage(OrderController orderController) {
        super("Confirm Order - HAWIYA");
        this.orderController = orderController;
        initUI();
    }

    private void initUI() {
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Confirm Your Order", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        // Center area: order summary
        orderSummaryArea = new JTextArea();
        orderSummaryArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(orderSummaryArea);
        add(scrollPane, BorderLayout.CENTER);

        // Bottom panel: discount + proceed
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(new JLabel("Discount:"));
        discountField = new JTextField(5);
        bottomPanel.add(discountField);

        JButton applyDiscountButton = new JButton("Apply Discount");
        applyDiscountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double discount = Double.parseDouble(discountField.getText());
                    orderController.applyDiscount(discount);
                    updateOrderSummary();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                            ConfirmOrderPage.this,
                            "Invalid discount value!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
        bottomPanel.add(applyDiscountButton);

        JButton proceedToPaymentButton = new JButton("Proceed to Payment");
        proceedToPaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Bill bill = orderController.getCurrentBill();
                if (bill.getItems().isEmpty()) {
                    JOptionPane.showMessageDialog(
                            ConfirmOrderPage.this,
                            "No items in the order!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                // Open CheckoutPage with the existing Bill
                CheckoutPage checkoutPage = new CheckoutPage(bill);
                checkoutPage.setVisible(true);
            }
        });
        bottomPanel.add(proceedToPaymentButton);

        add(bottomPanel, BorderLayout.SOUTH);

        updateOrderSummary();
    }

    private void updateOrderSummary() {
        Bill bill = orderController.getCurrentBill();
        bill.calculateTotal(); // Recalculate to ensure total is up-to-date

        StringBuilder sb = new StringBuilder();
        sb.append("Items in Order:\n");
        if (bill.getItems().isEmpty()) {
            sb.append("No items.\n");
        } else {
            for (Object item : bill.getItems()) {
                sb.append(" - ").append(item.toString()).append("\n");
            }
        }
        sb.append("\nTotal Price: EGP ").append(bill.getTotalPrice());
        sb.append("\nDiscount: EGP ").append(bill.getDiscount());
        sb.append("\nFinal Price: EGP ").append(bill.getFinalPrice());

        orderSummaryArea.setText(sb.toString());
    }
}
