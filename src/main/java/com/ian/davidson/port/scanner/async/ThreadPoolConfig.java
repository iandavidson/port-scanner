package com.ian.davidson.port.scanner.async;

import java.util.concurrent.Executor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@Configuration
public class ThreadPoolConfig {

    private final AsyncConfig asyncConfig;

    public ThreadPoolConfig(final AsyncConfig asyncConfig) {
        this.asyncConfig = asyncConfig;
    }

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(asyncConfig.getThreadPoolCoreSize());
        executor.setMaxPoolSize(asyncConfig.getThreadPoolMaxSize());
        executor.setQueueCapacity(asyncConfig.getQueueSize());
        executor.setThreadNamePrefix(asyncConfig.getThreadNamePrefix());
        executor.initialize();
        return executor;
    }
}
