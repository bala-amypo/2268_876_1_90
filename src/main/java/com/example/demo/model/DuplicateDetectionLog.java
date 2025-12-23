package com.example.demo.model;

import java.time.LocalDateTime;

public class DuplicateDetectionLog {
    private Ticket ticket;
    private Ticket duplicateTicket;
    private double matchScore;
    private LocalDateTime detectedAt = LocalDateTime.now();

    public DuplicateDetectionLog() {}

    public DuplicateDetectionLog(Ticket t1, Ticket t2, double score) {
        this.ticket = t1;
        this.duplicateTicket = t2;
        this.matchScore = score;
    }

    public double getMatchScore() { return matchScore; }
    public void setMatchScore(double matchScore) { this.matchScore = matchScore; }

    public LocalDateTime getDetectedAt() { return detectedAt; }
}
