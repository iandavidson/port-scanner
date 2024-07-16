package com.ian.davidson.port.scanner.service;

import com.ian.davidson.port.scanner.model.entity.Address;
import java.util.Set;

public interface AddressService {
    void addAddresses(Set<Address> addresses);
    void deleteAddressesByTenantId(Long tenantId);
}
