package com.ian.davidson.port.scanner.transformer;

import com.ian.davidson.port.scanner.model.entity.Address;
import com.ian.davidson.port.scanner.model.entity.Port;
import com.ian.davidson.port.scanner.model.entity.Tenant;
import com.ian.davidson.port.scanner.model.request.TenantRequest;
import com.ian.davidson.port.scanner.model.response.TenantResponse;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class TenantTransformer {

    public Tenant toTenant(final TenantRequest tenantRequest) {
        //Used in context
        return Tenant.builder()
                .name(tenantRequest.name())
                .build();
    }

    public TenantResponse toTenantResponse(final Tenant tenant) {

        Set<Integer> ports = tenant.getPorts() == null
                ? new HashSet<>()
                : tenant.getPorts().stream().map(Port::getPort).collect(Collectors.toSet());

        Set<String> addresses = tenant.getAddresses() == null
                ? new HashSet<>()
                : tenant.getAddresses().stream().map(Address::getAddress).collect(Collectors.toSet());

        return TenantResponse.builder()
                .id(tenant.getId())
                .name(tenant.getName())
                .ports(ports)
                .addresses(addresses)
                .build();
    }
}
