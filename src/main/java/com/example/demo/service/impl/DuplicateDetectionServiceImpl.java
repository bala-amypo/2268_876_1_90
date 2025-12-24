package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.DuplicateDetectionService;
import com.example.demo.util.TextSimilarityUtil;

import java.util.*;

public class DuplicateDetectionServiceImpl implements DuplicateDetectionService {
    private final TicketRepository ticketRepository;
    private final DuplicateRuleRepository ruleRepository;
    private final DuplicateDetectionLogRepository logRepository;

    public DuplicateDetectionServiceImpl(TicketRepository ticketRepository, DuplicateRuleRepository ruleRepository, DuplicateDetectionLogRepository logRepository) {
        this.ticketRepository = ticketRepository;
        this.ruleRepository = ruleRepository;
        this.logRepository = logRepository;
    }

    @Override
    public List<DuplicateDetectionLog> getLogsForTicket(Long ticketId) {
        return logRepository.findByTicket_Id(ticketId);
    }

    @Override
    public List<DuplicateDetectionLog> detectDuplicates(Long ticketId) {
        Ticket base = ticketRepository.findById(ticketId).orElse(null);
        if (base == null) return List.of();
        List<DuplicateRule> rules = ruleRepository.findAll();
        if (rules == null || rules.isEmpty()) return List.of();
        List<Ticket> open = ticketRepository.findByStatus("OPEN");
        List<DuplicateDetectionLog> logs = new ArrayList<>();
        for (Ticket other : open) {
            if (other == null) continue;
            if (other.getId() != null && base.getId() != null && Objects.equals(other.getId(), base.getId())) continue;
            for (DuplicateRule r : rules) {
                double score = 0.0;
                String type = r.getMatchType();
                if ("EXACT_MATCH".equalsIgnoreCase(type)) {
                    String s1 = base.getSubject() == null ? "" : base.getSubject();
                    String s2 = other.getSubject() == null ? "" : other.getSubject();
                    if (!s1.isBlank() && s1.equalsIgnoreCase(s2)) score = 1.0;
                } else if ("KEYWORD".equalsIgnoreCase(type)) {
                    String a = ((base.getSubject()==null?"":base.getSubject())+" "+(base.getDescription()==null?"":base.getDescription())).trim();
                    String b = ((other.getSubject()==null?"":other.getSubject())+" "+(other.getDescription()==null?"":other.getDescription())).trim();
                    score = TextSimilarityUtil.similarity(a,b);
                } else if ("SIMILARITY".equalsIgnoreCase(type)) {
                    String a = base.getDescription()==null?"":base.getDescription();
                    String b = other.getDescription()==null?"":other.getDescription();
                    score = TextSimilarityUtil.similarity(a,b);
                }
                if (score >= r.getThreshold()) {
                    DuplicateDetectionLog log = new DuplicateDetectionLog(base, other, score);
                    try { log = logRepository.save(log); } catch (Exception ignored) {}
                    logs.add(log);
                }
            }
        }
        return logs;
    }
}
