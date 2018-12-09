package com.application.swplanetsapi.web.dto.internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class GenericResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private HttpStatus httpStatus;
    private String message;
}
