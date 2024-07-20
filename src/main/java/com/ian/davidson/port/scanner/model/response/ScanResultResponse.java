package com.ian.davidson.port.scanner.model.response;

import com.ian.davidson.port.scanner.model.entity.ConnectionStatus;
import lombok.Builder;

@Builder
public record ScanResultResponse(
        Integer port,
        String address,
        ConnectionStatus connectionStatus) {
}
