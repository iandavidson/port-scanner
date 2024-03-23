package com.ian.davidson.port.scanner.service;

import com.ian.davidson.port.scanner.component.ScanQueue;
import com.ian.davidson.port.scanner.model.request.ScanRequest;
import com.ian.davidson.port.scanner.model.response.ScanResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class ScanManagerServiceImpl implements ScanManagerService {

    private final ScanQueue scanQueue;
    //jpa repo

    public ScanManagerServiceImpl(final ScanQueue scanQueue){
        this.scanQueue = scanQueue;
    }

    @Override
    public ScanResponse initializeScan(@Valid ScanRequest scanRequest) {
        //persist into db

        //push to queue to be executed and results recorded.
        try {
            scanQueue.waitIsNotFull();
            scanQueue.add(scanRequest);
        } catch (RuntimeException e) {
            throw new IllegalStateException("Failed to execute scan");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return ScanResponse.builder().id(-1).build();
    }

    @Override
    public ScanResponse getScan(Long id) {
        //read JPA REPO, still gotta make that ish.

        return null;
    }
}
