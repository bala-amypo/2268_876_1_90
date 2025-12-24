package com.example.demo.service;

import com.example.demo.model.Ticket;

public interface TicketService {
    Ticket createTicket(Long userId, Long categoryId, Ticket t);
    Ticket getTicket(Long id);
    java.util.List<Ticket> getAllTickets();
    java.util.List<Ticket> getTicketsByUser(Long userId);
}
