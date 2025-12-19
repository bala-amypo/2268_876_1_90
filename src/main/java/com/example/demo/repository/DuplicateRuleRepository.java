package com.example.demo.repository;

import com.example.demo.repository.DuplicateRuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface DuplicateRuleRepository extends JpaRepository<DuplicateRuleRepository, Long> {

    Optional<DuplicateRuleRepository> findByRuleName(String ruleName);
}
