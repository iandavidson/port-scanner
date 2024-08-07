package com.ian.davidson.port.scanner.async;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;


@Configuration
@Data
public class AsyncConfig {

    private final int timeout; //in ms
    private final int threadPoolCoreSize;
    private final int threadPoolMaxSize;
    private final String threadNamePrefix;
    private final int queueSize;

    public AsyncConfig(@Value("${scanner.async.time-out}") final int timeout,
                         @Value("${scanner.async.thread-pool-core}") final int threadPoolCoreSize,
                         @Value("${scanner.async.thread-pool-max}") final int threadPoolMaxSize,
                         @Value("${scanner.async.thread-name-prefix}") final String threadNamePrefix,
                         @Value("${scanner.async.queue-size}") final int queueSize){

        this.timeout = timeout;
        this.threadPoolCoreSize = threadPoolCoreSize;
        this.threadPoolMaxSize = threadPoolMaxSize;
        this.threadNamePrefix = threadNamePrefix;
        this.queueSize = queueSize;
    }
}
