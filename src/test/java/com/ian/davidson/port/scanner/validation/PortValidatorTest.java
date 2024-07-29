package com.ian.davidson.port.scanner.validation;

import com.ian.davidson.port.scanner.model.entity.Port;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
public class PortValidatorTest {

    @Test
    void isValid_success(){
        Integer port = 443;

        PortValidator portValidator = new PortValidator();
        Integer res = portValidator.isValid(port);

        assertThat(res).isNotNull();
        assertThat(res).isEqualTo(port);
    }

    @Test
    void isValid_faiure(){
        Integer port = -1;

        PortValidator portValidator = new PortValidator();
        assertThatThrownBy(() -> portValidator.isValid(port)).isInstanceOf(ValidationException.class)
                .hasMessage("Invalid port value provided: " + port);
    }
}
