package com.ian.davidson.port.scanner.async;

import com.ian.davidson.port.scanner.model.entity.ScanResult;
import java.util.List;

public interface ScanOperationExecutor {
    List<ScanResult> executeScan(List<ScanOperationTask> scanOperationTasks);
}
