package com.ian.davidson.port.scanner.model.queue;

import com.ian.davidson.port.scanner.exception.BadRequestException;
import com.ian.davidson.port.scanner.model.entity.Address;
import com.ian.davidson.port.scanner.model.entity.Port;
import com.ian.davidson.port.scanner.model.entity.Tenant;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Builder;


@Builder
public record ScanItinerary(Long tenantId, Long sessionId, Set<String> addresses, Set<Integer> ports) {
    public static ScanItinerary newScanComposition(final Tenant tenant, final Long sessionId) {
        if (tenant.getAddresses().isEmpty()) {
            throw new BadRequestException("No addresses associated with tenant, can't execute scan");
        } else if (tenant.getPorts().isEmpty()) {
            throw new BadRequestException("No ports associated with tenant, can't execute scan");
        }

        return new ScanItinerary(
                tenant.getId(),
                sessionId,
                tenant.getAddresses().stream().map(Address::getAddress).collect(Collectors.toSet()),
                tenant.getPorts().stream().map(Port::getPort).collect(Collectors.toSet()));
    }
}
