package com.ian.davidson.port.scanner.service;

import com.ian.davidson.port.scanner.model.entity.Tenant;

public interface ScanService {
    void initializeScan(Tenant tenant, Long sessionId);
}
