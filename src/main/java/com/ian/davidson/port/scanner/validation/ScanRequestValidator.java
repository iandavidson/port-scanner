package com.ian.davidson.port.scanner.validation;

import com.ian.davidson.port.scanner.model.request.ScanRequest;
import com.ian.davidson.port.scanner.validation.constraint.ScanRequestValidatorConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class ScanRequestValidator implements ConstraintValidator<ScanRequestValidatorConstraint, ScanRequest> {

    //    https://stackoverflow.com/questions/53829853/custom-message-for-constraintvalidator
    // Add custom message depending on failure
    @Override
    public void initialize(ScanRequestValidatorConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(ScanRequest value, ConstraintValidatorContext context) {
        if (Objects.isNull(value)) {
            return false;
        }

        if (Objects.isNull(value.getIps()) || Objects.isNull(value.getPorts())) {
            return false;
        }

        return !value.getIps().isEmpty() && !value.getPorts().isEmpty();
    }
}
