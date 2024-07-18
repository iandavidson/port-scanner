package com.ian.davidson.port.scanner.service;

import com.ian.davidson.port.scanner.model.entity.Port;
import com.ian.davidson.port.scanner.model.entity.Tenant;
import java.util.Set;

public interface PortService {
    void addPorts(Set<Port> ports);
    void deletePortsByTenantId(Long tenantId);
    void deleteByTenant(Tenant tenant);
}
