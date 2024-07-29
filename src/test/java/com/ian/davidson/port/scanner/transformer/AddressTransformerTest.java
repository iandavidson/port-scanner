package com.ian.davidson.port.scanner.transformer;

import com.ian.davidson.port.scanner.model.entity.Address;
import com.ian.davidson.port.scanner.validation.IpAddressValidator;
import jakarta.validation.ValidationException;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class AddressTransformerTest {

    @Test
    void toAddress_success() {
        String addr = "123.12.123.12";
        Long tenantId = 1L;

        IpAddressValidator ipAddressValidator = mock(IpAddressValidator.class);
        when(ipAddressValidator.isValid(addr)).thenReturn(addr);

        AddressTransformer addressTransformer = new AddressTransformer(ipAddressValidator);
        Address result = addressTransformer.toAddress(addr, tenantId);

        assertThat(result).isNotNull();
        assertThat(result.getAddress()).isEqualTo(addr);
        assertThat(result.getTenantId()).isEqualTo(tenantId);

        verify(ipAddressValidator).isValid(addr);
    }


    @Test
    void toAddress_failedValidation() {
        String addr = "400.12.123.12";
        Long tenantId = 1L;

        String message = "message";

        IpAddressValidator ipAddressValidator = mock(IpAddressValidator.class);
        when(ipAddressValidator.isValid(addr)).thenThrow(new ValidationException("message"));

        AddressTransformer addressTransformer = new AddressTransformer(ipAddressValidator);
        assertThatThrownBy(() -> addressTransformer.toAddress(addr, tenantId)).hasMessage("message")
                .isInstanceOf(ValidationException.class);

        verify(ipAddressValidator).isValid(addr);
    }

    @Test
    void toAddresses_success() {
        Set<String> addresses = Set.of("123.12.123.12", "99.99.99.99", "40.41.42.43");
        Long tenantId = 1L;

        IpAddressValidator ipAddressValidator = mock(IpAddressValidator.class);
        when(ipAddressValidator.isValid("123.12.123.12")).thenReturn("123.12.123.12");
        when(ipAddressValidator.isValid("99.99.99.99")).thenReturn("99.99.99.99");
        when(ipAddressValidator.isValid("40.41.42.43")).thenReturn("40.41.42.43");

        AddressTransformer addressTransformer = new AddressTransformer(ipAddressValidator);
        Set<Address> results = addressTransformer.toAddresses(addresses, tenantId);

        assertThat(results).isNotEmpty();
        assertThat(results.size()).isEqualTo(3);
        for (Address address : results) {
            assertThat(address.getTenantId()).isEqualTo(tenantId);
        }

        verify(ipAddressValidator).isValid("123.12.123.12");
        verify(ipAddressValidator).isValid("99.99.99.99");
        verify(ipAddressValidator).isValid("40.41.42.43");
    }

}
