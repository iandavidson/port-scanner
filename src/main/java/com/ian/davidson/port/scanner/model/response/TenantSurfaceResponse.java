package com.ian.davidson.port.scanner.model.response;

import java.util.Set;

@Deprecated
public record TenantSurfaceResponse(Set<Integer> ports, Set<String> addresses, Long tenantId) {
}
