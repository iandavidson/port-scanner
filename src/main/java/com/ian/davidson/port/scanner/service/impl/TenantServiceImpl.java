package com.ian.davidson.port.scanner.service.impl;

import com.ian.davidson.port.scanner.exception.ResourceConflictException;
import com.ian.davidson.port.scanner.model.entity.Tenant;
import com.ian.davidson.port.scanner.repository.TenantRepository;
import com.ian.davidson.port.scanner.service.TenantService;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

@Service
public class TenantServiceImpl implements TenantService {

    private final TenantRepository tenantRepository;

    public TenantServiceImpl(final TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Override
    public Tenant createTenant(Tenant tenant) {
        Optional<Tenant> optional = tenantRepository.findByName(tenant.getName());
        if(optional.isPresent()){
            throw new ResourceConflictException("Found tenant with same name provided >"+ tenant.getName() + "<" );
        }
    }

    @Override
    public Tenant getTenant(Long id) {
        return null;
    }

    @Override
    public void deleteTenant(Long id) {

    }
}
