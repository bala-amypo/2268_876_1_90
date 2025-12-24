package com.example.demo.repository;

import com.example.demo.model.DuplicateRule;
import java.util.*;

public interface DuplicateRuleRepository {
    Optional<DuplicateRule> findByRuleName(String name);
    DuplicateRule save(DuplicateRule r);
    Optional<DuplicateRule> findById(Long id);
    List<DuplicateRule> findAll();
}
