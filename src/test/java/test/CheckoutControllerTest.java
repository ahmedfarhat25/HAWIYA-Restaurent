package test;

import control.CheckoutController;
import control.OrderController;
import models.Bill;
import models.Customer;
import models.MainDish;
import models.Payment;
import models.PaymentMethod;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CheckoutControllerTest {

    private CheckoutController checkoutController;
    private OrderController orderController;
    private Customer customer;

    @BeforeEach
    public void setUp() {
        checkoutController = new CheckoutController();
        customer = new Customer(2, "john_doe", "pass", "john@example.com", "1234567890", "Cairo");
        orderController = new OrderController(customer);
    }

    @Test
    public void testProcessPaymentSuccess() {
        // Add an item so the total price is greater than zero.
        MainDish dish = new MainDish(1, "Koshari", 35.0, "Egyptian Dish");
        orderController.addMainDishToOrder(dish);
        Bill bill = orderController.getCurrentBill();
        checkoutController.processPayment(bill, PaymentMethod.CREDIT_CARD);
        Payment payment = bill.getPayment();
        Assertions.assertNotNull(payment, "Payment should be processed");
        Assertions.assertEquals("PAID", payment.getStatus());
        Assertions.assertEquals(bill.getFinalPrice(), payment.getAmount(), 0.01);
    }

    @Test
    public void testProcessPaymentWithNullBill() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                checkoutController.processPayment(null, PaymentMethod.CASH)
        );
        Assertions.assertEquals("Bill cannot be null", exception.getMessage());
    }

    @Test
    public void testProcessPaymentWithZeroTotal() {
        // Bill has no items so total price is 0.
        Bill bill = orderController.getCurrentBill();
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                checkoutController.processPayment(bill, PaymentMethod.CASH)
        );
        Assertions.assertEquals("Total price must be greater than 0 to process payment", exception.getMessage());
    }
}
