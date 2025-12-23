package com.example.demo.model;

import java.time.LocalDateTime;

public class User {
    private Long id;
    private String fullName;
    private String email;
    private String password;
    private String role;
    private LocalDateTime createdAt = LocalDateTime.now();

    public User() {}

    public User(String fullName, String email, String password, String role) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = role == null ? "USER" : role;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public String getRole() { return role == null ? "USER" : role; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}
