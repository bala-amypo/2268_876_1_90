package com.example.demo.repository;

import com.example.demo.model.DuplicateDetectionLog;
import java.util.*;

public interface DuplicateDetectionLogRepository {
    List<DuplicateDetectionLog> findByTicket_Id(Long ticketId);
    DuplicateDetectionLog save(DuplicateDetectionLog log);
}
