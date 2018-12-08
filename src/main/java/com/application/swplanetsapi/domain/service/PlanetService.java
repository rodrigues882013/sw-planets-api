package com.application.swplanetsapi.domain.service;

import com.application.swplanetsapi.domain.model.Planet;

import java.util.List;

public interface PlanetService {
    Planet create(Planet planet);
    Planet update(Long id, Planet planet);
    boolean delete(Long id);
    List<Planet> findAll();
    Planet findById(Long id);
    Integer countAppeirs(String planeName);
}
