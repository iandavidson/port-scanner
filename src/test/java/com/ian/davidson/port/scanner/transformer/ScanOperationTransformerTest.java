package com.ian.davidson.port.scanner.transformer;

import com.ian.davidson.port.scanner.model.queue.ScanItinerary;
import com.ian.davidson.port.scanner.model.scan.ScanOperation;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
public class ScanOperationTransformerTest {

    @Test
    void scanItineraryToScanOperations_success(){
        Set<String> addressSet = Set.of("12.12.12.12", "1.1.1.1", "2.2.2.2");
        Set<Integer> portSet = Set.of(22, 80, 443);

        ScanItinerary scanItinerary = ScanItinerary.builder()
                .addresses(addressSet)
                .ports(portSet)
                .sessionId(1L)
                .build();

        ScanOperationTransformer scanOperationTransformer = new ScanOperationTransformer();
        List<ScanOperation> result = scanOperationTransformer.scanItineraryToScanOperations(scanItinerary);

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(addressSet.size() * portSet.size());
    }
}
