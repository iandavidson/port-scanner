package com.ian.davidson.port.scanner.model.response;

import com.ian.davidson.port.scanner.model.entity.ConnectionStatus;
import lombok.Builder;

@Builder
public record ScanResultResponse(
        Integer port,
        String address,
        ConnectionStatus connectionStatus) implements Comparable<ScanResultResponse>{
    @Override
    public int compareTo(ScanResultResponse o) {
        if(address.equals(o.address)){
            return port.compareTo(o.port);
        }else{
            return address.compareTo(o.address);
        }
    }
}
