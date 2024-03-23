package com.ian.davidson.port.scanner.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScanController {

    @PostMapping
    public ResponseEntity<ScanRequest> scan
}
