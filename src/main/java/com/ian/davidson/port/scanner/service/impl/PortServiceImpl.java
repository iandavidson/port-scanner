package com.ian.davidson.port.scanner.service.impl;

import com.ian.davidson.port.scanner.model.entity.Port;
import com.ian.davidson.port.scanner.repository.PortRepository;
import com.ian.davidson.port.scanner.service.PortService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class PortServiceImpl implements PortService {

    private final PortRepository portRepository;

    public PortServiceImpl(final PortRepository portRepository) {
        this.portRepository = portRepository;
    }

    private void addPorts(final Set<Port> ports) {
        portRepository.saveAll(ports);
    }

    @Override
    public void deletePortsByTenantId(final Long tenantId) {
        portRepository.deleteAllByTenantId(tenantId);
    }

    @Override
    public void updatePortsByTenantId(final Set<Port> newPorts, final Long tenantId) {
        Set<Port> existingPorts = getPortsByTenantId(tenantId);
        Set<Port> toBeRemoved = new HashSet<>();
        Set<Port> toBeAdded = new HashSet<>();

        for (Port newPort : newPorts) {
            if (!existingPorts.contains(newPort)) {
                toBeAdded.add(newPort);
            }
        }

        for (Port existingPort : existingPorts) {
            if (!newPorts.contains(existingPort)) {
                toBeRemoved.add(existingPort);
            }
        }

        addPorts(toBeAdded);
        deletePorts(toBeRemoved);
    }

    private Set<Port> getPortsByTenantId(final Long tenantId) {
        return portRepository.findAllByTenantId(tenantId);
    }

    private void deletePorts(final Set<Port> ports){
        portRepository.deleteAll(ports);
    }
}
