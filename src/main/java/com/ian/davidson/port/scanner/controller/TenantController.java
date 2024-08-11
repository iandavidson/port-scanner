package com.ian.davidson.port.scanner.controller;

import com.ian.davidson.port.scanner.model.entity.Session;
import com.ian.davidson.port.scanner.model.entity.Tenant;
import com.ian.davidson.port.scanner.model.request.TenantRequest;
import com.ian.davidson.port.scanner.model.request.TenantSurfaceRequest;
import com.ian.davidson.port.scanner.model.response.ScanOverviewResponse;
import com.ian.davidson.port.scanner.model.response.SessionResponse;
import com.ian.davidson.port.scanner.model.response.TenantResponse;
import com.ian.davidson.port.scanner.service.ScanService;
import com.ian.davidson.port.scanner.service.SessionService;
import com.ian.davidson.port.scanner.service.TenantService;
import com.ian.davidson.port.scanner.transformer.AddressTransformer;
import com.ian.davidson.port.scanner.transformer.PortTransformer;
import com.ian.davidson.port.scanner.transformer.ScanResultTransformer;
import com.ian.davidson.port.scanner.transformer.SessionTransformer;
import com.ian.davidson.port.scanner.transformer.TenantTransformer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tenants")
public class TenantController {

    private final AddressTransformer addressTransformer;
    private final PortTransformer portTransformer;
    private final ScanService scanService;
    private final ScanResultTransformer scanResultTransformer;
    private final SessionService sessionService;
    private final SessionTransformer sessionTransformer;
    private final TenantService tenantService;
    private final TenantTransformer tenantTransformer;

    public TenantController(final AddressTransformer addressTransformer,
                            final PortTransformer portTransformer,
                            final ScanService scanService,
                            final ScanResultTransformer scanResultTransformer,
                            final SessionService sessionService,
                            final SessionTransformer sessionTransformer,
                            final TenantService tenantService,
                            final TenantTransformer tenantTransformer) {
        this.addressTransformer = addressTransformer;
        this.portTransformer = portTransformer;
        this.scanService = scanService;
        this.scanResultTransformer = scanResultTransformer;
        this.sessionService = sessionService;
        this.sessionTransformer = sessionTransformer;
        this.tenantService = tenantService;
        this.tenantTransformer = tenantTransformer;
    }


    @Operation(summary = "Get all tenants", description = "Fetch all tenant resources")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched all tenants")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TenantResponse>> getAllTenants() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tenantService.getAllTenants().stream()
                        .map(tenantTransformer::toTenantResponse)
                        .sorted()
                        .toList()
                );
    }

    @Operation(summary = "Get tenant by id", description = "Fetch tenant resource by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched")
    })
    @GetMapping(path = "/{tenantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TenantResponse> getTenant(@PathVariable Long tenantId) {
        return ResponseEntity.status(HttpStatus.OK).body(tenantTransformer.toTenantResponse(tenantService.getTenant(tenantId)));
    }

    @Operation(summary = "Create new tenant", description = "Create new tenant resource")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created")
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TenantResponse> createTenant(@RequestBody TenantRequest tenantRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tenantTransformer.toTenantResponse(
                        tenantService.createTenant(
                                tenantTransformer.toTenant(
                                        tenantRequest
                                )
                        )));
    }

    @Operation(summary = "Delete tenant by id", description = "Delete tenant resource by id; This will also remove " +
            "configured surface area and associated scan sessions + results")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted")
    })
    @DeleteMapping(path = "/{tenantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteTenant(@PathVariable Long tenantId) {

        tenantService.deleteTenant(tenantId);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Add surface for tenant by tenant id", description = "Create address and port surface area " +
            "by tenant id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created")
    })
    @PostMapping(path = "/{tenantId}/surface", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TenantResponse> addSurfaceAtTenantId(@PathVariable final Long tenantId,
                                                             @RequestBody final TenantSurfaceRequest tenantSurfaceRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tenantTransformer.toTenantResponse(
                        tenantService.addSurface(
                                portTransformer.toPorts(tenantSurfaceRequest.ports(), tenantId),
                                addressTransformer.toAddresses(tenantSurfaceRequest.addresses(), tenantId),
                                tenantId
                        )
                ));
    }

    @Operation(summary = "Update surface for tenant by tenant id", description = "Update configured address and port " +
            "surface area by tenant id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated")
    })
    @PutMapping(path = "/{tenantId}/surface", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TenantResponse> updateSurfaceAtTenantId(@PathVariable final Long tenantId,
                                                                @RequestBody final TenantSurfaceRequest tenantSurfaceRequest) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tenantTransformer.toTenantResponse(
                        tenantService.updateSurface(
                                portTransformer.toPorts(tenantSurfaceRequest.ports(), tenantId),
                                addressTransformer.toAddresses(tenantSurfaceRequest.addresses(), tenantId),
                                tenantId
                        )
                ));
    }

    @Operation(summary = "Delete entire surface by tenant id", description = "Delete configured addresses and ports for provided tenant id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated")
    })
    @DeleteMapping(path = "/{tenantId}/surface")
    public ResponseEntity<Void> deleteSurfaceAtTenantId(@PathVariable("tenantId") final Long tenantId){
        tenantService.deleteSurface(tenantId);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get scan overview at tenant id", description = "Fetch overview object describing scan " +
            "results at provided tenant id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched scan overview collection at " +
                    "provided tenant id")
    })
    @GetMapping(path = "/{tenantId}/overview", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScanOverviewResponse> scanOverviewsByTenantId(@PathVariable("tenantId") final Long tenantId) {

        //validate tenantId is valid
        Tenant tenant = tenantService.getTenant(tenantId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(scanResultTransformer.toScanOverviewResponse(tenant));
    }

    @Operation(summary = "Start new scan session at tenant id", description = "Kick off new scan session at tenant " +
            "id, given configured surface at tenant id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully started scan session at provided tenant id")
    })
    @PostMapping(path = "/{tenantId}/sessions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SessionResponse> startScanSession(@PathVariable("tenantId") final Long tenantId) {
        //validate tenantId is valid
        Tenant tenant = tenantService.getTenant(tenantId);
        //create session, kick off scan,
        Session session = sessionService.addSession(Session.builder().tenantId(tenantId).build());

        scanService.initializeScan(tenant, session.getId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(sessionTransformer.toSessionResponse(session));
    }

    @Operation(summary = "Get scan session result at tenant id and session id", description = "Fetch scan session " +
            "result at tenant id and session id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched scan session result")
    })
    @GetMapping(path = "/{tenantId}/sessions/{sessionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SessionResponse> scanSessionResults(@PathVariable("tenantId") final Long tenantId,
                                                      @PathVariable("sessionId") final Long sessionId) {
        //validate tenantId is valid
        Tenant tenant = tenantService.getTenant(tenantId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(sessionTransformer.toSessionResponse(
                        sessionService.getSession(sessionId)));
    }

    @Operation(summary = "Delete scan session at tenant id and session id", description = "Delete scan session and " +
            "results at tenant id and session id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted scan session and results " +
                    "resources at provided session id")
    })
    @DeleteMapping(path = "/{tenantId}/sessions/{sessionId}")
    public ResponseEntity<Void> deleteScanSession(@PathVariable("tenantId") final Long tenantId,
                                              @PathVariable("sessionId") final Long sessionId) {
        //validate tenantId is valid
        Tenant tenant = tenantService.getTenant(tenantId);

        sessionService.deleteSession(sessionId);

        return ResponseEntity.noContent().build();
    }
}
