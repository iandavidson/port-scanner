package com.ian.davidson.port.scanner.service;

import com.ian.davidson.port.scanner.model.entity.Port;
import com.ian.davidson.port.scanner.repository.PortRepository;
import com.ian.davidson.port.scanner.service.impl.PortServiceImpl;
import java.time.LocalDateTime;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class PortServiceTest {

    private static final Long TENANT_ID = 1L;

    private static final Set<Port> PORT_SET = Set.of(
            Port.builder().tenantId(TENANT_ID).port(22).id(1L).creationDate(LocalDateTime.now()).build(),
            Port.builder().tenantId(TENANT_ID).port(25).id(2L).creationDate(LocalDateTime.now()).build(),
            Port.builder().tenantId(TENANT_ID).port(80).id(3L).creationDate(LocalDateTime.now()).build()
    );

    private static final Set<Port> EXISTING_PORT_SET = Set.of(
            Port.builder().tenantId(TENANT_ID).port(80).id(3L).creationDate(LocalDateTime.now()).build(),
            Port.builder().tenantId(TENANT_ID).port(443).id(4L).creationDate(LocalDateTime.now()).build()
    );

    //add ports
    @Test
    void addPorts() {
        PortRepository portRepository = mock(PortRepository.class);

        PortService portService = new PortServiceImpl(portRepository);
        portService.addPorts(PORT_SET);

        verify(portRepository).saveAll(PORT_SET);
    }

    //delete ports By tenantId
    @Test
    void deletePortsByTenantId() {
        PortRepository portRepository = mock(PortRepository.class);
        doNothing().when(portRepository).deleteAllByTenantId(TENANT_ID);

        PortService portService = new PortServiceImpl(portRepository);
        portService.deletePortsByTenantId(TENANT_ID);

        verify(portRepository).deleteAllByTenantId(TENANT_ID);
    }

    //update ports by tenantId
    @Test
    void updatePortsByTenantId() {
        PortRepository portRepository = mock(PortRepository.class);
        when(portRepository.findAllByTenantId(TENANT_ID)).thenReturn(EXISTING_PORT_SET);

        PortService portService = new PortServiceImpl(portRepository);
        portService.updatePortsByTenantId(PORT_SET, TENANT_ID);

        verify(portRepository).findAllByTenantId(TENANT_ID);
        verify(portRepository).saveAll(anySet());
        verify(portRepository).deleteAll(anySet());
    }
}
