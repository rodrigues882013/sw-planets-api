package com.application.swplanetsapi.web.dto.internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlanetResponse {
    private String id;
    private String name;
    private String climate;
    private String terrain;
    private Integer appearIn;
}
