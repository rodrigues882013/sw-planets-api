package com.application.swplanetsapi.web.controller;

import com.application.swplanetsapi.web.dto.internal.ErrorResponse;
import com.application.swplanetsapi.infrastructure.exception.IntegrationException;
import com.application.swplanetsapi.infrastructure.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(value = ServiceException.class)
    public ResponseEntity<?> handleServicexception(ServiceException serviceException, HttpServletRequest request) {

        ErrorResponse response = new ErrorResponse(serviceException.getCode(), serviceException.getLocalizedMessage());
        log.error("Exception: {}", serviceException.getLocalizedMessage());

        return new ResponseEntity<>(response, serviceException.getCode());
    }

    @ExceptionHandler(value = IntegrationException.class)
    public ResponseEntity<?> handleIntegrationException(IntegrationException integrationException, HttpServletRequest request) {

        ErrorResponse response = new ErrorResponse(integrationException.getCode(),
                integrationException.getLocalizedMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handleException(Exception exception, HttpServletRequest request) {

        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST,
                exception.getLocalizedMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
