package com.example.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
// @Table(name = "duplicate_detection_log")
public class DuplicateDetectionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "matched_ticket_id", nullable = false)
    private Ticket matchedTicket;

    @Column(nullable = false)
    private Double matchScore;

    private LocalDateTime detectedAt;

    public DuplicateDetectionLog() {}

    public DuplicateDetectionLog(Long id, Ticket ticket, Ticket matchedTicket, Double matchScore) {
        this.id = id;
        this.ticket = ticket;
        this.matchedTicket = matchedTicket;
        this.matchScore = matchScore;
    }

    @PrePersist
    protected void onCreate() {
        this.detectedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Ticket getTicket() { return ticket; }
    public void setTicket(Ticket ticket) { this.ticket = ticket; }

    public Ticket getMatchedTicket() { return matchedTicket; }
    public void setMatchedTicket(Ticket matchedTicket) { this.matchedTicket = matchedTicket; }

    public Double getMatchScore() { return matchScore; }
    public void setMatchScore(Double matchScore) { this.matchScore = matchScore; }

    public LocalDateTime getDetectedAt() { return detectedAt; }
}
