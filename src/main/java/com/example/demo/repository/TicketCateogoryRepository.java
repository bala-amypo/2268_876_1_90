package com.example.demo.repository;

import com.example.demo.model.TicketCategoryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketCategoryRepository extends JpaRepository<TicketCategoryRepository, Long> {

    boolean existsByCategoryName(String categoryName);
}
