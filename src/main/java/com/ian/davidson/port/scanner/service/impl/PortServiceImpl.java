package com.ian.davidson.port.scanner.service.impl;

import com.ian.davidson.port.scanner.model.entity.Port;
import com.ian.davidson.port.scanner.model.entity.Tenant;
import com.ian.davidson.port.scanner.repository.PortRepository;
import com.ian.davidson.port.scanner.service.PortService;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class PortServiceImpl implements PortService {

    private final PortRepository portRepository;

    public PortServiceImpl(final PortRepository portRepository) {
        this.portRepository = portRepository;
    }

    @Override
    public void addPorts(Set<Port> ports) {
        portRepository.saveAll(ports);
    }

    @Override
    public void deletePortsByTenantId(Long tenantId) {
        portRepository.deleteAllByTenantId(tenantId);
    }

    @Override
    public void deleteByTenant(Tenant tenant) {
        portRepository.deleteByTenant(tenant);
    }
}
