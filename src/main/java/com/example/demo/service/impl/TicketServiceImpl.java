package com.example.demo.service.impl;

import com.example.demo.model.Ticket;
import com.example.demo.repository.TicketRepository;
import com.example.demo.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // 🔴 THIS WAS MISSING OR WRONG
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket updateTicket(Long id, Ticket ticket) {
        Ticket existing = getTicketById(id);
        existing.setTitle(ticket.getTitle());
        existing.setDescription(ticket.getDescription());
        return ticketRepository.save(existing);
    }

    @Override
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }
}
