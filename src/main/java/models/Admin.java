package models;

public class Admin extends User {
    private String adminRole;

    public Admin(int userId, String username, String password, String email, String phoneNumber, String adminRole) {
        super(userId, username, password, email, phoneNumber);
        this.adminRole = adminRole;
    }

    public String getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(String adminRole) {
        this.adminRole = adminRole;
    }
}
