package com.ian.davidson.port.scanner.model.business;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public record ScanComposition(List<String> ipAddresses, List<Integer> ports) { }
