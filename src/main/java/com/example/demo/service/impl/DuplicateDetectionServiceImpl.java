package com.example.demo.service.impl;

import com.example.demo.model.Ticket;
import com.example.demo.repository.TicketRepository;
import com.example.demo.service.DuplicateDetectionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // ✅ THIS IS MANDATORY
public class DuplicateDetectionServiceImpl implements DuplicateDetectionService {

    private final TicketRepository ticketRepository;

    public DuplicateDetectionServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public boolean isDuplicate(Ticket baseTicket) {
        List<Ticket> openTickets = ticketRepository.findByStatus("OPEN");

        for (Ticket candidate : openTickets) {
            if (candidate.getId() != null &&
                baseTicket.getId() != null &&
                candidate.getId().equals(baseTicket.getId())) {
                continue;
            }

            if (candidate.getSubject() != null &&
                baseTicket.getSubject() != null &&
                candidate.getSubject().equalsIgnoreCase(baseTicket.getSubject())) {
                return true;
            }
        }
        return false;
    }
}
