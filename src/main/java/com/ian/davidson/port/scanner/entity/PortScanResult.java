package com.ian.davidson.port.scanner.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PortScanResult {
    private final int port;
    private final int timeout;
    private final boolean exposed;
}
