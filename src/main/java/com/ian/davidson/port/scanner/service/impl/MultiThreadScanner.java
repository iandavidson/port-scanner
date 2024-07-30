package com.ian.davidson.port.scanner.service.impl;

import com.ian.davidson.port.scanner.model.queue.ScanItinerary;
import com.ian.davidson.port.scanner.model.scan.ScanOperation;
import com.ian.davidson.port.scanner.service.Scanner;
import com.ian.davidson.port.scanner.transformer.ScanOperationTransformer;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MultiThreadScanner implements Scanner {

    private final ScanOperationTransformer scanOperationTransformer;

    public MultiThreadScanner(final ScanOperationTransformer scanOperationTransformer){
        this.scanOperationTransformer = scanOperationTransformer;
    }

    @Override
    public void executeScan(final ScanItinerary scanItinerary) {
        List<ScanOperation> scanOperations = scanOperationTransformer.scanItineraryToScanOperations(scanItinerary);

    }
}
