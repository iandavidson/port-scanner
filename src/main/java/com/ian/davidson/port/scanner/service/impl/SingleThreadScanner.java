package com.ian.davidson.port.scanner.service.impl;

import com.ian.davidson.port.scanner.config.ScannerConfig;
import com.ian.davidson.port.scanner.model.entity.ConnectionStatus;
import com.ian.davidson.port.scanner.model.entity.ScanResult;
import com.ian.davidson.port.scanner.model.queue.ScanItinerary;
import com.ian.davidson.port.scanner.repository.ScanResultRepository;
import com.ian.davidson.port.scanner.service.Scanner;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SingleThreadScanner implements Scanner {

    //https://stackoverflow.com/questions/10240694/java-socket-api-how-to-tell-if-a-connection-has-been-closed

    private final ScanResultRepository scanResultRepository;
    private final ScannerConfig scannerConfig;

    public SingleThreadScanner(final ScanResultRepository scanResultRepository, final ScannerConfig scannerConfig) {
        this.scanResultRepository = scanResultRepository;
        this.scannerConfig = scannerConfig;
    }

    @Override
    public void executeScan(final ScanItinerary scanItinerary) {
        List<ScanResult> scanResults = new ArrayList<>();
        for (String address : scanItinerary.addresses()) {
            for (Integer port : scanItinerary.ports()) {

                boolean success = true;
                try {
                    Socket socket = new Socket();
                    socket.connect(
                            new InetSocketAddress(address, port),
                            scannerConfig.getTimeout()
                    );

                    socket.close();
                } catch (IOException e) {
                    success = false;
                }

                scanResults.add(ScanResult.builder()
                        .address(address)
                        .port(port)
                        .sessionId(scanItinerary.sessionId())
                        .status(success ? ConnectionStatus.OPEN : ConnectionStatus.CLOSED)
                        .timeOut(scannerConfig.getTimeout())
                        .build());
            }
        }

        scanResultRepository.saveAll(scanResults);
    }
}
