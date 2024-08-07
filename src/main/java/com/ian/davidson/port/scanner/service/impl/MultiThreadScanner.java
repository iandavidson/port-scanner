package com.ian.davidson.port.scanner.service.impl;

import com.ian.davidson.port.scanner.async.AsyncConfig;
import com.ian.davidson.port.scanner.async.ScanOperationExecutor;
import com.ian.davidson.port.scanner.async.ScanOperationTask;
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

    private final AsyncConfig asyncConfig;
    private final ScanOperationExecutor scanOperationExecutor;
    private final ScanResultRepository scanResultRepository;
    private final ScanOperationTransformer scanOperationTransformer;

    public MultiThreadScanner(final AsyncConfig asyncConfig,
                              final ScanOperationExecutor scanOperationExecutor,
                              final ScanResultRepository scanResultRepository,
                              final ScanOperationTransformer scanOperationTransformer) {
        this.asyncConfig = asyncConfig;
        this.scanOperationExecutor = scanOperationExecutor;
        this.scanResultRepository = scanResultRepository;
        this.scanOperationTransformer = scanOperationTransformer;
    }

    @Override
    public void executeScan(final ScanItinerary scanItinerary) {

        log.info("Starting scan on session: {}", scanItinerary.sessionId());

        //convert into atomic tasks to be done in parallel
        List<ScanOperation> scanOperations = scanOperationTransformer.scanItineraryToScanOperations(scanItinerary);
        List<ScanOperationTask> tasks = scanOperations.stream()
                .map(op -> ScanOperationTask.builder().scanOperation(op).timeout(asyncConfig.getTimeout()).build()).toList();

        //execute tasks
        List<ScanResult> scanResults = Collections.synchronizedList(new ArrayList<>());
        scanOperationExecutor.executeScan(tasks, scanResults);

        //save results
        scanResultRepository.saveAll(scanResults);
    }
}
