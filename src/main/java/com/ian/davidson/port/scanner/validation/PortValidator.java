package com.ian.davidson.port.scanner.validation;

import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class PortValidator extends AbstractValidator<Integer> {

    private static final Integer LOW = 0;
    private static final Integer HIGH = 65535;


    @Override
    public Integer isValid(final Integer port) {

        if (port >= LOW && port <= HIGH) {
            return port;
        } else {
            throw new ValidationException("Invalid port value provided: " + port);
        }
    }
}
