package com.application.swplanetsapi.web.dto.internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanetRequest {
    private String name;
    private String climate;
    private String terrain;
}
