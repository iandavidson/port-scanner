package com.ian.davidson.port.scanner.service;

import com.ian.davidson.port.scanner.model.ScanRequest;
import com.ian.davidson.port.scanner.model.ScanResponse;

public interface ScanManagerService {
    ScanResponse initializeScan(final ScanRequest scanRequest);
}
