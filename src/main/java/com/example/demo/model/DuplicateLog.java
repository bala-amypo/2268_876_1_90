package com.example.demo.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity @Table(name="duplicate_log")
public class DuplicateLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private Long ticketId;
    private String duplicateHash;
    private LocalDateTime detectedAt;
    /* getters / setters omitted for brevity */
}