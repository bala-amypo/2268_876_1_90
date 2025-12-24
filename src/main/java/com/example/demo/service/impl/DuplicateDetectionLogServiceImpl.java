package com.example.demo.service.impl;

import com.example.demo.model.DuplicateLog;
import com.example.demo.repository.DuplicateLogRepository;
import com.example.demo.service.DuplicateDetectionService;
import org.springframework.stereotype.Service;

@Service
public class DuplicateDetectionServiceImpl implements DuplicateDetectionService {

    private final DuplicateLogRepository duplicateLogRepository;

    public DuplicateDetectionServiceImpl(DuplicateLogRepository duplicateLogRepository) {
        this.duplicateLogRepository = duplicateLogRepository;
    }

    @Override
    public DuplicateLog getLog(Long id) {
        return duplicateLogRepository.findById(id).orElse(null);
    }
}
