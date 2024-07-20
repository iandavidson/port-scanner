package com.ian.davidson.port.scanner.model.response;

import com.ian.davidson.port.scanner.model.ScanOverview;
import java.util.List;
import lombok.Builder;

@Builder
public record ScanOverviewResponse(
        Long tenantId,
        List<ScanOverview> scanOverviews){

}
