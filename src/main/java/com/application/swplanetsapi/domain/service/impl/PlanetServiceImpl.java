package com.application.swplanetsapi.domain.service.impl;

import com.application.swplanetsapi.web.dto.external.PlanetSWResponse;
import com.application.swplanetsapi.domain.model.Planet;
import com.application.swplanetsapi.domain.repository.PlanetRepository;
import com.application.swplanetsapi.domain.service.PlanetService;
import com.application.swplanetsapi.infrastructure.api.SWApi;
import com.application.swplanetsapi.infrastructure.exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor
public class PlanetServiceImpl implements PlanetService {

    @Autowired
    public SWApi client;

    @Autowired
    public PlanetRepository repository;

    @Autowired
    public RedisTemplate<String, String> redisTemplate;

    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    @Override
    public Planet save(Planet planet) {

        try {
            log.info("Persisting planet");
            return repository.save(planet);

        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new ServiceException("Resource wasn't created", HttpStatus.NO_CONTENT);
        }
    }

    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    @Override
    public Boolean delete(String id) {
        log.info("Dropping planet with id {} from database", id);
        Planet p = findById(id);
        repository.delete(p);
        flushCache(p.getName());
        return true;
    }

    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    @Override
    public List<Planet> findAll() {
        return repository.findAll();
    }

    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    @Override
    public Planet findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ServiceException("Resource not found", HttpStatus.NOT_FOUND));
    }

    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    @Override
    @Cacheable(cacheNames = "planet-name",
            key = "#planetName",
            condition = "#planetName != null and #planetName != ''")
    public Integer getNumberMoviesWherePlanetShowedUp(String planetName){
        log.info("Call StarWars api for get information about planet {}", planetName);
        return countAppears(client.find(planetName).getResults());
    }

    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    @Override
    public Planet findByName(String name) {
        log.info("Searching for planet {}", name);
        return repository.findByName(name)
                .orElseThrow(() -> new ServiceException("Resource not Found", HttpStatus.NOT_FOUND));
    }

    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    private Integer countAppears(List<PlanetSWResponse> objects) {
        log.info("Counting some many times planets was showed up on movies.");

        return objects
                .stream()
                .map(x -> x.getFilms().size())
                .reduce((x, y) -> x + y)
                .orElse(0);
    }

    private void flushCache(String key){
        redisTemplate.delete("planet-name::" + key);
    }
}
