package com.application.swplanetsapi.domain.facade;

import com.application.swplanetsapi.application.dto.internal.PlanetRequest;
import com.application.swplanetsapi.application.dto.internal.PlanetResponse;

import java.util.List;

public interface PlanetFacade {
    PlanetResponse create(PlanetRequest planet);
    boolean delete(String id);
    List<PlanetResponse> findAll();
    PlanetResponse findById(String id);
}
