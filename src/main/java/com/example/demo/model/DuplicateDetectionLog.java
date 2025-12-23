=package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class DuplicateDetectionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Ticket ticket;

    @ManyToOne
    private Ticket duplicateTicket;

    private double matchScore;
    private LocalDateTime detectedAt = LocalDateTime.now();

    public DuplicateDetectionLog() {}

    public Ticket getTicket() { return ticket; }
    public void setTicket(Ticket ticket) { this.ticket = ticket; }

    public Ticket getDuplicateTicket() { return duplicateTicket; }
    public void setDuplicateTicket(Ticket duplicateTicket) {
        this.duplicateTicket = duplicateTicket;
    }

    public double getMatchScore() { return matchScore; }
    public void setMatchScore(double matchScore) { this.matchScore = matchScore; }

    public LocalDateTime getDetectedAt() { return detectedAt; }
}
