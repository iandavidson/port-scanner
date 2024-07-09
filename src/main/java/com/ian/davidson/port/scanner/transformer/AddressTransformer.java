package com.ian.davidson.port.scanner.transformer;

import com.ian.davidson.port.scanner.model.entity.Address;
import com.ian.davidson.port.scanner.model.entity.Tenant;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class AddressTransformer {

    public Set<Address> toAddresses(final Set<String> addresses, final Tenant tenant) {
        return addresses.stream().map(address -> toAddress(address, tenant)).collect(Collectors.toSet());
    }

    public Address toAddress(final String address, final Tenant tenant) {
        return Address.builder().address(address).tenant(tenant).build();
    }
}
