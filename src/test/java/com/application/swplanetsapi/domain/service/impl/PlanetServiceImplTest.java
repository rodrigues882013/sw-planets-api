package com.application.swplanetsapi.domain.service.impl;

import com.application.swplanetsapi.domain.facade.PlanetFacade;
import com.application.swplanetsapi.domain.model.Planet;
import com.application.swplanetsapi.domain.repository.PlanetRepository;
import com.application.swplanetsapi.domain.service.PlanetService;
import com.application.swplanetsapi.infrastructure.api.SWApi;
import com.application.swplanetsapi.infrastructure.exception.ServiceException;
import com.application.swplanetsapi.web.dto.internal.PlanetResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PlanetServiceImplTest {
    private static final String UNKNOWN = "Unknown";
    private static final String TATOOINE = "Tatooine";
    private static final String JAKKU = "Jakku";
    private static final String NABOO = "Naboo";

    private static final String CLIMATE_ARID = "temperate";
    private static final String TERRAIN_MOUNTAIN = "grasslands, mountains";
    private static final String TATOOINE_ID = "2a7b4170aef73e5a67ea88a9";
    private static final String JAKKU_ID = "f5ace2e5c315464390d644a4";
    private static final String UNKNOWN_ID = "9ff26b7b5e024125aab4560b";
    private static final String NABOO_ID = "9ff26b7b5e4569125aab4560b";


    private static final Integer TATOOINE_APPEARS = 7;
    private static final Integer JAKKU_APPEARS = 3;
    private static final Integer NABOO_APPEARS = 9;

    private Planet tatooine;
    private Planet jakku;
    private Planet naboo;
    private Planet nabooSaved;


    private PlanetService planetService;
    private PlanetRepository repository;
    private SWApi client;

    @Before
    public void setUp(){
//        client = mock(SWApi.class);
        repository = mock(PlanetRepository.class);
        planetService = new PlanetServiceImpl(client, repository);

        tatooine = Planet.builder()
                .name(TATOOINE)
                .id(TATOOINE_ID)
                .climate(CLIMATE_ARID)
                .appearIn(TATOOINE_APPEARS)
                .terrain(TERRAIN_MOUNTAIN).build();

        jakku = Planet.builder()
                .name(JAKKU)
                .id(JAKKU_ID)
                .climate(CLIMATE_ARID)
                .appearIn(JAKKU_APPEARS)
                .terrain(TERRAIN_MOUNTAIN).build();

        naboo = Planet.builder()
                .name(NABOO)
                .climate(CLIMATE_ARID)
                .appearIn(NABOO_APPEARS)
                .terrain(TERRAIN_MOUNTAIN).build();

        nabooSaved = Planet.builder()
                .name(NABOO)
                .id(NABOO_ID)
                .climate(CLIMATE_ARID)
                .appearIn(NABOO_APPEARS)
                .terrain(TERRAIN_MOUNTAIN).build();



        when(repository.findById(TATOOINE_ID)).thenReturn(Optional.of(tatooine));
        when(repository.findById(UNKNOWN_ID)).thenThrow(ServiceException.class);
        when(repository.findByName(JAKKU)).thenReturn(Optional.of(jakku));
//        when(repository.findByName(UNKNOWN)).thenThrow(ServiceException.class);
        when(repository.save(naboo)).thenReturn(nabooSaved);
        when(repository.findAll()).thenReturn(Arrays.asList(jakku, tatooine));



    }

    @Test
    public void testIfGivenIdFindResultFromDB(){
        Planet testable = planetService.findById(TATOOINE_ID);

        assertEquals(tatooine.getId(), testable.getId());
        assertEquals(tatooine.getName(), testable.getName());
        assertEquals(tatooine.getTerrain(), testable.getTerrain());
        assertEquals(tatooine.getClimate(), testable.getClimate());
        assertEquals(tatooine.getAppearIn(), testable.getAppearIn());
    }

    @Test(expected = ServiceException.class)
    public void testIfGivenIdThatNotExistThenThrowsException(){
        planetService.findById(UNKNOWN_ID);
    }

    @Test
    public void testIfGivenNameFindResultFromDB(){
        Planet testable = planetService.findByName(JAKKU);

        assertEquals(jakku.getId(), testable.getId());
        assertEquals(jakku.getName(), testable.getName());
        assertEquals(jakku.getTerrain(), testable.getTerrain());
        assertEquals(jakku.getClimate(), testable.getClimate());
        assertEquals(jakku.getAppearIn(), testable.getAppearIn());
    }

    @Test(expected = ServiceException.class)
    public void testIfGivenNameThatNotExistThenThrowsException(){
        planetService.findByName(UNKNOWN_ID);
    }

    @Test
    public void testIfGivenNothingThenFindAll(){
        List<Planet> testable = planetService.findAll();
        assertEquals(2, testable.size());
    }

    @Test
    public void testIfGivenPlanetThenCreateIt(){
        Planet testable = planetService.create(naboo);
        assertEquals(nabooSaved.getId(), testable.getId());
        assertEquals(nabooSaved.getName(), testable.getName());
        assertEquals(nabooSaved.getTerrain(), testable.getTerrain());
        assertEquals(nabooSaved.getClimate(), testable.getClimate());
        assertEquals(nabooSaved.getAppearIn(), testable.getAppearIn());
    }

}
