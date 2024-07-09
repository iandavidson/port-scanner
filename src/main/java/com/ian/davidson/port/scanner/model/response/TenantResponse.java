package com.ian.davidson.port.scanner.model.response;

import lombok.Builder;

import java.util.Set;

@Builder
public record TenantResponse(
        Long id,
        String name,
        Set<String> addresses,
        Set<Integer> ports) {}
