package com.application.swplanetsapi.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Planet {

    @Id
    private String id;

    private String name;

    private String climate;

    private String terrain;

    private Integer appearIn;
}
