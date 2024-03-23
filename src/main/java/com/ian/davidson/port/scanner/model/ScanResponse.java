package com.ian.davidson.port.scanner.model;

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
    private long Id;
    private Instant createdAt;


    @JsonProperty("IPs")
    private Set<Long> Ips;

    private Set<Long> Ports;
}
