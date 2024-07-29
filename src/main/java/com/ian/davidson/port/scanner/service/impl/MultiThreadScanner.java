package com.ian.davidson.port.scanner.service.impl;

import com.ian.davidson.port.scanner.model.queue.ScanItinerary;
import com.ian.davidson.port.scanner.service.Scanner;
import org.springframework.stereotype.Service;

@Service
public class MultiThreadScanner implements Scanner {

    @Override
    public void executeScan(ScanItinerary scanItinerary) {

    }
}
