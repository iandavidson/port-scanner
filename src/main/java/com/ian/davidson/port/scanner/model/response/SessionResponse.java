package com.ian.davidson.port.scanner.model.response;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.Builder;

@Builder
public record SessionResponse(
        Long sessionId,
        Long tenantId,
        Set<ScanResultResponse> scanResults,
        LocalDateTime startedAt) {
}
