package com.ian.davidson.port.scanner.queue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ian.davidson.port.scanner.config.ObjectMapperConfig;
import com.ian.davidson.port.scanner.model.queue.ScanItinerary;
import com.ian.davidson.port.scanner.service.Scanner;
import com.ian.davidson.port.scanner.service.impl.SingleThreadScanner;
import java.util.concurrent.CountDownLatch;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Component
public class Consumer {
    private final CountDownLatch latch;
    private final ObjectMapper objectMapper;
    private final Scanner scanner;

    public Consumer(final ObjectMapperConfig objectMapperConfig,
                    final SingleThreadScanner singleThreadScanner) {
        this.latch = new CountDownLatch(1);
        this.objectMapper = objectMapperConfig.objectMapper();
        this.scanner = singleThreadScanner;
    }

    //TODO: build wrapper around Consumer class so we can unobtrusively catch fail situations without making this method ugly
    public void consumeMessage(String message) {
        log.info("Received: {}", message);

        try {
            ScanItinerary scanItinerary = objectMapper.readValue(message, ScanItinerary.class);

            //Todo: build out a multithreaded approach
            scanner.executeScan(scanItinerary);

            log.info("success");
        } catch (JsonProcessingException e) {
            log.error("Error While deserializing message");
            throw new RuntimeException(e);
        } catch (RuntimeException e) {
            log.error("Unexpected Exception thrown; cause: {}", e.getCause());
            log.warn("Note: this message indicates message obj may not have been properly processed; \nunlocking " +
                    "latch and moving on");
        }
        latch.countDown();
    }
}
