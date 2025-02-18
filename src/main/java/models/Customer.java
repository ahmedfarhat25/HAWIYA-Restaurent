package models;

public class Customer extends User {
    private String address;

    public Customer(int userId, String username, String password, String email, String phoneNumber, String address) {
        super(userId, username, password, email, phoneNumber);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
