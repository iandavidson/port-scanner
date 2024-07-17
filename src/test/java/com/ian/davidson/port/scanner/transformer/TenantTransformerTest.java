package com.ian.davidson.port.scanner.transformer;

import com.ian.davidson.port.scanner.model.entity.Address;
import com.ian.davidson.port.scanner.model.entity.Port;
import com.ian.davidson.port.scanner.model.entity.Tenant;
import com.ian.davidson.port.scanner.model.request.TenantRequest;
import com.ian.davidson.port.scanner.model.response.TenantResponse;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TenantTransformerTest {

    @Test
    void toTenant_success() {
        TenantRequest tenantRequest = mock(TenantRequest.class);
        when(tenantRequest.name()).thenReturn("name");

        TenantTransformer tenantTransformer = new TenantTransformer();
        Tenant tenant = tenantTransformer.toTenant(tenantRequest);

        assertThat(tenant).isNotNull();
        assertThat(tenant.getName()).isEqualTo("name");
    }

    @Test
    void toTenantResponse_success() {
        Port port1 = mock(Port.class);
        when(port1.getPort()).thenReturn(1);
        Port port2 = mock(Port.class);
        when(port2.getPort()).thenReturn(2);
        Port port3 = mock(Port.class);
        when(port3.getPort()).thenReturn(3);
        Set<Port> ports = Set.of(port1, port2, port3);


        Address address1 = mock(Address.class);
        when(address1.getAddress()).thenReturn("1.1.1.1");
        Address address2 = mock(Address.class);
        when(address2.getAddress()).thenReturn("1.1.1.2");
        Address address3 = mock(Address.class);
        when(address3.getAddress()).thenReturn("1.1.1.3");
        Set<Address> addresses = Set.of(address1, address2, address3);

        Tenant tenant = mock(Tenant.class);
        when(tenant.getId()).thenReturn(1L);
        when(tenant.getName()).thenReturn("name");
        when(tenant.getPorts()).thenReturn(ports);
        when(tenant.getAddresses()).thenReturn(addresses);

        TenantTransformer tenantTransformer = new TenantTransformer();
        TenantResponse tenantResponse = tenantTransformer.toTenantResponse(tenant);

        assertThat(tenantResponse).isNotNull();
        assertThat(tenantResponse.id()).isEqualTo(1L);
        assertThat(tenantResponse.name()).isEqualTo("name");
        assertThat(tenantResponse.ports()).hasSize(3);
        assertThat(tenantResponse.addresses()).hasSize(3);
    }

    @Test
    void toTenantResponse_noPortsNoAddresses_success(){
        Tenant tenant = mock(Tenant.class);
        when(tenant.getId()).thenReturn(1L);
        when(tenant.getName()).thenReturn("name");
        when(tenant.getPorts()).thenReturn(null);
        when(tenant.getAddresses()).thenReturn(null);

        TenantTransformer tenantTransformer = new TenantTransformer();
        TenantResponse tenantResponse = tenantTransformer.toTenantResponse(tenant);

        assertThat(tenantResponse).isNotNull();
        assertThat(tenantResponse.id()).isEqualTo(1L);
        assertThat(tenantResponse.name()).isEqualTo("name");
        assertThat(tenantResponse.ports()).isEmpty();
        assertThat(tenantResponse.addresses()).isEmpty();
    }
}
