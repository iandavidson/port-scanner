package com.ian.davidson.port.scanner.transformer;

import com.ian.davidson.port.scanner.model.entity.ScanResult;
import com.ian.davidson.port.scanner.model.entity.Session;
import com.ian.davidson.port.scanner.model.response.ScanResultResponse;
import com.ian.davidson.port.scanner.model.response.SessionResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class SessionTransformerTest {

    private static final Long SESSION_ID = 1L;
    private static final Long TENANT_ID = 100L;
    private static final LocalDateTime NOW = LocalDateTime.now();

    @Test
    void toSession() {
        Session session = new SessionTransformer(null).toSession(TENANT_ID);

        assertThat(session).isNotNull();
        assertThat(session.getTenantId()).isEqualTo(TENANT_ID);
    }

    @Test
    void toSessionResponse() {
        Set<ScanResult> scanResults = new HashSet<>();
        List<ScanResultResponse> scanResultResponses = new ArrayList<>();


        ScanResultTransformer scanResultTransformer = mock(ScanResultTransformer.class);
        when(scanResultTransformer.toScanResultResponses(scanResults)).thenReturn(scanResultResponses);

        Session session = mock(Session.class);
        when(session.getId()).thenReturn(SESSION_ID);
        when(session.getTenantId()).thenReturn(TENANT_ID);
        when(session.getCreationDate()).thenReturn(NOW);
        when(session.getScanResultSet()).thenReturn(scanResults);

        SessionResponse sessionResponse = new SessionTransformer(scanResultTransformer).toSessionResponse(session);

        assertThat(sessionResponse).isNotNull();
        assertThat(sessionResponse.sessionId()).isEqualTo(SESSION_ID);
        assertThat(sessionResponse.tenantId()).isEqualTo(TENANT_ID);
        assertThat(sessionResponse.startedAt()).isEqualTo(NOW);
        assertThat(sessionResponse.scanResults()).isNotNull();
        assertThat(sessionResponse.scanResults().isEmpty()).isTrue();

        verify(scanResultTransformer).toScanResultResponses(scanResults);

        verify(session).getId();
        verify(session).getTenantId();
        verify(session).getScanResultSet();
        verify(session).getCreationDate();
    }
}
