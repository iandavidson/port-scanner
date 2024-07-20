package com.ian.davidson.port.scanner.model;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record ScanOverview(Long sessionId, LocalDateTime started) {}
