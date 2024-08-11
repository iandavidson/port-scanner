package com.ian.davidson.port.scanner.transformer;

import com.ian.davidson.port.scanner.model.response.ScanOverview;
import com.ian.davidson.port.scanner.model.entity.ScanResult;
import com.ian.davidson.port.scanner.model.entity.Tenant;
import com.ian.davidson.port.scanner.model.response.ScanOverviewResponse;
import com.ian.davidson.port.scanner.model.response.ScanResultResponse;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class ScanResultTransformer {

    public List<ScanResultResponse> toScanResultResponses(final Set<ScanResult> scanResults) {
        return scanResults == null
                ? Collections.emptyList()
                : scanResults.stream().map(this::toScanResultResponse).sorted().toList();
    }

    public ScanResultResponse toScanResultResponse(final ScanResult scanResult) {
        return ScanResultResponse.builder()
                .address(scanResult.getAddress())
                .port(scanResult.getPort())
                .connectionStatus(scanResult.getStatus())
                .build();
    }

    public ScanOverviewResponse toScanOverviewResponse(final Tenant tenant) {
        return ScanOverviewResponse.builder()
                .tenantId(tenant.getId())
                .scanOverviews(
                        tenant.getSessions().stream().map(
                                session -> ScanOverview.builder().sessionId(session.getId()).started(session.getCreationDate()).build())
                                .sorted()
                                .toList())
                .build();
    }

}
