package com.example.demo.repository;

import com.example.demo.model.Ticket;
import java.util.*;

public interface TicketRepository {
    Ticket save(Ticket t);
    Optional<Ticket> findById(Long id);
    List<Ticket> findAll();
    List<Ticket> findByCategory_Id(Long id);
    List<Ticket> findByUser_Id(Long id);
    List<Ticket> findByStatus(String status);
    List<Ticket> findBySubjectContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String s1, String s2);
}
