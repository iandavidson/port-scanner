package com.ian.davidson.port.scanner.model.business;

import java.util.Set;
import lombok.Builder;

@Builder
public record ScanComposition(Set<Address> ipAddresses, Set<Port> ports) {
}
