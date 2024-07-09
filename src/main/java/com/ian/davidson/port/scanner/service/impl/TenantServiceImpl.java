package com.ian.davidson.port.scanner.service.impl;

import com.ian.davidson.port.scanner.exception.ResourceConflictException;
import com.ian.davidson.port.scanner.exception.ResourceNotFoundException;
import com.ian.davidson.port.scanner.model.entity.Address;
import com.ian.davidson.port.scanner.model.entity.Port;
import com.ian.davidson.port.scanner.model.entity.Tenant;
import com.ian.davidson.port.scanner.model.response.TenantSurfaceResponse;
import com.ian.davidson.port.scanner.repository.TenantRepository;
import com.ian.davidson.port.scanner.service.TenantService;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class TenantServiceImpl implements TenantService {

    private final TenantRepository tenantRepository;

    public TenantServiceImpl(final TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Override
    public Tenant createTenant(Tenant tenant) {
        Optional<Tenant> optional = tenantRepository.findByName(tenant.getName());

        if (optional.isPresent()) {
            throw new ResourceConflictException("Found tenant with same name provided >" + tenant.getName() + "<");
        }

        tenantRepository.save(tenant);

        return tenant;
    }

    @Override
    public Tenant getTenant(Long id) {
        return tenantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No tenant found at id >" + id + "<"));
    }

    @Override
    public void deleteTenant(Long id) {
        tenantRepository.deleteById(id);
    }

    @Override
    public TenantSurfaceResponse addSurface(Set<Port> ports, Set<Address> addresses, Long tenantId) {
        //
    }
}
