package com.ian.davidson.port.scanner.async;

import com.ian.davidson.port.scanner.config.ScannerConfig;
import java.util.concurrent.Executor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@Configuration
public class ThreadPoolConfig {

    private final ScannerConfig scannerConfig;

    public ThreadPoolConfig(final ScannerConfig scannerConfig) {
        this.scannerConfig = scannerConfig;
    }

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(scannerConfig.getThreadPoolCoreSize());
        executor.setMaxPoolSize(scannerConfig.getThreadPoolMaxSize());
        executor.setQueueCapacity(scannerConfig.getQueueSize());
        executor.setThreadNamePrefix("ThreadPool-");
        executor.initialize();
        return executor;
    }
}
