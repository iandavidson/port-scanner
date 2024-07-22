package com.ian.davidson.port.scanner.service.impl;

import com.ian.davidson.port.scanner.model.entity.Tenant;
import com.ian.davidson.port.scanner.queue.ScanDispatchProducer;
import com.ian.davidson.port.scanner.service.ScanService;
import org.springframework.stereotype.Service;

@Service
public class ScanServiceImpl implements ScanService {

    private final ScanDispatchProducer scanDispatchProducer;
//    private final ScanQueue scanQueue;
    //jpa repo

    public ScanServiceImpl(final ScanDispatchProducer scanDispatchProducer) {
        this.scanDispatchProducer = scanDispatchProducer;
    }

    @Override
    public void initializeScan(final Tenant tenant, final Long sessionId) {
        //make instance composed of {tenantId, sessionId, ports, addresses}

        //connect to producer, send this off
    }
}
