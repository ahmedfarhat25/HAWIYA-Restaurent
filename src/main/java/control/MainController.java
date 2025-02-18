package control;

import models.Admin;
import models.Customer;

/**
 * MainController could handle higher-level application logic,
 * such as switching views, handling login, etc.
 */
public class MainController {
    private Admin adminUser;
    private Customer currentCustomer;

    public MainController() {
        // Example: create a default Admin
        adminUser = new Admin(1, "admin", "admin123", "admin@hawiya.com", "0123456789", "SuperAdmin");
    }

    public void setCurrentCustomer(Customer customer) {
        this.currentCustomer = customer;
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public Admin getAdminUser() {
        return adminUser;
    }
}
