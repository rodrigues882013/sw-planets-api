package com.application.swplanetsapi.infrastructure.api;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "star-wars-api", url = "${application.starwars.url}")
public interface SWApi {


}
