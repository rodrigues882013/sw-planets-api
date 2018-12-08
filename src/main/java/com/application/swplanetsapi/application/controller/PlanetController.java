package com.application.swplanetsapi.application.controller;

import com.application.swplanetsapi.application.dto.internal.PlanetRequest;
import com.application.swplanetsapi.application.dto.internal.PlanetResponse;
import com.application.swplanetsapi.infrastructure.api.SWApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("planets")
public class PlanetController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public SWApi client;

    @GetMapping("/{id}")
    public ResponseEntity<PlanetResponse> findById(@PathVariable("id") String id){
        return null;
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        //client.findAll();
        return new ResponseEntity<>(client.findAll(null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PlanetResponse> create(@RequestBody PlanetRequest planetRequest){
        return null;
    }

    @PutMapping
    public ResponseEntity<PlanetResponse> update(@PathVariable("id") Integer id,
                                                 @RequestBody PlanetRequest planetRequest){
        return null;
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        return null;
    }



}
