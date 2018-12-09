package com.application.swplanetsapi.domain.service;

import com.application.swplanetsapi.domain.model.Planet;

import java.util.List;

public interface PlanetService {
    Planet save(Planet planet);
    Boolean delete(String id);
    List<Planet> findAll();
    Planet findById(String id);
    Integer getNumberMoviesWherePlanetShowedUp(String planetName);
    Planet findByName(String name);
}
