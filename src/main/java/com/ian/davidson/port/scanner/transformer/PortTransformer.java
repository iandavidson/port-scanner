package com.ian.davidson.port.scanner.transformer;

import com.ian.davidson.port.scanner.model.entity.Port;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class PortTransformer {

    public Set<Port> toPorts(final Set<Integer> rawPorts, final Long tenantId) {
        return rawPorts.stream().map(port -> toPort(port, tenantId)).collect(Collectors.toSet());
    }

    public Port toPort(final Integer rawPort, final Long tenantId) {
        return Port.builder().port(rawPort).tenantId(tenantId).build();
    }
}
