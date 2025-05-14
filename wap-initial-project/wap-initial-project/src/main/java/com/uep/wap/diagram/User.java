package com.uep.wap.diagram;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;
    private String email;
    private String password;
    
    public User() {
        // Default constructor required by JPA
    }
    
    public User(Long userId, String username, String email, String password) {
        this.id = userId;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String email, String password) {
    }

    // Business methods
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
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
