package com.ian.davidson.port.scanner.model.business;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Port {
    private final Long value;
    private Long tenantId;
}
