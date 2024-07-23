package com.ian.davidson.port.scanner.model.response;

import java.util.List;
import lombok.Builder;

@Builder
public record ScanOverviewResponse(
        Long tenantId,
        List<ScanOverview> scanOverviews){

}
