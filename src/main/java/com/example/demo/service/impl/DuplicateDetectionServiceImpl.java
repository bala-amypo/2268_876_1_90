package com.example.demo.service.impl;

import com.example.demo.model.DuplicateDetectionLog;
import com.example.demo.model.DuplicateRule;
import com.example.demo.model.Ticket;
import com.example.demo.repository.DuplicateDetectionLogRepository;
import com.example.demo.repository.DuplicateRuleRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.util.TextSimilarityUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DuplicateDetectionServiceImpl {

    private final TicketRepository ticketRepository;
    private final DuplicateRuleRepository ruleRepository;
    private final DuplicateDetectionLogRepository logRepository;

    public DuplicateDetectionServiceImpl(TicketRepository ticketRepository,
                                        DuplicateRuleRepository ruleRepository,
                                        DuplicateDetectionLogRepository logRepository) {
        this.ticketRepository = ticketRepository;
        this.ruleRepository = ruleRepository;
        this.logRepository = logRepository;
    }

    public List<DuplicateDetectionLog> detectDuplicates(Long ticketId) {
        Ticket baseTicket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("ticket not found"));

        List<DuplicateDetectionLog> logs = new ArrayList<>();
        List<DuplicateRule> rules = ruleRepository.findAll();
        List<Ticket> openTickets = ticketRepository.findByStatus("OPEN");

        for (DuplicateRule rule : rules) {
            for (Ticket candidate : openTickets) {
                if (candidate.getId().equals(baseTicket.getId())) continue;

                double score = 0.0;

                switch (rule.getMatchType()) {
                    case "EXACT_MATCH":
                        score = baseTicket.getSubject().equalsIgnoreCase(candidate.getSubject()) ? 1.0 : 0.0;
                        break;
                    case "KEYWORD":
                        String[] baseWords = baseTicket.getSubject().toLowerCase().split("\\s+");
                        String[] candidateWords = candidate.getSubject().toLowerCase().split("\\s+");
                        int matches = 0;
                        for (String w1 : baseWords) {
                            for (String w2 : candidateWords) {
                                if (w1.equals(w2)) matches++;
                            }
                        }
                        score = (double) matches / baseWords.length;
                        break;
                    case "SIMILARITY":
                        score = TextSimilarityUtil.similarity(baseTicket.getDescription(), candidate.getDescription());
                        break;
                }

                if (score >= rule.getThreshold()) {
                    DuplicateDetectionLog log = new DuplicateDetectionLog();
                    log.setTicket(baseTicket);
                    log.setMatchedTicket(candidate);
                    log.setMatchScore(score);
                    logs.add(logRepository.save(log));
                }
            }
        }

        return logs;
    }

    public List<DuplicateDetectionLog> getLogsForTicket(Long ticketId) {
        return logRepository.findByTicket_Id(ticketId);
    }

    public DuplicateDetectionLog getLog(Long id) {
        return logRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Log not found"));
    }
}
