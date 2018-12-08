package com.application.swplanetsapi.application.dto.internal;

import lombok.Data;

@Data
public class PlanetRequest {
    private String name;
    private String climate;
    private String terrain;
}
