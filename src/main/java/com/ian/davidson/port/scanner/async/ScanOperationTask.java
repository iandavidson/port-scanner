package com.ian.davidson.port.scanner.async;

import com.ian.davidson.port.scanner.model.entity.ConnectionStatus;
import com.ian.davidson.port.scanner.model.entity.ScanResult;
import com.ian.davidson.port.scanner.model.scan.ScanOperation;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.Callable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Builder
@Slf4j
public record ScanOperationTask(ScanOperation scanOperation, Integer timeout) implements Callable<ScanResult> {

    @Override
    public ScanResult call() throws Exception {
        log.debug("Scanning: {}:{} for session:{}", scanOperation.address(), scanOperation.port(),
                scanOperation.sessionId());
        boolean success = true;
        try {
            Socket socket = new Socket();
            socket.connect(
                    new InetSocketAddress(scanOperation.address(), scanOperation.port()),
                    timeout
            );

            socket.close();
        } catch (IOException e) {
            success = false;
        }

        return ScanResult.builder()
                .address(scanOperation.address())
                .port(scanOperation.port())
                .sessionId(scanOperation.sessionId())
                .status(success ? ConnectionStatus.OPEN : ConnectionStatus.CLOSED)
                .timeOut(timeout)
                .build();

    }
}
