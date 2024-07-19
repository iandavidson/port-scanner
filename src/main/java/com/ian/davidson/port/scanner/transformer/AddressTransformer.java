package com.ian.davidson.port.scanner.transformer;

import com.ian.davidson.port.scanner.model.entity.Address;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class AddressTransformer {

    public Set<Address> toAddresses(final Set<String> addresses, final Long tenantId) {
        return addresses.stream().map(address -> toAddress(address, tenantId)).collect(Collectors.toSet());
    }

    public Address toAddress(final String address, final Long tenantId) {
        return Address.builder().address(address).tenantId(tenantId).build();
    }
}
