package com.ian.davidson.port.scanner.model.business;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Address {

    @Pattern(
            regexp = "^((25[0-5]|(2[0-4]|1[0-9]|[1-9]|)[0-9])(\\.(?!$)|$)){4}$",
            message = "Address does not conform to valid IP structure")
    private final String value;
    private Long tenantId;
}
