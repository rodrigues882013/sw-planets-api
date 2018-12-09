package com.application.swplanetsapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Planet {

    @Id
    private String id;

    private String name;

    private String climate;

    private String terrain;

    private Integer appearIn;
}
