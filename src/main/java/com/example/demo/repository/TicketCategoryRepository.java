package com.example.demo.repository;

import com.example.demo.model.TicketCategory;
import java.util.*;

public interface TicketCategoryRepository {
    Optional<TicketCategory> findById(Long id);
    boolean existsByCategoryName(String name);
    TicketCategory save(TicketCategory category);
}
