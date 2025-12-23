package com.example.demo.repository;

import com.example.demo.model.Ticket;
import java.util.*;

public interface TicketRepository {
    Optional<Ticket> findById(Long id);
    List<Ticket> findAll();
    List<Ticket> findByStatus(String status);
    List<Ticket> findByCategory_Id(Long id);
    List<Ticket> findByUser_Id(Long id);
    List<Ticket> findBySubjectContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String s1, String s2);
    Ticket save(Ticket ticket);
}
