package com.application.swplanetsapi.domain.facade.impl;

import com.application.swplanetsapi.infrastructure.exception.ServiceException;
import com.application.swplanetsapi.infrastructure.utils.Constant;
import com.application.swplanetsapi.web.dto.internal.GenericResponse;
import com.application.swplanetsapi.web.dto.internal.PlanetRequest;
import com.application.swplanetsapi.web.dto.internal.PlanetResponse;
import com.application.swplanetsapi.domain.facade.PlanetFacade;
import com.application.swplanetsapi.domain.model.Planet;
import com.application.swplanetsapi.domain.service.PlanetService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlanetFacadeImpl implements PlanetFacade {

    @Autowired
    public DozerBeanMapper mapper;

    @Autowired
    public PlanetService service;

    @Override
    public PlanetResponse create(PlanetRequest planet) {

        validateFields(planet);
        Planet p = mapper.map(planet, Planet.class);
        return convert(service.create(p));

    }

    @Override
    public GenericResponse delete(String id) {
        service.delete(id);
        return new GenericResponse(HttpStatus.OK, Constant.deleteMessage);
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

    private void validateFields(PlanetRequest request){
        if (Objects.isNull(request) ||
                (Objects.isNull(request.getName()) || Strings.isBlank(request.getName())) ||
                (Objects.isNull(request.getClimate()) || Strings.isBlank(request.getClimate())) ||
                (Objects.isNull(request.getTerrain()) || Strings.isBlank(request.getTerrain()))) {
            throw new ServiceException("Request is invalid", HttpStatus.BAD_REQUEST);
        }
    }





}
