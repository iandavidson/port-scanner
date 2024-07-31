package com.ian.davidson.port.scanner.async;

import com.ian.davidson.port.scanner.model.entity.ScanResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ScanOperationExecutorImpl implements ScanOperationExecutor {

    private final ThreadPoolTaskExecutor taskExecutor;

    public ScanOperationExecutorImpl(final Executor taskExecutor) {
        this.taskExecutor = (ThreadPoolTaskExecutor) taskExecutor;
    }

    @Override
    public void executeScan(final List<ScanOperationTask> scanOperationTasks,
                            final List<ScanResult> results) {

        List<Future<ScanResult>> futures = Collections.synchronizedList(new ArrayList<>());

        try {
            for (ScanOperationTask task : scanOperationTasks) {
                futures.add(taskExecutor.submit(task));
            }

            for (Future<ScanResult> future : futures) {
                try {
                    results.add(future.get());
                } catch (Exception e) {
                    log.error("Couldn't add result for some reason: {}", e);
                }
            }
        } catch (Exception e) {
            log.error("Unexpected Exception thrown during session scan: {}", e);
        }
    }
}
