package com.example.demo.service;

import com.example.demo.model.DuplicateDetectionLog;
import java.util.*;

public interface DuplicateDetectionService {
    java.util.List<DuplicateDetectionLog> getLogsForTicket(Long ticketId);
    java.util.List<DuplicateDetectionLog> detectDuplicates(Long ticketId);
}
