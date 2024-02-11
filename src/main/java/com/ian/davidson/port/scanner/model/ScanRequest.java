package com.ian.davidson.port.scanner.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ScanRequest {
    @JsonProperty("IPs")
    private List<Long> Ips;

    private List<Long> Ports;
}
