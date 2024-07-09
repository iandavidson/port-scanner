package com.ian.davidson.port.scanner.service.impl;

import com.ian.davidson.port.scanner.model.request.ScanRequest;
import com.ian.davidson.port.scanner.model.response.ScanResponse;
import com.ian.davidson.port.scanner.queue.ScanDispatchProducer;
import com.ian.davidson.port.scanner.service.ScanService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class ScanServiceImpl implements ScanService {

    private final ScanDispatchProducer scanDispatchProducer;
//    private final ScanQueue scanQueue;
    //jpa repo

    public ScanServiceImpl(final ScanDispatchProducer scanDispatchProducer){
        this.scanDispatchProducer = scanDispatchProducer;
    }


//    public ScanManagerServiceImpl(final ScanQueue scanQueue){
//        this.scanQueue = scanQueue;
//    }

    @Override
    public ScanResponse initializeScan(@Valid ScanRequest scanRequest) {
        //persist into db

        //push to queue to be executed and results recorded.
        this.scanDispatchProducer.produce();

        //return tenant id
        return ScanResponse.builder().id(-1).build();

    }

    @Override
    public ScanResponse getScan(Long id) {
        //read JPA REPO, still gotta make that ish.

        return null;
    }
}
