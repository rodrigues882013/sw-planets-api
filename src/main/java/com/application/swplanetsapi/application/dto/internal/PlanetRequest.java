package com.application.swplanetsapi.application.dto.internal;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PlanetRequest {

    @NotNull
    private String name;

    @NotNull
    private String climate;

    @NotNull
    private String terrain;
}
