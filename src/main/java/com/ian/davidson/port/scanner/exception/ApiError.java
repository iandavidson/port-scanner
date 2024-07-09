package com.ian.davidson.port.scanner.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {
    private HttpStatus status;

    @Builder.Default
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss z", timezone = "UTC")
    private LocalDateTime timestamp = LocalDateTime.now(ZoneOffset.UTC);

    private String message;
    private List<String> errors;
    private String debugMessage;
    private Integer errorCode;
    private Throwable exception;

    public static class ApiErrorBuilder {
        public ApiErrorBuilder exception(final Throwable exception) {
            this.debugMessage = exception.getLocalizedMessage();

            return this;
        }

        private ApiErrorBuilder debugMessage(final String debugMessage) {
            return this;
        }
    }
}
