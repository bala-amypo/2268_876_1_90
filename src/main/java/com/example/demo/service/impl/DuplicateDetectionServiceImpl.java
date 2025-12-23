package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.DuplicateDetectionService;
import com.example.demo.util.TextSimilarityUtil;

import java.util.*;

public class DuplicateDetectionServiceImpl implements DuplicateDetectionService {

    private final TicketRepository ticketRepo;
    private final DuplicateRuleRepository ruleRepo;
    private final DuplicateDetectionLogRepository logRepo;

    public DuplicateDetectionServiceImpl(TicketRepository t, DuplicateRuleRepository r, DuplicateDetectionLogRepository l) {
        this.ticketRepo = t;
        this.ruleRepo = r;
        this.logRepo = l;
    }

    public List<DuplicateDetectionLog> detectDuplicates(Long ticketId) {
        Optional<Ticket> baseOpt = ticketRepo.findById(ticketId);
        if (baseOpt.isEmpty()) return List.of();

        Ticket base = baseOpt.get();
        List<DuplicateRule> rules = ruleRepo.findAll();
        List<Ticket> open = ticketRepo.findByStatus("OPEN");

        List<DuplicateDetectionLog> result = new ArrayList<>();

        for (Ticket t : open) {
            if (t.getId() != null && t.getId().equals(base.getId())) continue;

            for (DuplicateRule r : rules) {
                double score = 0;

                switch (r.getMatchType()) {
                    case "EXACT_MATCH" ->
                            score = base.getSubject() != null && t.getSubject() != null &&
                                    base.getSubject().equalsIgnoreCase(t.getSubject()) ? 1.0 : 0.0;
                    case "KEYWORD" ->
                            score = TextSimilarityUtil.similarity(
                                    base.getSubject() + " " + base.getDescription(),
                                    t.getSubject() + " " + t.getDescription());
                    case "SIMILARITY" ->
                            score = TextSimilarityUtil.similarity(base.getDescription(), t.getDescription());
                }

                if (score >= r.getThreshold()) {
                    DuplicateDetectionLog log = new DuplicateDetectionLog(base, t, score);
                    logRepo.save(log);
                    result.add(log);
                }
            }
        }
        return result;
    }

    public List<DuplicateDetectionLog> getLogsForTicket(Long id) {
        return logRepo.findByTicket_Id(id);
    }
}
