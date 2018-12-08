package com.application.swplanetsapi.domain.repository;

import com.application.swplanetsapi.domain.model.Planet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlanetRepository extends MongoRepository<Planet, String> {
    Planet findByName(String planetName);
}
