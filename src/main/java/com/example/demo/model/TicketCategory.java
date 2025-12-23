package com.example.demo.model;

import java.time.LocalDateTime;

public class TicketCategory {
    private Long id;
    private String categoryName;
    private String description;
    private LocalDateTime createdAt = LocalDateTime.now();

    public TicketCategory() {}

    public TicketCategory(String name, String desc) {
        this.categoryName = name;
        this.description = desc;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}
