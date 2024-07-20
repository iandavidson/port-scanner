package com.ian.davidson.port.scanner.service;

import com.ian.davidson.port.scanner.model.entity.Tenant;
import com.ian.davidson.port.scanner.model.request.ScanRequest;
import jakarta.validation.Valid;

public interface ScanService {
    void initializeScan(Tenant tenant, Long sessionId);
//
//    ScanResponse getScan(final Long id);
}
