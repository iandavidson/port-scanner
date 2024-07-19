package com.ian.davidson.port.scanner.service.impl;

import com.ian.davidson.port.scanner.model.entity.Session;
import com.ian.davidson.port.scanner.model.entity.Tenant;
import com.ian.davidson.port.scanner.repository.SessionRepository;
import com.ian.davidson.port.scanner.service.SessionService;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;

    public SessionServiceImpl(final SessionRepository sessionRepository){
        this.sessionRepository = sessionRepository;
    }

    @Override
    public void addSession(Session session) {
        sessionRepository.save(session);
    }

    @Override
    public void deleteSessionsByTenantId(Long tenantId) {
        sessionRepository.deleteAllByTenantId(tenantId);
    }

    @Override
    public void deleteByTenant(Tenant tenant) {
        sessionRepository.deleteByTenant(tenant);
    }
}
