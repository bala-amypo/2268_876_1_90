package com.example.demo.service.impl;

import com.example.demo.model.Ticket;
import com.example.demo.model.TicketCategory;
import com.example.demo.model.User;
import com.example.demo.repository.TicketCategoryRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TicketService;

import java.util.List;

public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final TicketCategoryRepository categoryRepository;

    public TicketServiceImpl(TicketRepository ticketRepository, UserRepository userRepository, TicketCategoryRepository categoryRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Ticket createTicket(Long userId, Long categoryId, Ticket t) {
        User u = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
        TicketCategory c = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("category not found"));
        if (t.getDescription() == null || t.getDescription().length() < 10) {
            throw new IllegalArgumentException("description too short");
        }
        t.setUser(u);
        t.setCategory(c);
        return ticketRepository.save(t);
    }

    @Override
    public Ticket getTicket(Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("ticket not found"));
    }

    @Override
    public List<Ticket> getAllTickets() {
        List<Ticket> list = ticketRepository.findAll();
        return list == null ? java.util.List.of() : list;
    }

    @Override
    public List<Ticket> getTicketsByUser(Long userId) {
        List<Ticket> list = ticketRepository.findByUser_Id(userId);
        return list == null ? java.util.List.of() : list;
    }
}
