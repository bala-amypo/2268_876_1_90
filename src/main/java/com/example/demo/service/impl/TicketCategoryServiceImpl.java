package com.example.demo.service.impl;

import com.example.demo.model.TicketCategory;
import com.example.demo.repository.TicketCategoryRepository;
import com.example.demo.service.TicketCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketCategoryServiceImpl implements TicketCategoryService {

    private final TicketCategoryRepository ticketCategoryRepository;

    public TicketCategoryServiceImpl(TicketCategoryRepository ticketCategoryRepository) {
        this.ticketCategoryRepository = ticketCategoryRepository;
    }

    @Override
    public List<TicketCategory> getAllCategories() {
        return ticketCategoryRepository.findAll();
    }
}
