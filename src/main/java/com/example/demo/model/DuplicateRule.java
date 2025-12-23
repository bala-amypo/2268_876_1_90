package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class DuplicateRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleName;
    private String matchType;
    private double threshold;

    public DuplicateRule() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRuleName() { return ruleName; }
    public void setRuleName(String ruleName) { this.ruleName = ruleName; }

    public String getMatchType() { return matchType; }
    public void setMatchType(String matchType) { this.matchType = matchType; }

    public double getThreshold() { return threshold; }
    public void setThreshold(double threshold) {
        if (threshold < 0 || threshold > 1)
            throw new IllegalArgumentException("threshold");
        this.threshold = threshold;
    }
}
