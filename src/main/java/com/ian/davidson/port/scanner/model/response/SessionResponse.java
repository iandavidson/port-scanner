package com.ian.davidson.port.scanner.model.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

@Builder
public record SessionResponse(
        Long sessionId,
        Long tenantId,
        List<ScanResultResponse> scanResults,
        LocalDateTime startedAt) {
}
