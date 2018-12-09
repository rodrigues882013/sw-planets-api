package com.application.swplanetsapi.infrastructure.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IntegrationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String reason;
    private HttpStatus code;

}
