package com.application.swplanetsapi.infrastructure.api;

import com.application.swplanetsapi.application.dto.external.PlanetSWListResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "swapi", url = "${application.starwars.url}")
public interface SWApi {

    @GetMapping(value = "/planets/", produces = "application/json")
    PlanetSWListResponse findAll(@RequestParam(value = "search", required = false) String search);

}
