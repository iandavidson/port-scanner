package com.ian.davidson.port.scanner.service;

import com.ian.davidson.port.scanner.model.entity.Address;
import java.util.Set;

public interface AddressService {
    void deleteAddressesByTenantId(Long tenantId);
    void updateAddressesByTenantId(Set<Address> newAddresses, Long tenantId);
}
