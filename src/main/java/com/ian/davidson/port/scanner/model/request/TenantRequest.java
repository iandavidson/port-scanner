package com.ian.davidson.port.scanner.model.request;

import java.util.Set;

public record TenantRequest(
        String name,
        Set<Integer> ports,
        Set<String> addresses
) {

}
