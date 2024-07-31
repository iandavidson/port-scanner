package com.ian.davidson.port.scanner.service;

import com.ian.davidson.port.scanner.exception.ResourceConflictException;
import com.ian.davidson.port.scanner.exception.ResourceNotFoundException;
import com.ian.davidson.port.scanner.model.entity.Address;
import com.ian.davidson.port.scanner.model.entity.Port;
import com.ian.davidson.port.scanner.model.entity.Tenant;
import com.ian.davidson.port.scanner.repository.TenantRepository;
import com.ian.davidson.port.scanner.service.impl.TenantServiceImpl;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TenantServiceTest {

    private static final String NAME = "name";
    private static final Long ID = 1L;

    @Test
    void createTenant_success() {
        TenantRepository tenantRepository = mock(TenantRepository.class);
        when(tenantRepository.findByName(NAME)).thenReturn(Optional.empty());

        Tenant tenant = mock(Tenant.class);
        when(tenant.getName()).thenReturn(NAME);

        TenantService tenantService = new TenantServiceImpl(tenantRepository, null, null, null);
        Tenant newTenant = tenantService.createTenant(tenant);

        assertThat(newTenant).isNotNull();
        verify(tenant).getName();
        verify(tenantRepository).findByName(NAME);
        verify(tenantRepository).save(tenant);
    }

    @Test
    void createTenant_tenantAlreadyExists() {
        TenantRepository tenantRepository = mock(TenantRepository.class);
        when(tenantRepository.findByName(NAME)).thenReturn(Optional.of(mock(Tenant.class)));

        Tenant tenant = mock(Tenant.class);
        when(tenant.getName()).thenReturn(NAME);

        TenantService tenantService = new TenantServiceImpl(tenantRepository, null, null, null);
        assertThatThrownBy(() -> tenantService.createTenant(tenant)).hasMessage("Found tenant with same name " +
                "provided: " + NAME).isInstanceOf(ResourceConflictException.class);

        verify(tenant, times(2)).getName();
        verify(tenantRepository).findByName(NAME);
    }

    @Test
    void getTenant_success() {
        Tenant tenant = mock(Tenant.class);

        TenantRepository tenantRepository = mock(TenantRepository.class);
        when(tenantRepository.findById(ID)).thenReturn(Optional.of(tenant));

        TenantService tenantService = new TenantServiceImpl(tenantRepository, null, null, null);
        Tenant result = tenantService.getTenant(ID);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(tenant);
        verify(tenantRepository).findById(ID);
    }

    @Test
    void getTenant_notFound() {
        TenantRepository tenantRepository = mock(TenantRepository.class);
        when(tenantRepository.findById(ID)).thenReturn(Optional.empty());

        TenantService tenantService = new TenantServiceImpl(tenantRepository, null, null, null);
        assertThatThrownBy(() -> tenantService.getTenant(ID)).hasMessage("No tenant found at id: " + ID)
                .isInstanceOf(ResourceNotFoundException.class);

        verify(tenantRepository).findById(ID);
    }

    @Test
    void getAllTenants() {
        TenantRepository tenantRepository = mock(TenantRepository.class);
        when(tenantRepository.findAll()).thenReturn(
                List.of(mock(Tenant.class))
        );

        List<Tenant> result = new TenantServiceImpl(tenantRepository, null, null, null).getAllTenants();

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);
        verify(tenantRepository).findAll();
    }

    @Test
    void deleteTenant() {
        SessionService sessionService = mock(SessionService.class);
        doNothing().when(sessionService).deleteSessionsByTenantId(ID);

        PortService portService = mock(PortService.class);
        doNothing().when(portService).deletePortsByTenantId(ID);

        AddressService addressService = mock(AddressService.class);
        doNothing().when(addressService).deleteAddressesByTenantId(ID);

        TenantRepository tenantRepository = mock(TenantRepository.class);
        doNothing().when(tenantRepository).deleteById(ID);

        TenantService tenantService = new TenantServiceImpl(tenantRepository, addressService, portService,
                sessionService);
        tenantService.deleteTenant(ID);

        verify(sessionService).deleteSessionsByTenantId(ID);
        verify(portService).deletePortsByTenantId(ID);
        verify(addressService).deleteAddressesByTenantId(ID);
        verify(tenantRepository).deleteById(ID);
    }

    @Test
    void addSurface() {
        Tenant tenant = mock(Tenant.class);
        Set<Port> ports = Set.of(mock(Port.class));
        Set<Address> addresses = Set.of(mock(Address.class));

        TenantRepository tenantRepository = mock(TenantRepository.class);
        when(tenantRepository.findById(ID)).thenReturn(Optional.of(tenant));

        PortService portService = mock(PortService.class);
        doNothing().when(portService).updatePortsByTenantId(ports, ID);

        AddressService addressService = mock(AddressService.class);
        doNothing().when(addressService).updateAddressesByTenantId(addresses, ID);

        TenantService tenantService = new TenantServiceImpl(tenantRepository, addressService, portService, null);
        tenantService.addSurface(ports, addresses, ID);

        verify(tenantRepository).findById(ID);
        verify(portService).updatePortsByTenantId(ports, ID);
        verify(addressService).updateAddressesByTenantId(addresses, ID);
    }

    @Test
    void updateSurface() {
        Tenant tenant = mock(Tenant.class);
        Set<Port> ports = Set.of(mock(Port.class));
        Set<Address> addresses = Set.of(mock(Address.class));

        TenantRepository tenantRepository = mock(TenantRepository.class);
        when(tenantRepository.findById(ID)).thenReturn(Optional.of(tenant));

        PortService portService = mock(PortService.class);
        doNothing().when(portService).updatePortsByTenantId(ports, ID);

        AddressService addressService = mock(AddressService.class);
        doNothing().when(addressService).updateAddressesByTenantId(addresses, ID);

        TenantService tenantService = new TenantServiceImpl(tenantRepository, addressService, portService, null);
        tenantService.updateSurface(ports, addresses, ID);

        verify(tenantRepository).findById(ID);
        verify(portService).updatePortsByTenantId(ports, ID);
        verify(addressService).updateAddressesByTenantId(addresses, ID);
    }
}
