package com.application.swplanetsapi.application.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanetSWResponse implements Serializable {
    private String name;
    private String  climate;
    private Date created;
    private String diameter;
    private List<String> films;
    private String gravity;
    private String orbitalPeriod;
    private String population;
    private List<String> residents;
    private String rotationPeriod;
    private String surfaceWater;
    private String terrain;
    private String url;

}
