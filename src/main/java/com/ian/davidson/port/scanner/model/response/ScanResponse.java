package com.ian.davidson.port.scanner.model.response;

import java.time.Instant;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ScanResponse {
    private long id;
    private Instant createdAt;
    private Set<String> addresses;

    private Set<Long> Ports;
}
