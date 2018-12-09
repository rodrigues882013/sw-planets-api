package com.application.swplanetsapi.infrastructure.api.fallbacks;

import com.application.swplanetsapi.infrastructure.api.SWApi;
import com.application.swplanetsapi.web.dto.external.PlanetSWListResponse;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@NoArgsConstructor
@Slf4j
public class SWApiFallback implements SWApi {

    @Override
    public PlanetSWListResponse find(String search) {
        /*
        * In this case, we consider that planet is really new
        * and not showed up before in any Star War movie franchise.
        * */
        log.info("Using fallback.");
        log.warn("Set to 0 the number of appears, in this case or API is unavailable or planet not exist on SWAPI database.");
        PlanetSWListResponse response = new PlanetSWListResponse();
        response.setResults(new ArrayList<>());
        return response;
    }
}
