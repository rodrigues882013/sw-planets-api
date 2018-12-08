package com.application.swplanetsapi.domain.service.impl;

import com.application.swplanetsapi.domain.model.Planet;
import com.application.swplanetsapi.domain.repository.PlanetRepository;
import com.application.swplanetsapi.domain.service.PlanetService;
import com.application.swplanetsapi.infrastructure.api.SWApi;
import org.springframework.beans.factory.annotation.Autowired;
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

        return null;
    }

    @Override
    public Planet update(Long id, Planet planet) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<Planet> findAll() {
        return null;
    }

    @Override
    public Planet findById(Long id) {
        return null;
    }

    @Override
    public Integer countAppeirs(String planeName) {
        return null;
    }
}
