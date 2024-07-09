package com.ian.davidson.port.scanner.service;

import com.ian.davidson.port.scanner.model.request.ScanRequest;
import com.ian.davidson.port.scanner.model.response.ScanResponse;
import jakarta.validation.Valid;

public interface ScanService {
    ScanResponse initializeScan(final @Valid ScanRequest scanRequest);

    ScanResponse getScan(final Long id);
}
