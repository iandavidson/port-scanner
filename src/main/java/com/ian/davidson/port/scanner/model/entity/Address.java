package com.ian.davidson.port.scanner.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Address {
    private final int port;
    private final int timeout;
    private final boolean exposed;
    private final ConnectionStatus status;
}
