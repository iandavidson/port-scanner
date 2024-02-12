package com.ian.davidson.port.scanner.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ian.davidson.port.scanner.validation.constraint.ScanRequestValidatorConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@ScanRequestValidatorConstraint
@Data
public class ScanRequest {
    @JsonProperty("IPs")
    private Set<Long> Ips;

    private Set<Long> Ports;
}
