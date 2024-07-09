package com.ian.davidson.port.scanner.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@Builder
public class ScanResponse {
    private long id;
    private Instant createdAt;
    private Set<String> addresses;

    private Set<Long> Ports;
}
