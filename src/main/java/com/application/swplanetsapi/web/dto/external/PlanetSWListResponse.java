package com.application.swplanetsapi.web.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanetSWListResponse implements Serializable {
    private Integer count;
    private String next;
    private String previous;
    private List<PlanetSWResponse> results;
}
