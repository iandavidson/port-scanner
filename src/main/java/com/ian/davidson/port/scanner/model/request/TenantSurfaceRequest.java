package com.ian.davidson.port.scanner.model.request;

import java.util.Set;

public record TenantSurfaceRequest(Set<Integer> ports, Set<String> addresses) {
}
