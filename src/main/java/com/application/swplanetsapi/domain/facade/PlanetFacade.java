package com.application.swplanetsapi.domain.facade;

import com.application.swplanetsapi.web.dto.internal.GenericResponse;
import com.application.swplanetsapi.web.dto.internal.PlanetRequest;
import com.application.swplanetsapi.web.dto.internal.PlanetResponse;

import java.util.List;

public interface PlanetFacade {
    PlanetResponse create(PlanetRequest planet);
    GenericResponse delete(String id);
    List<PlanetResponse> findAll();
    PlanetResponse findById(String id);
    PlanetResponse findByName(String name);
    PlanetResponse update(String id, PlanetRequest planetRequest);
}
