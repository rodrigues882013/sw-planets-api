package com.application.swplanetsapi.domain.facade.impl;

import com.application.swplanetsapi.domain.facade.PlanetFacade;
import com.application.swplanetsapi.domain.model.Planet;
import com.application.swplanetsapi.domain.service.PlanetService;
import com.application.swplanetsapi.infrastructure.exception.ServiceException;
import com.application.swplanetsapi.infrastructure.utils.Constant;
import com.application.swplanetsapi.web.dto.internal.GenericResponse;
import com.application.swplanetsapi.web.dto.internal.PlanetRequest;
import com.application.swplanetsapi.web.dto.internal.PlanetResponse;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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

    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    @Override
    public PlanetResponse create(PlanetRequest planet) {
        Planet p = savePlanet(null, planet);

        //Obtain number of participations on SW movies
        p.setAppearIn(getNumberOfAppears(p.getName()));
        return convert(p);

    }

    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    @Override
    public GenericResponse delete(String id) {
        service.delete(id);
        return new GenericResponse(HttpStatus.OK, Constant.deleteMessage);
    }

    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    @Override
    public List<PlanetResponse> findAll() {
        return service
                .findAll()
                .stream()
                .map(x -> {
                    //Obtain number of participation on SW movies
                    x.setAppearIn(getNumberOfAppears(x.getName()));
                    return convert(x);
                })
                .collect(Collectors.toList());
    }

    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    @Override
    public PlanetResponse findById(String id) {
        Planet p = service.findById(id);

        //Obtain number of participation on SW movies
        p.setAppearIn(getNumberOfAppears(p.getName()));

        return convert(p);
    }

    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    @Override
    public PlanetResponse findByName(String name) {
        Planet p = service.findByName(name);

        //Obtain number of participation on SW movies
        p.setAppearIn(getNumberOfAppears(p.getName()));

        return convert(p);
    }

    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    @Override
    public PlanetResponse update(String id, PlanetRequest planetRequest) {
        Planet p = savePlanet(id, planetRequest);

        //Obtain number of participation on SW movies
        p.setAppearIn(getNumberOfAppears(p.getName()));
        return convert(p);
    }

    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    private Planet savePlanet(String id, PlanetRequest planetRequest){
        validateFields(planetRequest);
        Planet p = mapper.map(planetRequest, Planet.class);

        if (!Objects.isNull(id) || !Strings.isEmpty(id))
            p.setId(id);

        return service.save(p);

    }

    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    private PlanetResponse convert(Planet planet){
        return mapper.map(planet, PlanetResponse.class);
    }

    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    private Integer getNumberOfAppears(String name){
        return service.getNumberMoviesWherePlanetShowedUp(name);
    }

    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    private void validateFields(PlanetRequest request){
        if (Objects.isNull(request) ||
                (Objects.isNull(request.getName()) || Strings.isBlank(request.getName())) ||
                (Objects.isNull(request.getClimate()) || Strings.isBlank(request.getClimate())) ||
                (Objects.isNull(request.getTerrain()) || Strings.isBlank(request.getTerrain()))) {
            throw new ServiceException("Request is invalid", HttpStatus.BAD_REQUEST);
        }
    }





}
