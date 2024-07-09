package com.ian.davidson.port.scanner.transformer;

import com.ian.davidson.port.scanner.model.entity.Port;
import com.ian.davidson.port.scanner.model.entity.Tenant;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class PortTransformer {

    public Set<Port> toPorts(final Set<Integer> rawPorts, final Tenant tenant) {
        return rawPorts.stream().map(port -> toPort(port, tenant)).collect(Collectors.toSet());
    }

    public Port toPort(final Integer rawPort, final Tenant tenant) {
        return Port.builder().port(rawPort).tenant(tenant).build();
    }
}
