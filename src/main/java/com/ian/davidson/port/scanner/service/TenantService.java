package com.ian.davidson.port.scanner.service;


import com.ian.davidson.port.scanner.model.entity.Address;
import com.ian.davidson.port.scanner.model.entity.Port;
import com.ian.davidson.port.scanner.model.entity.Tenant;
import com.ian.davidson.port.scanner.model.response.TenantSurfaceResponse;
import java.util.Set;

public interface TenantService {
    Tenant createTenant(Tenant tenant);

    Tenant getTenant(Long id);

    void deleteTenant(Long id);

    TenantSurfaceResponse addSurface(Set<Port> ports, Set<Address> addresses, Long tenantId);
}
