package com.example.demo.model;

public class DuplicateRule {
    private Long id;
    private String ruleName;
    private String matchType;
    private double threshold;

    public DuplicateRule() {}

    public DuplicateRule(String name, String type, double threshold) {
        this.ruleName = name;
        this.matchType = type;
        this.threshold = threshold;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRuleName() { return ruleName; }
    public void setRuleName(String ruleName) { this.ruleName = ruleName; }

    public String getMatchType() { return matchType; }
    public void setMatchType(String matchType) { this.matchType = matchType; }

    public double getThreshold() { return threshold; }
    public void setThreshold(double threshold) { this.threshold = threshold; }
}
