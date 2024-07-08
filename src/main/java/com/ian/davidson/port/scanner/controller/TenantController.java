package com.ian.davidson.port.scanner.controller;

import com.ian.davidson.port.scanner.model.entity.Tenant;
import com.ian.davidson.port.scanner.model.request.TenantRequest;
import com.ian.davidson.port.scanner.model.response.TenantResponse;
import com.ian.davidson.port.scanner.service.TenantService;
import com.ian.davidson.port.scanner.transformer.TenantTransformer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tenant")
public class TenantController {

    private final TenantTransformer tenantTransformer;
    private final TenantService tenantService;

    public TenantController(final TenantTransformer tenantTransformer, final TenantService tenantService) {
        this.tenantTransformer = tenantTransformer;
        this.tenantService = tenantService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TenantResponse> createTenant(@RequestBody TenantRequest tenantRequest) {
        Tenant tenant = tenantService.createTenant(tenantTransformer.toTenant(tenantRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(tenantTransformer.toTenantResponse(tenant));
    }

    @GetMapping(path = "/{tenantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TenantResponse> getTenant(@PathVariable Long tenantId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                    tenantTransformer.toTenantResponse(
                            tenantService.getTenant(tenantId)
                )
        );
    }

    @DeleteMapping(path = "/{tenantId}")
    public ResponseEntity<Void> deleteTenant(@PathVariable Long tenantId){
        tenantService.deleteTenant(tenantId);

        return ResponseEntity.noContent().build();
    }
}
