package com.application.swplanetsapi.application.dto.internal;

import lombok.Data;

@Data
public class PlanetResponse {
    private String name;
    private String climate;
    private String terrain;
    private Integer showedUpIn;
}
