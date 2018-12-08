package com.application.swplanetsapi.application.dto.internal;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private HttpStatus httpStatus;
    private String message;
    private Integer httpCode;

    public ErrorResponse() {
    }

    public ErrorResponse(HttpStatus code, String message) {
        this.message = message;
        this.httpStatus = code;
        this.httpCode = code.value();

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.httpCode = httpStatus.value();
    }

    public Integer getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }
}
