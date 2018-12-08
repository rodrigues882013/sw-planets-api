package com.application.swplanetsapi.application.controller;

import com.application.swplanetsapi.application.dto.internal.GenericResponse;
import com.application.swplanetsapi.application.dto.internal.PlanetRequest;
import com.application.swplanetsapi.application.dto.internal.PlanetResponse;
import com.application.swplanetsapi.domain.facade.PlanetFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("planets")
public class PlanetController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public PlanetFacade facade;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id){
        return new ResponseEntity<>(facade.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam(value = "name", required = false) String name) {

        if (Objects.isNull(name)) {
            return new ResponseEntity<>(facade.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(facade.findByName(name), HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<PlanetResponse> create(@RequestBody PlanetRequest planetRequest){
        return new ResponseEntity<>(facade.create(planetRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        facade.delete(id);
        GenericResponse response =
                new GenericResponse(HttpStatus.OK, "Resource deleted with successful");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }



}
