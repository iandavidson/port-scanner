package com.ian.davidson.port.scanner.queue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ian.davidson.port.scanner.config.ObjectMapperConfig;
import com.ian.davidson.port.scanner.model.queue.ScanItinerary;
import com.ian.davidson.port.scanner.service.Scanner;
import com.ian.davidson.port.scanner.service.impl.MultiThreadScanner;
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

    /*
    eventually, once we have more than 1 impl of Scanner:
    - use config class to define @Bean(name = "scanner") factory method returning Scanner type
    - this @Bean factory should have the preferred impl defined
    - Use @Qualifier(name = "scanner") in constructor to reference @Bean

    ANOTHER IDEA: leverage a property value that specifies the scanner impl class
     */

    public Consumer(final ObjectMapperConfig objectMapperConfig,
                    final MultiThreadScanner multiThreadScanner) {
        this.latch = new CountDownLatch(1);
        this.objectMapper = objectMapperConfig.objectMapper();
        this.scanner = multiThreadScanner;
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
            log.error("Unexpected Exception thrown; cause: {}", e);
            log.warn("Note: this message indicates message obj may not have been properly processed; \nunlocking " +
                    "latch and moving on");
        }
        latch.countDown();
    }
}
