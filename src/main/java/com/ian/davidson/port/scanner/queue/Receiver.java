package com.ian.davidson.port.scanner.queue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ian.davidson.port.scanner.config.ObjectMapperConfig;
import com.ian.davidson.port.scanner.model.queue.ScanItinerary;
import com.ian.davidson.port.scanner.service.SingleThreadScanner;
import java.util.concurrent.CountDownLatch;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Component
public class Receiver {
    private final CountDownLatch latch;
    private final ObjectMapper objectMapper;
    private final SingleThreadScanner singleThreadScanner;


    public Receiver(final ObjectMapperConfig objectMapperConfig,
                    final SingleThreadScanner singleThreadScanner){
        this.latch = new CountDownLatch(1);
        this.objectMapper = objectMapperConfig.objectMapper();
        this.singleThreadScanner = singleThreadScanner;
    }

    public void receiveMessage(String message) {
        log.info("Received: {}", message);

        try {
            ScanItinerary scanItinerary = objectMapper.readValue(message, ScanItinerary.class);

            //Todo: build out a multithreaded approach
            singleThreadScanner.executeScan(scanItinerary);

            log.info("success");
        } catch (JsonProcessingException e) {
            log.error("Jiminy friggin crickets");
            throw new RuntimeException(e);
        }
        latch.countDown();
    }
}
