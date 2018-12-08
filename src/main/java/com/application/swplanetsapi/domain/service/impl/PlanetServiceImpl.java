package com.application.swplanetsapi.domain.service.impl;

import com.application.swplanetsapi.application.dto.external.PlanetSWResponse;
import com.application.swplanetsapi.domain.model.Planet;
import com.application.swplanetsapi.domain.repository.PlanetRepository;
import com.application.swplanetsapi.domain.service.PlanetService;
import com.application.swplanetsapi.infrastructure.api.SWApi;
import com.application.swplanetsapi.infrastructure.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanetServiceImpl implements PlanetService {

    @Autowired
    public SWApi client;

    @Autowired
    public PlanetRepository repository;

    @Override
    public Planet create(Planet planet) {

        try {
            return repository.save(planet);

        } catch (Exception ex) {
            throw new ServiceException("Resource wasn't created", HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            repository.deleteById(id);
            return true;

        } catch (Exception ex) {
            throw new ServiceException("Resource wasn't deleted", HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public List<Planet> findAll() {
        return repository.findAll();
    }

    @Override
    public Planet findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ServiceException("Resource not found", HttpStatus.NOT_FOUND));
    }


    @Override
    @Cacheable(cacheNames = "planet-name",
            key = "#planetName",
            condition = "#planetName != null and #planetName != ''")
    public Integer getNumberMoviesWherePlanetShowedUp(String planetName){
        return countAppears(client.findAll(planetName).getResults());
    }


    private Integer countAppears(List<PlanetSWResponse> objects) {
        return objects
                .stream()
                .map(x -> x.getFilms().size())
                .reduce((x, y) -> x + y)
                .orElse(0);
    }
}
