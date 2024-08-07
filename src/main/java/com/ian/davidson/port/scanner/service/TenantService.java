package com.ian.davidson.port.scanner.service;


import com.ian.davidson.port.scanner.model.entity.Address;
import com.ian.davidson.port.scanner.model.entity.Port;
import com.ian.davidson.port.scanner.model.entity.Tenant;
import java.util.List;
import java.util.Set;

public interface TenantService {
    Tenant createTenant(Tenant tenant);

    Tenant getTenant(Long id);
    List<Tenant> getAllTenants();
    void deleteTenant(Long id);

    Tenant addSurface(Set<Port> ports, Set<Address> addresses, Long tenantId);
    Tenant updateSurface(Set<Port> ports, Set<Address> addresses, Long tenantId);
    void deleteSurface(Long tenantId);
}
