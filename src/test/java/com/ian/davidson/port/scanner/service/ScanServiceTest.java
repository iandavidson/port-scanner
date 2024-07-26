package com.ian.davidson.port.scanner.service;

import com.ian.davidson.port.scanner.model.entity.Tenant;
import com.ian.davidson.port.scanner.model.queue.ScanItinerary;
import com.ian.davidson.port.scanner.queue.Producer;
import com.ian.davidson.port.scanner.service.impl.ScanServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class ScanServiceTest {

    @Test
    void initializeScan() {
        ScanItinerary scanItinerary = mock(ScanItinerary.class);
        Tenant tenant = mock(Tenant.class);
        Long sessionId = 1L;

        Producer producer = mock(Producer.class);
        doNothing().when(producer).send(scanItinerary);

        try (MockedStatic<ScanItinerary> scanItineraryStatic = mockStatic(ScanItinerary.class)) {
            scanItineraryStatic
                    .when(() -> ScanItinerary.newScanComposition(tenant, sessionId))
                    .thenReturn(scanItinerary);

            ScanService scanService = new ScanServiceImpl(producer);
            scanService.initializeScan(tenant, sessionId);
        }
        verify(producer).send(scanItinerary);
    }
}