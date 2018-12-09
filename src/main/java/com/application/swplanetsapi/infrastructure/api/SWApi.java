package com.application.swplanetsapi.infrastructure.api;

import com.application.swplanetsapi.infrastructure.api.fallbacks.SWApiFallback;
import com.application.swplanetsapi.web.dto.external.PlanetSWListResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "swapi",
        url = "${application.starwars.url}",
        fallback = SWApiFallback.class)
public interface SWApi {

    @GetMapping(value = "/planets/", produces = "application/json")
    PlanetSWListResponse find(@RequestParam(value = "search", required = false) String search);

}
