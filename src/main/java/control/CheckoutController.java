package control;

import models.Bill;
import models.Payment;
import models.PaymentMethod;

import java.time.LocalDateTime;

public class CheckoutController {

    public void processPayment(Bill bill, PaymentMethod method) {
        // Simple validation
        if (bill == null) {
            throw new IllegalArgumentException("Bill cannot be null");
        }
        if (bill.getTotalPrice() <= 0) {
            throw new IllegalArgumentException("Total price must be greater than 0 to process payment");
        }

        // Create a Payment
        Payment payment = new Payment(
                2001,
                method,
                bill.getFinalPrice(),
                LocalDateTime.now(),
                "PAID"
        );
        // Attach payment to the bill
        bill.setPayment(payment);
    }
}
