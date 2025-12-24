package com.example.demo.repository;

import com.example.demo.model.TicketCategory;
import java.util.*;

public interface TicketCategoryRepository {
    boolean existsByCategoryName(String name);
    TicketCategory save(TicketCategory c);
    Optional<TicketCategory> findById(Long id);
    List<TicketCategory> findAll();
}
