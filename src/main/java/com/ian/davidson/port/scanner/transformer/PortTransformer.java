package com.ian.davidson.port.scanner.transformer;

import com.ian.davidson.port.scanner.model.entity.Port;
import com.ian.davidson.port.scanner.validation.PortValidator;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class PortTransformer {

    private final PortValidator portValidator;

    public PortTransformer(final PortValidator portValidator){
        this.portValidator = portValidator;
    }

    public Set<Port> toPorts(final Set<Integer> rawPorts, final Long tenantId) {
        return rawPorts.stream().map(port -> toPort(port, tenantId)).collect(Collectors.toSet());
    }

    public Port toPort(final Integer rawPort, final Long tenantId) {
        return Port.builder()
                .port(portValidator.isValid(rawPort))
                .tenantId(tenantId)
                .build();
    }
}
