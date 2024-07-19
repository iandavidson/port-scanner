package com.ian.davidson.port.scanner.controller;

import com.ian.davidson.port.scanner.model.entity.Session;
import com.ian.davidson.port.scanner.model.entity.Tenant;
import com.ian.davidson.port.scanner.model.response.ScanOverviewResponse;
import com.ian.davidson.port.scanner.model.response.SessionResponse;
import com.ian.davidson.port.scanner.service.ScanService;
import com.ian.davidson.port.scanner.service.SessionService;
import com.ian.davidson.port.scanner.service.TenantService;
import com.ian.davidson.port.scanner.transformer.ScanResultTransformer;
import com.ian.davidson.port.scanner.transformer.SessionTransformer;
import java.net.URISyntaxException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class ScanController {

    private final ScanService scanService;
    private final SessionService sessionService;
    private final TenantService tenantService;
    private final ScanResultTransformer scanResultTransformer;
    private final SessionTransformer sessionTransformer;

    public ScanController(final ScanService scanService, final SessionService sessionService,
                          final TenantService tenantService, final ScanResultTransformer scanResultTransformer,
                          final SessionTransformer sessionTransformer) {
        this.scanService = scanService;
        this.sessionService = sessionService;
        this.tenantService = tenantService;
        this.scanResultTransformer = scanResultTransformer;
        this.sessionTransformer = sessionTransformer;
    }


    @PostMapping(path = "/{tenantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SessionResponse> scan(@PathVariable("tenantId") final Long tenantId) throws URISyntaxException {
        //validate tenantId is valid
        Tenant tenant = tenantService.getTenant(tenantId);
        //create session, kick off scan,
        Session session = sessionService.addSession(Session.builder().tenantId(tenantId).build());

        scanService.initializeScan(tenant, session.getId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(sessionTransformer.toSessionResponse(session));
    }

    //return overview object for all
    @GetMapping(path = "/{tenantId}/{sessionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SessionResponse> scanResult(@PathVariable("tenantId") final Long tenantId,
                                                      @PathVariable("sessionId") final Long sessionId) {

    }

    //look up session via repository
    @GetMapping(path = "/{tenantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScanOverviewResponse> scanOverviewsByTenantId(@PathVariable("tenantId") final Long tenantId) {

    }


}
