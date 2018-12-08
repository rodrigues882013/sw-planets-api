package com.application.swplanetsapi.application.controller;

import com.application.swplanetsapi.application.dto.internal.ErrorResponse;
import com.application.swplanetsapi.infrastructure.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(value = ServiceException.class)
    public ResponseEntity<?> handleServicexception(ServiceException serviceException, HttpServletRequest request) {

        ErrorResponse response = new ErrorResponse(serviceException.getCode(), serviceException.getLocalizedMessage());

        return new ResponseEntity<>(response, serviceException.getCode());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handleException(Exception serviceException, HttpServletRequest request) {

        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST, serviceException.getLocalizedMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
