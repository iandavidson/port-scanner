package com.ian.davidson.port.scanner.model.response;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record ScanOverview(Long sessionId, LocalDateTime started) implements Comparable<ScanOverview> {
    @Override
    public int compareTo(ScanOverview o) {
        return (int) Math.signum(sessionId - o.sessionId);
    }
}
