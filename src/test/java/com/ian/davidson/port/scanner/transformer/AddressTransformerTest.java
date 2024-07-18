package com.ian.davidson.port.scanner.transformer;

import com.ian.davidson.port.scanner.model.entity.Address;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class AddressTransformerTest {

    @Test
    void toAddress_success() {
        String addr = "123.12.123.12";
        Long tenantId = 1L;

        AddressTransformer addressTransformer = new AddressTransformer();
        Address result = addressTransformer.toAddress(addr, tenantId);

        assertThat(result).isNotNull();
        assertThat(result.getAddress()).isEqualTo(addr);
        assertThat(result.getTenantId()).isEqualTo(tenantId);
    }

    @Test
    void toAddresses_success() {
        Set<String> addresses = Set.of("123.12.123.12", "99.99.99.99", "40.41.42.43");
        Long tenantId = 1L;

        AddressTransformer addressTransformer = new AddressTransformer();
        Set<Address> results = addressTransformer.toAddresses(addresses, tenantId);

        assertThat(results).isNotEmpty();
        assertThat(results.size()).isEqualTo(3);
        for (Address address : results) {
            assertThat(address.getTenantId()).isEqualTo(tenantId);
        }
    }
}
