package com.ian.davidson.port.scanner.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;


@Configuration
@Data
public class ScannerConfig {

    private final int timeout; //in ms
    private final int threadPoolCoreSize;
    private final int threadPoolMaxSize;
    private final String threadNamePrefix;
    private final int queueSize;

    public ScannerConfig(@Value("${scanner.time-out}") final int timeout,
                         @Value("${scanner.thread-pool-core}") final int threadPoolCoreSize,
                         @Value("${scanner.thread-pool-max}") final int threadPoolMaxSize,
                         @Value("${scanner.thread-name-prefix}") final String threadNamePrefix,
                         @Value("${scanner.queue-size}") final int queueSize){

        this.timeout = timeout;
        this.threadPoolCoreSize = threadPoolCoreSize;
        this.threadPoolMaxSize = threadPoolMaxSize;
        this.threadNamePrefix = threadNamePrefix;
        this.queueSize = queueSize;
    }
}
