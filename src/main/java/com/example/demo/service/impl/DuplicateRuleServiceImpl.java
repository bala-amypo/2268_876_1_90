package com.example.demo.service.impl;

import com.example.demo.model.DuplicateRule;
import com.example.demo.repository.DuplicateRuleRepository;
import com.example.demo.service.DuplicateRuleService;

public class DuplicateRuleServiceImpl implements DuplicateRuleService {
    private final DuplicateRuleRepository repository;

    public DuplicateRuleServiceImpl(DuplicateRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public DuplicateRule createRule(DuplicateRule rule) {
        if (repository.findByRuleName(rule.getRuleName()).isPresent()) {
            throw new IllegalArgumentException("rule exists");
        }
        if (rule.getThreshold() < 0.0 || rule.getThreshold() > 1.0) {
            throw new IllegalArgumentException("threshold out of range");
        }
        return repository.save(rule);
    }

    @Override
    public DuplicateRule getRule(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("rule not found"));
    }
}
