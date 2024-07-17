package com.ian.davidson.port.scanner.service;


import com.ian.davidson.port.scanner.model.entity.Session;

public interface SessionService {
    void addSession(Session session);
    void deleteSessionsByTenantId(Long tenantId);
}
