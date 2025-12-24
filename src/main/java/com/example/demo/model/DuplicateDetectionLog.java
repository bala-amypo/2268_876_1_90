package com.example.demo.model;

import java.time.Instant;

public class DuplicateDetectionLog {
    private Long id;
    private Ticket ticket;
    private Ticket duplicateOf;
    private double matchScore;
    private Instant detectedAt = Instant.now();

    public DuplicateDetectionLog() {}

    public DuplicateDetectionLog(Ticket ticket, Ticket duplicateOf, double matchScore) {
        this.ticket = ticket;
        this.duplicateOf = duplicateOf;
        this.matchScore = matchScore;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Ticket getTicket() { return ticket; }
    public void setTicket(Ticket ticket) { this.ticket = ticket; }
    public Ticket getDuplicateOf() { return duplicateOf; }
    public void setDuplicateOf(Ticket duplicateOf) { this.duplicateOf = duplicateOf; }
    public double getMatchScore() { return matchScore; }
    public void setMatchScore(double matchScore) { this.matchScore = matchScore; }
    public Instant getDetectedAt() { return detectedAt; }
    public void setDetectedAt(Instant detectedAt) { this.detectedAt = detectedAt; }
}
