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

//    private final AddressTransformer addressTransformer;
//    private final PortTransformer portTransformer;
//
//    public TenantTransformer(final AddressTransformer addressTransformer,
//                             final PortTransformer portTransformer) {
//        this.addressTransformer = addressTransformer;
//        this.portTransformer = portTransformer;
//    }


    public Tenant toTenant(final TenantRequest tenantRequest) {
        //Used in context
        return Tenant.builder()
                .name(tenantRequest.name())
                .build();
    }

//    public Tenant toTenant(final TenantRequest tenantRequest){
//        Set<Address> addresses = addressTransformer.toAddresses(tenantRequest.addresses());
//        Set<Port> ports = portTransformer.toPorts(tenantRequest.ports());
//
//        return Tenant.builder()
//                .name(tenantRequest.name())
//                .addresses(addresses)
//                .ports(ports)
//                .build();
//    }

    public TenantResponse toTenantResponse(final Tenant tenant) {
        Set<Integer> ports = tenant.getPorts() != null ? tenant.getPorts().stream().map(Port::getPort).collect(Collectors.toSet()) : new HashSet<>();
        Set<String> addresses = tenant.getAddresses() != null ? tenant.getAddresses().stream().map(Address::getAddress).collect(Collectors.toSet()) : new HashSet<>();

        return TenantResponse.builder()
                .id(tenant.getId())
                .name(tenant.getName())
                .ports(ports)
                .addresses(addresses)
                .build();
    }
}
