package com.ian.davidson.port.scanner.service.impl;

import com.ian.davidson.port.scanner.exception.ResourceNotFoundException;
import com.ian.davidson.port.scanner.model.entity.Session;
import com.ian.davidson.port.scanner.repository.SessionRepository;
import com.ian.davidson.port.scanner.service.SessionService;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;

    public SessionServiceImpl(final SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Session addSession(final Session session) {
        return sessionRepository.save(session);
    }

    @Override
    public Session getSession(final Long sessionId) {
        return sessionRepository.findById(sessionId).orElseThrow(() -> new ResourceNotFoundException(
                "Could not find session at id: " + sessionId));
    }

    @Override
    public void deleteSessionsByTenantId(final Long tenantId) {
        sessionRepository.deleteAllByTenantId(tenantId);
    }

    @Override
    public void deleteSession(Long sessionId) {
        sessionRepository.deleteById(sessionId);
    }
}
