package com.example.demo.service.impl;

import com.example.demo.model.TicketCategory;
import com.example.demo.repository.TicketCategoryRepository;
import com.example.demo.service.TicketCategoryService;
import com.example.demo.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketCategoryServiceImpl implements TicketCategoryService {

    private final TicketCategoryRepository categoryRepository;

    public TicketCategoryServiceImpl(TicketCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public TicketCategory createCategory(TicketCategory category) {
        boolean exists = categoryRepository.existsByCategoryName(category.getCategoryName());
        if (exists) {
            throw new IllegalArgumentException("Category already exists");
        }

        if (category.getCreatedAt() == null) {
            category.setCreatedAt(LocalDateTime.now());
        }

        return categoryRepository.save(category);
    }

    @Override
    public TicketCategory getCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found"));
    }

    @Override
    public List<TicketCategory> getAllCategories() {
        return categoryRepository.findAll();
    }
}
