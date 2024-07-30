package com.ian.davidson.port.scanner.transformer;

import com.ian.davidson.port.scanner.model.queue.ScanItinerary;
import com.ian.davidson.port.scanner.model.scan.ScanOperation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ScanOperationTransformer {

    public List<ScanOperation> scanItineraryToScanOperations(final ScanItinerary scanItinerary){
        List<ScanOperation> scanOperations = new ArrayList<>();

        for(String address: scanItinerary.addresses()){
            for(Integer port: scanItinerary.ports()){
                scanOperations.add(
                        ScanOperation.builder()
                                .sessionId(scanItinerary.sessionId())
                                .address(address)
                                .port(port)
                                .build());
            }
        }

        return scanOperations;
    }
}
