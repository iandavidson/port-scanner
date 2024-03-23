package com.ian.davidson.port.scanner.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScanRequest {

    private List<Integer> ports = new ArrayList<>();
    private Integer portRangeStart;
    private Integer portRangeEnd;

    private List<Integer> ips = new ArrayList<>();
    private Integer ipRangeStart;
    private Integer ipRangeEnd;
}
