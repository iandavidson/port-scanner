package com.ian.davidson.port.scanner.model.response;

import java.util.Set;
import lombok.Builder;

@Builder
public record TenantResponse (
        Long id,
        String name,
        Set<String> addresses,
        Set<Integer> ports) implements Comparable<TenantResponse> {
    @Override
    public int compareTo(TenantResponse o) {
        return (int) Math.signum(id - o.id);
    }
}
