package com.ian.davidson.port.scanner.controller;

import com.ian.davidson.port.scanner.model.ScanRequest;
import com.ian.davidson.port.scanner.model.ScanResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ScanController {


    @PostMapping(path = "/scan", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScanResponse> scan(@RequestBody ScanRequest scanRequest){
        return null;
    }

}
