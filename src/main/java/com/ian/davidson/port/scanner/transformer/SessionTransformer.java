package com.ian.davidson.port.scanner.transformer;

import com.ian.davidson.port.scanner.model.entity.Session;
import com.ian.davidson.port.scanner.model.response.SessionResponse;
import org.springframework.stereotype.Component;

@Component
public class SessionTransformer {

    private final ScanResultTransformer scanResultTransformer;

    public SessionTransformer(final ScanResultTransformer scanResultTransformer){
        this.scanResultTransformer = scanResultTransformer;
    }

    public Session toSession(final Long tenantId){
        return Session.builder().tenantId(tenantId).build();
    }

    public SessionResponse toSessionResponse(final Session session){
        return SessionResponse.builder()
                .sessionId(session.getId())
                .tenantId(session.getTenantId())
                .scanResults(scanResultTransformer.toScanResultResponses(session.getScanResultSet()))
                .startedAt(session.getCreationDate())
                .build();
    }
}
