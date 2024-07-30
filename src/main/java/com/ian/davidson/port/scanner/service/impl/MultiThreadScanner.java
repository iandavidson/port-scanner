package com.ian.davidson.port.scanner.service.impl;

import com.ian.davidson.port.scanner.async.ScanOperationExecutor;
import com.ian.davidson.port.scanner.async.ScanOperationTask;
import com.ian.davidson.port.scanner.config.ScannerConfig;
import com.ian.davidson.port.scanner.model.entity.ScanResult;
import com.ian.davidson.port.scanner.model.queue.ScanItinerary;
import com.ian.davidson.port.scanner.model.scan.ScanOperation;
import com.ian.davidson.port.scanner.repository.ScanResultRepository;
import com.ian.davidson.port.scanner.service.Scanner;
import com.ian.davidson.port.scanner.transformer.ScanOperationTransformer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MultiThreadScanner implements Scanner {

    private final ScanOperationTransformer scanOperationTransformer;
    private final ScanOperationExecutor scanOperationExecutor;
    private final ScannerConfig scannerConfig;
    private final ScanResultRepository scanResultRepository;

    public MultiThreadScanner(final ScanOperationTransformer scanOperationTransformer,
                              final ScanOperationExecutor scanOperationExecutor,
                              final ScannerConfig scannerConfig,
                              final ScanResultRepository scanResultRepository) {
        this.scanOperationTransformer = scanOperationTransformer;
        this.scanOperationExecutor = scanOperationExecutor;
        this.scannerConfig = scannerConfig;
        this.scanResultRepository = scanResultRepository;
    }

    @Override
    public void executeScan(final ScanItinerary scanItinerary) {

        log.info("Starting scan on session: {}", scanItinerary.sessionId());

        //convert into atomic tasks to be done in parallel
        List<ScanOperation> scanOperations = scanOperationTransformer.scanItineraryToScanOperations(scanItinerary);
        List<ScanOperationTask> tasks = scanOperations.stream()
                .map(op -> ScanOperationTask.builder().scanOperation(op).timeout(scannerConfig.getTimeout()).build()).toList();

        //execute tasks
        List<ScanResult> scanResults = Collections.synchronizedList(new ArrayList<>());
        scanOperationExecutor.executeScan(tasks, scanResults);

        //save results
        scanResultRepository.saveAll(scanResults);
    }
}
