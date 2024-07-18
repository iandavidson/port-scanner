package com.ian.davidson.port.scanner.transformer;

import com.ian.davidson.port.scanner.model.entity.Port;
import java.util.Set;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PortTransformerTest {

    @Test
    void toPort_success() {
        Integer port = 3306;
        Long tenantId = 1L;

        PortTransformer portTransformer = new PortTransformer();
        Port result = portTransformer.toPort(port, tenantId);

        assertThat(result).isNotNull();
        assertThat(result.getPort()).isEqualTo(port);
        assertThat(result.getTenantId()).isEqualTo(tenantId);
    }

    @Test
    void toAddresses_success() {
        Set<Integer> ports = Set.of(25, 443, 3306);
        Long tenantId = 1L;

        PortTransformer portTransformer = new PortTransformer();
        Set<Port> results = portTransformer.toPorts(ports, tenantId);

        assertThat(results).isNotEmpty();
        assertThat(results.size()).isEqualTo(3);
        for (Port port : results) {
            assertThat(port.getTenantId()).isEqualTo(tenantId);
        }
    }
}
