package com.ian.davidson.port.scanner.transformer;

import com.ian.davidson.port.scanner.model.entity.Port;
import com.ian.davidson.port.scanner.validation.PortValidator;
import jakarta.validation.ValidationException;
import java.util.Set;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PortTransformerTest {

    @Test
    void toPort_success() {
        Integer port = 3306;
        Long tenantId = 1L;

        PortValidator portValidator = mock(PortValidator.class);
        when(portValidator.isValid(port)).thenReturn(port);

        PortTransformer portTransformer = new PortTransformer(portValidator);
        Port result = portTransformer.toPort(port, tenantId);

        assertThat(result).isNotNull();
        assertThat(result.getPort()).isEqualTo(port);
        assertThat(result.getTenantId()).isEqualTo(tenantId);

        verify(portValidator).isValid(port);
    }

    @Test
    void toPort_failure() {
        Integer port = -1;
        Long tenantId = 1L;

        PortValidator portValidator = mock(PortValidator.class);
        when(portValidator.isValid(port)).thenThrow(new ValidationException("Invalid port"));

        PortTransformer portTransformer = new PortTransformer(portValidator);
        assertThatThrownBy(() -> portTransformer.toPort(port, tenantId)).isInstanceOf(ValidationException.class)
                .hasMessage("Invalid port");
    }


    @Test
    void toPorts_success() {
        Set<Integer> ports = Set.of(25, 443, 3306);
        Long tenantId = 1L;

        PortValidator portValidator = mock(PortValidator.class);
        when(portValidator.isValid(25)).thenReturn(25);
        when(portValidator.isValid(443)).thenReturn(443);
        when(portValidator.isValid(3306)).thenReturn(3306);

        PortTransformer portTransformer = new PortTransformer(portValidator);
        Set<Port> results = portTransformer.toPorts(ports, tenantId);

        assertThat(results).isNotEmpty();
        assertThat(results.size()).isEqualTo(3);
        for (Port port : results) {
            assertThat(port.getTenantId()).isEqualTo(tenantId);
        }

        verify(portValidator).isValid(25);
        verify(portValidator).isValid(443);
        verify(portValidator).isValid(3306);
    }
}
