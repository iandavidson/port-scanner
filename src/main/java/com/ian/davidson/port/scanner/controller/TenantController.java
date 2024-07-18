package com.ian.davidson.port.scanner.controller;

import com.ian.davidson.port.scanner.model.entity.Tenant;
import com.ian.davidson.port.scanner.model.request.TenantRequest;
import com.ian.davidson.port.scanner.model.request.TenantSurfaceRequest;
import com.ian.davidson.port.scanner.model.response.TenantResponse;
import com.ian.davidson.port.scanner.model.response.TenantSurfaceResponse;
import com.ian.davidson.port.scanner.service.TenantService;
import com.ian.davidson.port.scanner.transformer.AddressTransformer;
import com.ian.davidson.port.scanner.transformer.PortTransformer;
import com.ian.davidson.port.scanner.transformer.TenantTransformer;
import java.util.List;
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
    private final PortTransformer portTransformer;
    private final AddressTransformer addressTransformer;
    private final TenantService tenantService;

    public TenantController(final TenantTransformer tenantTransformer, final PortTransformer portTransformer,
                            final AddressTransformer addressTransformer, final TenantService tenantService) {
        this.tenantTransformer = tenantTransformer;
        this.portTransformer = portTransformer;
        this.addressTransformer = addressTransformer;
        this.tenantService = tenantService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TenantResponse> createTenant(@RequestBody TenantRequest tenantRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tenantTransformer.toTenantResponse(tenantService.createTenant(tenantTransformer.toTenant(tenantRequest))));
    }

    @GetMapping(path = "/{tenantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TenantResponse> getTenant(@PathVariable Long tenantId) {
        return ResponseEntity.status(HttpStatus.OK).body(tenantTransformer.toTenantResponse(tenantService.getTenant(tenantId)));
    }

    @DeleteMapping(path = "/{tenantId}")
    public ResponseEntity<Void> deleteTenant(@PathVariable Long tenantId) {

        tenantService.deleteTenant(tenantId);

        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/{tenantId}/surface")
    public ResponseEntity<TenantResponse> addSurfaceAtTenant(@PathVariable Long tenantId,
                                                                    @RequestBody TenantSurfaceRequest tenantSurfaceRequest) {
        Tenant tenant = tenantService.getTenant(tenantId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tenantTransformer.toTenantResponse(

                        tenantService.addSurface(
                            portTransformer.toPorts(tenantSurfaceRequest.ports(), tenant),
                            addressTransformer.toAddresses(tenantSurfaceRequest.addresses(), tenant),
                            tenant
                        )
                ));
    }

    @GetMapping
    public ResponseEntity<List<TenantResponse>> getAllTenants() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tenantService.getAllTenants().stream()
                        .map(tenantTransformer::toTenantResponse)
                        .toList()
                );
    }

}
