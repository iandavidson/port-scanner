package com.ian.davidson.port.scanner.service;

import com.ian.davidson.port.scanner.model.queue.ScanItinerary;

public interface Scanner {
    void executeScan(final ScanItinerary scanItinerary);
}
