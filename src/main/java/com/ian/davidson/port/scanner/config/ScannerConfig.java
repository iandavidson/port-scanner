package com.ian.davidson.port.scanner.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;


@Configuration
@Data
public class ScannerConfig {

    private final int timeout; //in ms
    private final int threadPoolSize;

    public ScannerConfig(@Value("${scanner.time-out}") final int timeout,
                         @Value("${scanner.thread-pool-size}") final int threadPoolSize){
        this.timeout = timeout;
        this.threadPoolSize = threadPoolSize;
    }
}
