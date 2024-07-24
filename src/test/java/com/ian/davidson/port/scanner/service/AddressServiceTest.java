package com.ian.davidson.port.scanner.service;

import com.ian.davidson.port.scanner.model.entity.Address;
import com.ian.davidson.port.scanner.repository.AddressRepository;
import com.ian.davidson.port.scanner.service.impl.AddressServiceImpl;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class AddressServiceTest {

    private static final Long TENANT_ID = 1L;

    private static final Set<Address> ADDRESS_SET = Set.of(
            Address.builder().tenantId(TENANT_ID).address("208.67.222.222").id(1L).build(),
            Address.builder().tenantId(TENANT_ID).address("208.67.222.223").id(2L).build(),
            Address.builder().tenantId(TENANT_ID).address("208.67.222.224").id(3L).build()
    );

    private static final Set<Address> EXISTING_ADDRESS_SET = Set.of(
            Address.builder().tenantId(TENANT_ID).address("208.67.222.224").id(3L).build(),
            Address.builder().tenantId(TENANT_ID).address("208.67.222.225").id(4L).build()
    );

    @Test
    void addAddresses() {
        AddressRepository addressRepository = mock(AddressRepository.class);

        AddressService addressService = new AddressServiceImpl(addressRepository);
        addressService.addAddresses(ADDRESS_SET);

        verify(addressRepository).saveAll(ADDRESS_SET);
    }

    @Test
    void deleteAddressesByTenantId() {
        AddressRepository addressRepository = mock(AddressRepository.class);
        doNothing().when(addressRepository).deleteAllByTenantId(TENANT_ID);

        AddressService addressService = new AddressServiceImpl(addressRepository);
        addressService.deleteAddressesByTenantId(TENANT_ID);

        verify(addressRepository).deleteAllByTenantId(TENANT_ID);
    }

    @Test
    void updateAddressesByTenantId() {
        AddressRepository addressRepository = mock(AddressRepository.class);
        when(addressRepository.findAllByTenantId(TENANT_ID)).thenReturn(EXISTING_ADDRESS_SET);

        AddressService addressService = new AddressServiceImpl(addressRepository);
        addressService.updateAddressesByTenantId(ADDRESS_SET, TENANT_ID);

        verify(addressRepository).findAllByTenantId(TENANT_ID);
        verify(addressRepository).saveAll(anySet());
        verify(addressRepository).deleteAll(anySet());
    }
}
