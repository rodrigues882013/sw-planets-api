package com.application.swplanetsapi.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class Planet {

    @Id
    private Long id;

    private String name;

    private String climate;

    private String terrain;

    @Field(value = "showed_up_in")
    private Integer showedUpIn;
}
