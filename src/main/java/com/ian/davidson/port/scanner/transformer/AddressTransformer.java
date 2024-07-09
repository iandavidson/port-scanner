package com.ian.davidson.port.scanner.transformer;

import com.ian.davidson.port.scanner.model.entity.Address;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class AddressTransformer {

    public Set<Address> toAddresses(Set<String> addresses) {
        return addresses.stream().map(this::toAddress).collect(Collectors.toSet());
    }

    public Address toAddress(String address) {
        return Address.builder().address(address).build();
    }
}
