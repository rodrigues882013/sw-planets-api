package com.application.swplanetsapi.web.dto.internal;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class PlanetRequest {
    private String name;
    private String climate;
    private String terrain;
}
