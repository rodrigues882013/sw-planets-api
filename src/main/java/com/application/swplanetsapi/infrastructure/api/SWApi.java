package com.application.swplanetsapi.infrastructure.api;

import com.application.swplanetsapi.application.dto.external.PlanetSWListResponse;
import com.application.swplanetsapi.infrastructure.exception.IntegrationException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "swapi",
        url = "${application.starwars.url}",
        configuration = {SWApi.SWApiErrorDecode.class})
public interface SWApi {

    @GetMapping(value = "/planets/", produces = "application/json")
    PlanetSWListResponse findAll(@RequestParam(value = "search", required = false) String search);

    class SWApiErrorDecode implements ErrorDecoder {

        @Override
        public Exception decode(String s, Response response) {
            final HttpStatus statusCode = HttpStatus.valueOf(response.status());

            if (HttpStatus.NOT_FOUND.equals(statusCode))
                throw new IntegrationException("Resource not found on external api", HttpStatus.NOT_FOUND);

            throw new IntegrationException("Integration api fail", statusCode);
        }
    }

}
