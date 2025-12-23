package com.example.demo.repository;

import com.example.demo.model.DuplicateRule;
import java.util.*;

public interface DuplicateRuleRepository {
    Optional<DuplicateRule> findById(Long id);
    Optional<DuplicateRule> findByRuleName(String name);
    List<DuplicateRule> findAll();
    DuplicateRule save(DuplicateRule rule);
}
