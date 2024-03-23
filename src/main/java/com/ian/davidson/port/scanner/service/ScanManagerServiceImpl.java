package com.ian.davidson.port.scanner.service;

import com.ian.davidson.port.scanner.model.ScanRequest;
import com.ian.davidson.port.scanner.model.ScanResponse;
import org.springframework.stereotype.Service;

@Service
public class ScanManagerServiceImpl implements ScanManagerService {
    @Override
    public ScanResponse initializeScan(ScanRequest scanRequest) {
        return null;
    }
}
