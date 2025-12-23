package com.example.demo.service.impl;

import com.example.demo.model.DuplicateLog;
import com.example.demo.model.Ticket;
import com.example.demo.repository.DuplicateLogRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.service.DuplicateDetectionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DuplicateDetectionServiceImpl implements DuplicateDetectionService {

    private final TicketRepository ticketRepository;
    private final DuplicateLogRepository duplicateLogRepository;

    public DuplicateDetectionServiceImpl(
            TicketRepository ticketRepository,
            DuplicateLogRepository duplicateLogRepository) {
        this.ticketRepository = ticketRepository;
        this.duplicateLogRepository = duplicateLogRepository;
    }

    @Override
    public void detectDuplicates(Long ticketId) {
        Ticket baseTicket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        List<Ticket> openTickets = ticketRepository.findByStatus("OPEN");

        for (Ticket candidate : openTickets) {
            if (candidate.getId().equals(baseTicket.getId())) {
                continue;
            }

            if (candidate.getSubject() != null &&
                baseTicket.getSubject() != null &&
                candidate.getSubject().equalsIgnoreCase(baseTicket.getSubject())) {

                DuplicateLog log = new DuplicateLog();
                log.setTicketId(baseTicket.getId());
                log.setDuplicateTicketId(candidate.getId());
                log.setMessage("Duplicate ticket detected");

                duplicateLogRepository.save(log);
            }
        }
    }

    @Override
    public List<DuplicateLog> getLogsForTicket(Long ticketId) {
        return duplicateLogRepository.findByTicketId(ticketId);
    }

    @Override
    public DuplicateLog getLog(Long logId) {
        return duplicateLogRepository.findById(logId)
                .orElseThrow(() -> new RuntimeException("Log not found"));
    }
}
