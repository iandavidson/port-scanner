package com.ian.davidson.port.scanner.component;


import com.ian.davidson.port.scanner.model.request.ScanRequest;
import com.ian.davidson.port.scanner.service.ScanExecutionService;
import org.springframework.stereotype.Component;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class ScanQueue {

    //TODO set as property
    private static final int MAX_QUEUE_SIZE = 10;
    private final ScanExecutionService scanExecutionService;
    private final LinkedBlockingQueue<ScanRequest> queue;
    private final Object IS_NOT_FULL = new Object();
    private final Object IS_NOT_EMPTY = new Object();
    public ScanQueue(final ScanExecutionService scanExecutionService) {
        this.scanExecutionService = scanExecutionService;
        this.queue = new LinkedBlockingQueue<>(MAX_QUEUE_SIZE);
    }

    public void execute(ScanRequest scan){
        queue.add(scan);
    }

    public void waitIsNotFull() throws InterruptedException {
        synchronized (IS_NOT_FULL) {
            IS_NOT_FULL.wait();
        }
    }

    private void notifyIsNotFull() {
        synchronized (IS_NOT_FULL) {
            IS_NOT_FULL.notify();
        }
    }



}
