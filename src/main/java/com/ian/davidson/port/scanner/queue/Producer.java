package com.ian.davidson.port.scanner.queue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ian.davidson.port.scanner.config.ObjectMapperConfig;
import com.ian.davidson.port.scanner.config.RabbitConfig;
import com.ian.davidson.port.scanner.model.queue.ScanItinerary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

//TODO: give it a better name once I validate this pattern of config works
@Slf4j
@Component
public class Producer {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitConfig rabbitConfig;
    private final ObjectMapper objectMapper;

    public Producer(final RabbitTemplate rabbitTemplate,
                    final RabbitConfig rabbitConfig,
                    final ObjectMapperConfig objectMapperConfig) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitConfig = rabbitConfig;
        this.objectMapper = objectMapperConfig.objectMapper();
    }

    public void send(final ScanItinerary scanItinerary) {
        try{
            String payload = objectMapper.writeValueAsString(scanItinerary);
            log.info("Attempting to produce: {}", payload);
            rabbitTemplate.convertAndSend(
                    rabbitConfig.getTopicExchangeName(),
                    "foo.bar.baz",
                    payload);
        }catch(JsonProcessingException e){
            log.error("Couldn't serialize this obj: {}", scanItinerary);
            throw new IllegalStateException("couldn't scan itinerary to serialize into json");
        }
    }

}
