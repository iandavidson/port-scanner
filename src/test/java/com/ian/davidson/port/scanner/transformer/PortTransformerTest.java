package com.ian.davidson.port.scanner.transformer;

import com.ian.davidson.port.scanner.model.entity.Port;
import com.ian.davidson.port.scanner.model.entity.Tenant;
import java.util.Set;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class PortTransformerTest {

    @Test
    void toPort_success() {
        Integer port = 3306;
        Tenant tenant = mock(Tenant.class);

        PortTransformer portTransformer = new PortTransformer();
        Port result = portTransformer.toPort(port, tenant);

        assertThat(result).isNotNull();
        assertThat(result.getPort()).isEqualTo(port);
        assertThat(result.getTenant()).isNotNull();
    }

    @Test
    void toAddresses_success() {
        Set<Integer> ports = Set.of(25, 443, 3306);
        Tenant tenant = mock(Tenant.class);

        PortTransformer portTransformer = new PortTransformer();
        Set<Port> results = portTransformer.toPorts(ports, tenant);

        assertThat(results).isNotEmpty();
        assertThat(results.size()).isEqualTo(3);
        for (Port port : results) {
            assertThat(port.getTenant()).isNotNull();
        }
    }
}
