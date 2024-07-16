package com.ian.davidson.port.scanner.service.impl;

import com.ian.davidson.port.scanner.exception.ResourceConflictException;
import com.ian.davidson.port.scanner.exception.ResourceNotFoundException;
import com.ian.davidson.port.scanner.model.entity.Address;
import com.ian.davidson.port.scanner.model.entity.Port;
import com.ian.davidson.port.scanner.model.entity.Tenant;
import com.ian.davidson.port.scanner.model.response.TenantSurfaceResponse;
import com.ian.davidson.port.scanner.repository.AddressRepository;
import com.ian.davidson.port.scanner.repository.PortRepository;
import com.ian.davidson.port.scanner.repository.TenantRepository;
import com.ian.davidson.port.scanner.service.AddressService;
import com.ian.davidson.port.scanner.service.PortService;
import com.ian.davidson.port.scanner.service.SessionService;
import com.ian.davidson.port.scanner.service.TenantService;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class TenantServiceImpl implements TenantService {

    private final TenantRepository tenantRepository;
    private final AddressService addressService;
    private final PortService portService;
    private final SessionService sessionService;

    public TenantServiceImpl(final TenantRepository tenantRepository,
                             final AddressService addressService,
                             final PortService portService,
                             final SessionService sessionService) {
        this.tenantRepository = tenantRepository;
        this.addressService = addressService;
        this.portService = portService;
        this.sessionService = sessionService;
    }

    @Override
    public Tenant createTenant(final Tenant tenant) {
        Optional<Tenant> optional = tenantRepository.findByName(tenant.getName());

        if (optional.isPresent()) {
            throw new ResourceConflictException("Found tenant with same name provided >" + tenant.getName() + "<");
        }

        tenantRepository.save(tenant);

        return tenant;
    }

    @Override
    public Tenant getTenant(final Long tenantId) {
        return tenantRepository.findById(tenantId)
                .orElseThrow(() -> new ResourceNotFoundException("No tenant found at id >" + tenantId + "<"));
    }

    @Override
    public void deleteTenant(final Long tenantId) {
        sessionService.deleteSessionsByTenantId(tenantId);
        portService.deletePortsByTenantId(tenantId);
        addressService.deleteAddressesByTenantId(tenantId);
        tenantRepository.deleteById(tenantId);
    }

    @Override
    public TenantSurfaceResponse addSurface(Set<Port> ports, Set<Address> addresses, Tenant tenant) {
        throw new IllegalStateException("not implemented");
    }

}
