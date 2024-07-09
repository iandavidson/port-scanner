package com.ian.davidson.port.scanner.controller;

import com.ian.davidson.port.scanner.model.request.ScanRequest;
import com.ian.davidson.port.scanner.model.response.ScanResponse;
import com.ian.davidson.port.scanner.service.ScanService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.net.URISyntaxException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/scan")
public class ScanController {

    private final ScanService scanService;
    private HttpServletRequest httpServletRequest;

    public ScanController(final ScanService scanService) {
        this.scanService = scanService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScanResponse> scan(@RequestBody @Valid ScanRequest scanRequest) throws URISyntaxException {
        //TODO: convert before sending
        ScanResponse scanResponse = scanService.initializeScan(scanRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(scanResponse);
    }

    @GetMapping(path = "/{tenantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScanResponse> scan(@PathVariable Long tenantId) {
        return ResponseEntity.ok(scanService.getScan(tenantId));
    }

}
