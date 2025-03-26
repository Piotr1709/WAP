package com.uep.wap.diagram;

public abstract class User {
    private int userId;
    private String username;
    private String email;
    private String password;

    public User(int userId, String username, String email, String password) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void createAccount() {
        // Implementation for account creation
    }

    public void login() {
        // Implementation for user login
    }

    public void updateProfile() {
        // Implementation for profile update
    }

    // Getters and setters
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
