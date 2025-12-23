package com.example.demo.service.impl;

import com.example.demo.model.DuplicateRule;
import com.example.demo.repository.DuplicateRuleRepository;
import com.example.demo.service.DuplicateRuleService;

public class DuplicateRuleServiceImpl implements DuplicateRuleService {

    private final DuplicateRuleRepository repo;

    public DuplicateRuleServiceImpl(DuplicateRuleRepository repo) {
        this.repo = repo;
    }

    public DuplicateRule createRule(DuplicateRule r) {
        if (repo.findByRuleName(r.getRuleName()).isPresent())
            throw new RuntimeException("Rule exists");
        return repo.save(r);
    }

    public DuplicateRule getRule(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found"));
    }
}
