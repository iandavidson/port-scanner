package com.ian.davidson.port.scanner.service;


import com.ian.davidson.port.scanner.model.entity.Session;
import com.ian.davidson.port.scanner.model.entity.Tenant;

public interface SessionService {
    Session addSession(Session session);
    Session getSession(Long sessionId);
    void deleteSessionsByTenantId(Long tenantId);
    void deleteByTenant(Tenant tenant);

}
