package com.ian.davidson.port.scanner.validation.constraint;

import com.ian.davidson.port.scanner.validation.ScanRequestValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint( validatedBy = ScanRequestValidator.class)
@Target({TYPE})
@Retention(RUNTIME)
@Documented
public @interface ScanRequestValidatorConstraint {

    String message() default "Invalid configuration of parameters";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
