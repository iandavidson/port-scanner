package com.ian.davidson.port.scanner.transformer;

import com.ian.davidson.port.scanner.model.entity.ConnectionStatus;
import com.ian.davidson.port.scanner.model.entity.ScanResult;
import com.ian.davidson.port.scanner.model.entity.Session;
import com.ian.davidson.port.scanner.model.entity.Tenant;
import com.ian.davidson.port.scanner.model.response.ScanOverviewResponse;
import com.ian.davidson.port.scanner.model.response.ScanResultResponse;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
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
public class ScanResultTransformerTest {


    private static final int PORT_1 = 22;
    private static final int PORT_2 = 25;
    private static final String ADDRESS_1 = "8.8.8.8";
    private static final String ADDRESS_2 = "8.8.8.9";
    private static final ConnectionStatus STATUS_1 = ConnectionStatus.TIMEOUT;
    private static final ConnectionStatus STATUS_2 = ConnectionStatus.OPEN;


    @Test
    void toScanResultResponses_success() {
        ScanResult scanResult1 = mock(ScanResult.class);
        when(scanResult1.getPort()).thenReturn(PORT_1);
        when(scanResult1.getAddress()).thenReturn(ADDRESS_1);
        when(scanResult1.getStatus()).thenReturn(STATUS_1);

        ScanResult scanResult2 = mock(ScanResult.class);
        when(scanResult2.getPort()).thenReturn(PORT_2);
        when(scanResult2.getAddress()).thenReturn(ADDRESS_2);
        when(scanResult2.getStatus()).thenReturn(STATUS_2);

        Set<ScanResult> scanResultSet = new LinkedHashSet<>();
        scanResultSet.add(scanResult1);
        scanResultSet.add(scanResult2);

        List<ScanResultResponse> results = new ScanResultTransformer().toScanResultResponses(scanResultSet);

        assertThat(results).isNotNull();
        assertThat(results.size()).isEqualTo(2);

        assertThat(results.get(0)).isNotNull();
        assertThat(results.get(0).port()).isEqualTo(PORT_1);
        assertThat(results.get(0).address()).isEqualTo(ADDRESS_1);
        assertThat(results.get(0).connectionStatus()).isEqualTo(STATUS_1);

        verify(scanResult1).getPort();
        verify(scanResult1).getAddress();
        verify(scanResult1).getStatus();

        assertThat(results.get(1)).isNotNull();
        assertThat(results.get(1).port()).isEqualTo(PORT_2);
        assertThat(results.get(1).address()).isEqualTo(ADDRESS_2);
        assertThat(results.get(1).connectionStatus()).isEqualTo(STATUS_2);

        verify(scanResult2).getPort();
        verify(scanResult2).getAddress();
        verify(scanResult2).getStatus();
    }

    @Test
    void toScanResultResponses_null() {
        List<ScanResultResponse> results = new ScanResultTransformer().toScanResultResponses(null);
        assertThat(results).isNotNull();
    }

    @Test
    void toScanResultResponse() {
        ScanResult scanResult = mock(ScanResult.class);
        when(scanResult.getPort()).thenReturn(PORT_1);
        when(scanResult.getAddress()).thenReturn(ADDRESS_1);
        when(scanResult.getStatus()).thenReturn(STATUS_1);

        ScanResultResponse result = new ScanResultTransformer().toScanResultResponse(scanResult);

        assertThat(result).isNotNull();
        assertThat(result.port()).isEqualTo(PORT_1);
        assertThat(result.address()).isEqualTo(ADDRESS_1);
        assertThat(result.connectionStatus()).isEqualTo(STATUS_1);

        verify(scanResult).getPort();
        verify(scanResult).getAddress();
        verify(scanResult).getStatus();
    }

    @Test
    void toScanOverviewResponse() {
        final Long tenantId = 1L;
        final Long session1Id = 100L;
        final Long session2Id = 101L;
        final LocalDateTime sessionCreationTime = LocalDateTime.now();


        Session session1 = mock(Session.class);
        when(session1.getId()).thenReturn(session1Id);
        when(session1.getCreationDate()).thenReturn(sessionCreationTime);

        Session session2 = mock(Session.class);
        when(session2.getId()).thenReturn(session2Id);
        when(session2.getCreationDate()).thenReturn(sessionCreationTime);

        Set<Session> sessions = new LinkedHashSet<>();
        sessions.add(session1);
        sessions.add(session2);

        Tenant tenant = mock(Tenant.class);
        when(tenant.getId()).thenReturn(tenantId);
        when(tenant.getSessions()).thenReturn(sessions);


        ScanOverviewResponse scanOverviewResponse = new ScanResultTransformer().toScanOverviewResponse(tenant);

        assertThat(scanOverviewResponse).isNotNull();
        assertThat(scanOverviewResponse.tenantId()).isEqualTo(tenantId);
        assertThat(scanOverviewResponse.scanOverviews()).isNotNull();

        assertThat(scanOverviewResponse.scanOverviews().get(0).sessionId()).isEqualTo(session1Id);
        assertThat(scanOverviewResponse.scanOverviews().get(0).started()).isEqualTo(sessionCreationTime);
        assertThat(scanOverviewResponse.scanOverviews().get(1).sessionId()).isEqualTo(session2Id);
        assertThat(scanOverviewResponse.scanOverviews().get(1).started()).isEqualTo(sessionCreationTime);

        verify(tenant).getId();
        verify(tenant).getSessions();

        verify(session1).getId();
        verify(session1).getCreationDate();

        verify(session2).getId();
        verify(session2).getCreationDate();
    }
}
