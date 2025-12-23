package com.example.demo.service;

import com.example.demo.model.DuplicateLog;
import java.util.List;

public interface DuplicateDetectionService {

    void detectDuplicates(Long ticketId);

    List<DuplicateLog> getLogsForTicket(Long ticketId);

    DuplicateLog getLog(Long logId);
}
