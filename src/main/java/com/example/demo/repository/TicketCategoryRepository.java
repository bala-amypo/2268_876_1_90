package com.example.demo.repository;

import com.example.demo.repository.TicketCategoryRepository;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TicketCategoryRepository extends JpaRepository<TicketCategoryRepository, Long> {
   @Repository
   
    boolean existsByCategoryName(String categoryName);
}
