package com.ian.davidson.port.scanner.model.scan;

import lombok.Builder;

@Builder
public record ScanOperation(Long sessionId, String address, Integer port) {
}
