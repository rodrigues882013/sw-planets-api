package com.application.swplanetsapi.web.dto.internal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private HttpStatus httpStatus;
    private String message;
    private Integer httpCode;

    public ErrorResponse(HttpStatus code, String message) {
        this.message = message;
        this.httpStatus = code;
        this.httpCode = code.value();

    }
}
