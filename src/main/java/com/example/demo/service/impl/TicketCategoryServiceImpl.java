package com.example.demo.service.impl;

import com.example.demo.model.TicketCategory;
import com.example.demo.repository.TicketCategoryRepository;
import com.example.demo.service.TicketCategoryService;

public class TicketCategoryServiceImpl implements TicketCategoryService {

    private final TicketCategoryRepository repo;

    public TicketCategoryServiceImpl(TicketCategoryRepository repo) {
        this.repo = repo;
    }

    public TicketCategory createCategory(TicketCategory c) {
        if (repo.existsByCategoryName(c.getCategoryName()))
            throw new RuntimeException("Category exists");
        return repo.save(c);
    }

    public TicketCategory getCategory(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }
}
