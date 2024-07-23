package com.ian.davidson.port.scanner.service.impl;

import com.ian.davidson.port.scanner.model.entity.Tenant;
import com.ian.davidson.port.scanner.model.queue.ScanItinerary;
import com.ian.davidson.port.scanner.queue.Producer;
import com.ian.davidson.port.scanner.service.ScanService;
import org.springframework.stereotype.Service;

@Service
public class ScanServiceImpl implements ScanService {

    private final Producer producer;

    public ScanServiceImpl(final Producer producer) {
        this.producer = producer;
    }

    @Override
    public void initializeScan(final Tenant tenant, final Long sessionId) {
        //make instance composed of {tenantId, sessionId, ports, addresses}
        producer.send(ScanItinerary.newScanComposition(tenant, sessionId));
    }
}
