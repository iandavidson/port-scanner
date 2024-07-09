package com.ian.davidson.port.scanner.transformer;

import com.ian.davidson.port.scanner.model.entity.Address;
import com.ian.davidson.port.scanner.model.entity.Port;
import com.ian.davidson.port.scanner.model.entity.Tenant;
import com.ian.davidson.port.scanner.model.request.TenantRequest;
import com.ian.davidson.port.scanner.model.response.TenantResponse;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TenantTransformer {

    public Tenant toTenant(final TenantRequest tenantRequest){
        Set<Address> addresses = tenantRequest.addresses().stream().map(raw -> {

        }).collect(Collectors.toSet());

        return Tenant.builder().name(tenantRequest.name()).build();
    }

    public TenantResponse toTenantResponse(final Tenant tenant){
        Set<Integer> ports = tenant.getPorts().stream().map(Port::getPort).collect(Collectors.toSet());
        Set<String> addresses = tenant.getAddresses().stream().map(Address::getAddress).collect(Collectors.toSet());

        return TenantResponse.builder()
                .id(tenant.getId())
                .name(tenant.getName())
                .ports(ports)
                .addresses(addresses)
                .build();
    }
}
