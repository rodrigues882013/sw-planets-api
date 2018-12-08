package com.application.swplanetsapi.domain.facade.impl;

import com.application.swplanetsapi.application.dto.internal.PlanetRequest;
import com.application.swplanetsapi.application.dto.internal.PlanetResponse;
import com.application.swplanetsapi.domain.facade.PlanetFacade;
import com.application.swplanetsapi.domain.model.Planet;
import com.application.swplanetsapi.domain.service.PlanetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanetFacadeImpl implements PlanetFacade {

    @Autowired
    public DozerBeanMapper mapper;

    @Autowired
    public PlanetService service;

    @Override
    public PlanetResponse create(PlanetRequest planet) {
        Planet p = mapper.map(planet, Planet.class);
        return convert(p);
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<PlanetResponse> findAll() {
        return service
                .findAll()
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    @Override
    public PlanetResponse findById(String id) {
        return convert(service.findById(id));
    }

    private PlanetResponse convert(Planet planet){
        PlanetResponse response = mapper.map(service.create(planet), PlanetResponse.class);
        response.setAppearIn(service.getNumberMoviesWherePlanetShowedUp(planet.getName()));
        return response;
    }



}
