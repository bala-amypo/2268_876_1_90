package com.example.demo.service.impl;

import com.example.demo.model.DuplicateRule;
import com.example.demo.repository.DuplicateRuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DuplicateRuleServiceImpl {

    private final DuplicateRuleRepository ruleRepository;

    public DuplicateRuleServiceImpl(DuplicateRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    public DuplicateRule createRule(DuplicateRule rule) {
        Optional<DuplicateRule> existing = ruleRepository.findByRuleName(rule.getRuleName());
        if (existing.isPresent()) {
            throw new IllegalArgumentException("Rule already exists");
        }

        if (rule.getThreshold() < 0.0 || rule.getThreshold() > 1.0) {
            throw new IllegalArgumentException("Threshold must be between 0.0 and 1.0");
        }

        return ruleRepository.save(rule);
    }

    public List<DuplicateRule> getAllRules() {
        return ruleRepository.findAll();
    }

    public DuplicateRule getRule(Long id) {
        return ruleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found"));
    }
}
