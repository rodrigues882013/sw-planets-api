package com.application.swplanetsapi.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("planets")
public class PlanetController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id){
        return ResponseEntity.ok("OK");
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok("OK");
    }

}
