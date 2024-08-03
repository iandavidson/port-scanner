package com.ian.davidson.port.scanner.service;

import com.ian.davidson.port.scanner.model.entity.Port;
import java.util.Set;

public interface PortService {
    void deletePortsByTenantId(Long tenantId);
    void updatePortsByTenantId(Set<Port> newPorts, Long tenantId);
}
