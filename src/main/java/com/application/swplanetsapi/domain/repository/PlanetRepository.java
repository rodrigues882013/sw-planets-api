package com.application.swplanetsapi.domain.repository;

import com.application.swplanetsapi.domain.model.Planet;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PlanetRepository extends MongoRepository<Planet, String> {
    Optional<Planet> findByName(String planetName);
}
