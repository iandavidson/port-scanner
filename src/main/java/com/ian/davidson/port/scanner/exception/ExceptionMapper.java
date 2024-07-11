package com.ian.davidson.port.scanner.exception;

import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
@Slf4j
public class ExceptionMapper extends ResponseEntityExceptionHandler {

    private static final String INTERNAL_SERVER_ERROR_MESSAGE = "Internal Server Error";

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(final ResourceNotFoundException resourceNotFoundException) {
        log(resourceNotFoundException);

        return createResponseEntity(
                ApiError.builder()
                        .status(HttpStatus.NOT_FOUND)
                        .message(resourceNotFoundException.getMessage())
                        .build());
    }

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<?> handleResourceConflict(final ResourceConflictException resourceConflictException) {
        log(resourceConflictException);

        return createResponseEntity(
                ApiError.builder()
                        .status(HttpStatus.CONFLICT)
                        .message(resourceConflictException.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException(final ValidationException validationException) {
        log(validationException);

        return createResponseEntity(
                ApiError.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .message(validationException.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(final BadRequestException badRequestException) {
        log(badRequestException);

        return createResponseEntity(
                ApiError.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .message(badRequestException.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> handleIllegalStateException(final IllegalStateException illegalStateException) {
        log(illegalStateException);

        return createResponseEntity(
                ApiError.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .message(illegalStateException.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleGenericRuntimeException(final RuntimeException runtimeException) {
        log(runtimeException);

        return createResponseEntity(
                ApiError.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .message(INTERNAL_SERVER_ERROR_MESSAGE)
                        .build());
    }


    private void log(final Exception exception) {
        final var logStatement = "exception-mapper cause={}, exception={}";
        if (exception instanceof IllegalArgumentException
                || exception instanceof ResourceNotFoundException) {
            log.warn(logStatement, exception.getMessage(), exception);
        } else if (exception instanceof RuntimeException) {
            log.error(logStatement, exception.getMessage(), exception);
        } else {
            log.debug(logStatement, exception.getMessage(), exception);
        }
    }

    private ResponseEntity<?> createResponseEntity(ApiError error) {
        return new ResponseEntity<>(error, error.getStatus());
    }
}
