package com.application.swplanetsapi.domain.facade.impl;

import com.application.swplanetsapi.web.dto.internal.PlanetRequest;
import com.application.swplanetsapi.web.dto.internal.PlanetResponse;
import com.application.swplanetsapi.domain.facade.PlanetFacade;
import com.application.swplanetsapi.domain.model.Planet;
import com.application.swplanetsapi.domain.service.PlanetService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return convert(service.create(p));
    }

    @Override
    @Transactional
    public void delete(String id) {
        service.delete(id);
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

    @Override
    public PlanetResponse findByName(String name) {
        return convert(service.findByName(name));
    }

    private PlanetResponse convert(Planet planet){
        PlanetResponse response = mapper.map(planet, PlanetResponse.class);
        response.setAppearIn(service.getNumberMoviesWherePlanetShowedUp(planet.getName()));
        return response;
    }



}
