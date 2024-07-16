package com.ian.davidson.port.scanner.service.impl;

import com.ian.davidson.port.scanner.model.entity.Address;
import com.ian.davidson.port.scanner.repository.AddressRepository;
import com.ian.davidson.port.scanner.service.AddressService;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(final AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public void addAddresses(Set<Address> addresses) {
        addressRepository.saveAll(addresses);
    }

    @Override
    public void deleteAddressesByTenantId(Long tenantId) {
        addressRepository.deleteAllByTenantId(tenantId);
    }
}
