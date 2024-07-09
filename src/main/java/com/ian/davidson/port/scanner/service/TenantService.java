package com.ian.davidson.port.scanner.service;


import com.ian.davidson.port.scanner.model.entity.Tenant;

public interface TenantService {
    Tenant createTenant(Tenant tenant);

    Tenant getTenant(Long id);

    void deleteTenant(Long id);
}
