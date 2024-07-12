package com.ian.davidson.port.scanner.transformer;

import com.ian.davidson.port.scanner.model.entity.Address;
import com.ian.davidson.port.scanner.model.entity.Tenant;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@ExtendWith(SpringExtension.class)
public class AddressTransformerTest {

    @Test
    void toAddress_success() {
        String addr = "123.12.123.12";
        Tenant tenant = mock(Tenant.class);

        AddressTransformer addressTransformer = new AddressTransformer();
        Address result = addressTransformer.toAddress(addr, tenant);

        assertThat(result).isNotNull();
        assertThat(result.getAddress()).isEqualTo(addr);
        assertThat(result.getTenant()).isNotNull();
    }

    @Test
    void toAddresses_success() {
        Set<String> addresses = Set.of("123.12.123.12", "99.99.99.99", "40.41.42.43");
        Tenant tenant = mock(Tenant.class);

        AddressTransformer addressTransformer = new AddressTransformer();
        Set<Address> results = addressTransformer.toAddresses(addresses, tenant);

        assertThat(results).isNotEmpty();
        assertThat(results.size()).isEqualTo(3);
        for (Address address : results) {
            assertThat(address.getTenant()).isNotNull();
        }
    }
}
