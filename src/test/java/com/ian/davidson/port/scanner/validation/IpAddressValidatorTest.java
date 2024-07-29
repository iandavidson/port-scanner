package com.ian.davidson.port.scanner.validation;

import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
public class IpAddressValidatorTest {

    @Test
    void isValid_success() {
        String addr = "12.12.12.12";
        IpAddressValidator ipAddressValidator = new IpAddressValidator();
        String res = ipAddressValidator.isValid(addr);

        assertThat(res).isNotNull();
        assertThat(res).isEqualTo(addr);
    }


    @Test
    void isValid_failure() {
        String addr = "1222.400.abc.def";
        IpAddressValidator ipAddressValidator = new IpAddressValidator();

        assertThatThrownBy( () -> ipAddressValidator.isValid(addr)).isInstanceOf(ValidationException.class)
                .hasMessage("Address provided: " + addr + ", does not follow IP convention");
    }
}
