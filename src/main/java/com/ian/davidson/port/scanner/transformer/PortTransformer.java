package com.ian.davidson.port.scanner.transformer;

import com.ian.davidson.port.scanner.model.entity.Port;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class PortTransformer {

    public Set<Port> toPorts(final Set<Integer> rawPorts) {
        return rawPorts.stream().map(this::toPort).collect(Collectors.toSet());
    }

    public Port toPort(final Integer rawPort) {
        return Port.builder().port(rawPort).build();
    }
}
