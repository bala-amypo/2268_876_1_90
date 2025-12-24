package com.example.demo.service.impl;

import com.example.demo.model.DuplicateRule;
import com.example.demo.repository.DuplicateRuleRepository;
import com.example.demo.service.DuplicateRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DuplicateRuleServiceImpl implements DuplicateRuleService {

    private final DuplicateRuleRepository duplicateRuleRepository;

    public DuplicateRuleServiceImpl(DuplicateRuleRepository duplicateRuleRepository) {
        this.duplicateRuleRepository = duplicateRuleRepository;
    }

    @Override
    public List<DuplicateRule> getAllRules() {
        return duplicateRuleRepository.findAll();
    }
}
