package com.ian.davidson.port.scanner.controller;

import com.ian.davidson.port.scanner.model.request.ScanRequest;
import com.ian.davidson.port.scanner.model.response.ScanResponse;
import com.ian.davidson.port.scanner.service.ScanManagerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;


@RestController
public class ScanController {

    private final ScanManagerService scanManagerService;
    private HttpServletRequest httpServletRequest;

    public ScanController(final ScanManagerService scanManagerService, HttpServletRequest httpServletRequest) {
        this.scanManagerService = scanManagerService;
        this.httpServletRequest = httpServletRequest;
    }

    @PostMapping(path = "/scan", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScanResponse> scan(@RequestBody @Valid ScanRequest scanRequest) throws URISyntaxException {
        ScanResponse scanResponse = scanManagerService.initializeScan(scanRequest);

        //todo: fix this rather than hard coding
        return ResponseEntity.created(new URI("http://localhost:8080/scan/"+ scanResponse.getId())).body(scanResponse);
    }

    @GetMapping(path = "/scan/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScanResponse> scan(@PathVariable Long id){
        return ResponseEntity.ok(scanManagerService.getScan(id));
    }

}
