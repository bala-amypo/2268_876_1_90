package com.example.demo.service.impl;

import com.example.demo.model.TicketCategory;
import com.example.demo.repository.TicketCategoryRepository;
import com.example.demo.service.TicketCategoryService;

public class TicketCategoryServiceImpl implements TicketCategoryService {
    private final TicketCategoryRepository repository;

    public TicketCategoryServiceImpl(TicketCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public TicketCategory createCategory(TicketCategory c) {
        if (c == null || c.getCategoryName() == null || c.getCategoryName().isBlank())
            throw new IllegalArgumentException("category name required");
        if (repository.existsByCategoryName(c.getCategoryName()))
            throw new IllegalArgumentException("category exists");
        return repository.save(c);
    }

    @Override
    public TicketCategory getCategory(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("category not found"));
    }
}
