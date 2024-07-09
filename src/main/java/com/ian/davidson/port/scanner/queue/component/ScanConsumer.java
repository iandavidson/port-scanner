package com.ian.davidson.port.scanner.queue.component;

import org.springframework.stereotype.Component;

@Component
public class ScanConsumer {

    private final ScanQueue scanQueue;

    public ScanConsumer(final ScanQueue scanQueue) {
        this.scanQueue = scanQueue;
    }
}
