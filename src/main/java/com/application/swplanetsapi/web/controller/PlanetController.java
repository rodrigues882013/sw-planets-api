package com.application.swplanetsapi.web.controller;

import com.application.swplanetsapi.web.dto.internal.GenericResponse;
import com.application.swplanetsapi.web.dto.internal.PlanetRequest;
import com.application.swplanetsapi.web.dto.internal.PlanetResponse;
import com.application.swplanetsapi.domain.facade.PlanetFacade;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("planets")
public class PlanetController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public PlanetFacade facade;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id){
        log.info("Searching for planet with id: {}", id);
        return new ResponseEntity<>(facade.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam(value = "name", required = false) String name) {

        if (Objects.isNull(name)) {
            log.info("Listing all planets.");
            return new ResponseEntity<>(facade.findAll(), HttpStatus.OK);
        } else {
            log.info("Looking for planet with name: {}", name);
            return new ResponseEntity<>(facade.findByName(name), HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<PlanetResponse> create(@RequestBody PlanetRequest planetRequest){
        log.info("Creating planet {}", planetRequest);
        return new ResponseEntity<>(facade.create(planetRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        log.info("Deleting planet id: {}", id);
        return new ResponseEntity<>(facade.delete(id), HttpStatus.OK);

    }



}
