package com.ian.davidson.port.scanner.transformer;

import com.ian.davidson.port.scanner.model.entity.Tenant;
import com.ian.davidson.port.scanner.model.request.TenantRequest;
import com.ian.davidson.port.scanner.model.response.TenantResponse;
import org.springframework.stereotype.Component;

@Component
public class TenantTransformer {

    public Tenant toTenant(final TenantRequest tenantRequest){
        return Tenant.builder().name(tenantRequest.name()).build();
    }

    public TenantResponse toTenantResponse(final Tenant tenant){
        return new TenantResponse(tenant.getId(), tenant.getName());
    }
}
