package com.example.demo.repository;

import com.example.demo.repository.TicketCategoryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository

public interface TicketCategoryRepository extends JpaRepository<TicketCategoryRepository, Long> {

    boolean existsByCategoryName(String categoryName);
}
