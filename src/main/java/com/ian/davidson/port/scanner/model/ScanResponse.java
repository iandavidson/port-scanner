package com.ian.davidson.port.scanner.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ScanResponse {
    private long Id;
    private Instant createdAt;

    @JsonProperty("IPs")
    private List<Long> Ips;

    private List<Long> Ports;
}
