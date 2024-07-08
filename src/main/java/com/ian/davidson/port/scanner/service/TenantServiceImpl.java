package com.ian.davidson.port.scanner.service;

import com.ian.davidson.port.scanner.model.entity.Tenant;
import com.ian.davidson.port.scanner.repository.TenantRepository;
import org.springframework.stereotype.Service;

@Service
public class TenantServiceImpl implements TenantService {

    private final TenantRepository tenantRepository;

    public TenantServiceImpl(final TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Override
    public Tenant createTenant(Tenant tenant) {
        return null;
    }

    @Override
    public Tenant getTenant(Long id) {
        return null;
    }

    @Override
    public void deleteTenant(Long id) {

    }
}
