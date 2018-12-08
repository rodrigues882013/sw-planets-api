package com.application.swplanetsapi.domain.facade;

import com.application.swplanetsapi.web.dto.internal.PlanetRequest;
import com.application.swplanetsapi.web.dto.internal.PlanetResponse;

import java.util.List;

public interface PlanetFacade {
    PlanetResponse create(PlanetRequest planet);
    void delete(String id);
    List<PlanetResponse> findAll();
    PlanetResponse findById(String id);
    PlanetResponse findByName(String name);
}
