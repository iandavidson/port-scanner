package com.ian.davidson.port.scanner.service.impl;

import com.ian.davidson.port.scanner.model.entity.Address;
import com.ian.davidson.port.scanner.repository.AddressRepository;
import com.ian.davidson.port.scanner.service.AddressService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    public AddressServiceImpl(final AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public void addAddresses(final Set<Address> addresses) {
        addressRepository.saveAll(addresses);
    }

    @Override
    public void deleteAddressesByTenantId(final Long tenantId) {
        addressRepository.deleteAllByTenantId(tenantId);
    }

    @Override
    public void updateAddressesByTenantId(final Set<Address> newAddresses, final Long tenantId) {
        Set<Address> existingAddresses = getAddressesByTenantId(tenantId);
        Set<Address> toBeRemoved = new HashSet<>();
        Set<Address> toBeAdded = new HashSet<>();

        for (Address newAddress : newAddresses) {
            if (!existingAddresses.contains(newAddress)) {
                toBeAdded.add(newAddress);
            }
        }

        for (Address existingAddress : existingAddresses) {
            if (!newAddresses.contains(existingAddress)) {
                toBeRemoved.add(existingAddress);
            }
        }

        addAddresses(toBeAdded);
        deleteAddresses(toBeRemoved);
    }

    private Set<Address> getAddressesByTenantId(final Long tenantId) {
        return addressRepository.findAllByTenantId(tenantId);
    }

    private void deleteAddresses(final Set<Address> addresses){
        addressRepository.deleteAll(addresses);
    }
}
