package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ REQUIRED for User ↔ Ticket mapping
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Long categoryId;
    private String subject;
    private String description;
    private String status;

    // ---------- getters & setters ----------

    public Long getId() {
        return id;
    }

    public User getUser() {          // ✅ REQUIRED
        return user;
    }

    public void setUser(User user) { // ✅ REQUIRED
        this.user = user;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
