package com.application.swplanetsapi.application.dto.internal;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class GenericResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private HttpStatus httpStatus;
    private String message;
}
