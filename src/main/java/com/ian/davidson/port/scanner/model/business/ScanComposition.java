package com.ian.davidson.port.scanner.model.business;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
@Builder
public record ScanComposition(Set<Address> ipAddresses, Set<Port> ports) { }
