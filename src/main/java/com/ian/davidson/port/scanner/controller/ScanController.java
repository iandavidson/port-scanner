package com.ian.davidson.port.scanner.controller;

import com.ian.davidson.port.scanner.model.ScanRequest;
import com.ian.davidson.port.scanner.model.ScanResponse;
import com.ian.davidson.port.scanner.service.ScanManagerService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ScanController {

    private final ScanManagerService scanManagerService;

    public ScanController(final ScanManagerService scanManagerService) {
        this.scanManagerService = scanManagerService;
    }

    @PostMapping(path = "/scan", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScanResponse> scan(@RequestBody @Valid ScanRequest scanRequest) {
        return null;
    }

}
