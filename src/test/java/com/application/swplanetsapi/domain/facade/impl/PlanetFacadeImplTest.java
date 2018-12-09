package com.application.swplanetsapi.domain.facade.impl;

import com.application.swplanetsapi.domain.facade.PlanetFacade;
import com.application.swplanetsapi.domain.model.Planet;
import com.application.swplanetsapi.domain.service.PlanetService;
import com.application.swplanetsapi.infrastructure.exception.ServiceException;
import com.application.swplanetsapi.infrastructure.utils.Constant;
import com.application.swplanetsapi.web.dto.internal.GenericResponse;
import com.application.swplanetsapi.web.dto.internal.PlanetRequest;
import com.application.swplanetsapi.web.dto.internal.PlanetResponse;
import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PlanetFacadeImplTest {

    private static final String UNKNOWN = "Unknown";
    private static final String TATOOINE = "Tatooine";
    private static final String NEW_TATOOINE = "New Tatooine";
    private static final String JAKKU = "Jakku";
    private static final String NABOO = "Naboo";
    private static final String HOTH = "Hoth";
    
    private static final String CLIMATE_TEMPERATE = "temperate";
    private static final String TERRAIN_MOUNTAIN = "grasslands, mountains";
    private static final String TERRAIN_DESERT = "dessert";


    private static final String TATOOINE_ID = "2a7b4170aef73e5a67ea88a9";
    private static final String UNKNOWN_ID = "9ff26b7b5e024125aab4560b";
    private static final String JAKKU_ID = "f5ace2e5c315464390d644a4";
    private static final String HOTH_ID = "b64ae853ec224938b50a055b";
    private static final String NABOO_ID = "9ac26b7b5e024125aab4560b";

    private static final Integer TATOOINE_APPEARS = 7;
    private static final Integer NABOO_APPEARS = 5;
    private static final Integer HOTH_APPEARS = 2;
    private static final Integer JAKKU_APPEARS = 3;


    private Planet tatooine;
    private Planet jakku;
    private Planet naboo;
    private Planet hoth;
    private Planet hothConverted;



    private PlanetRequest emptyReq;
    private PlanetRequest hothReq;


    private PlanetFacade planetFacade;
    private PlanetService planetService;

    private GenericResponse genericResponse;


    @Before
    public void setUp(){
        planetService = mock(PlanetService.class);
        planetFacade = new PlanetFacadeImpl(new DozerBeanMapper(), planetService);

        genericResponse = GenericResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(Constant.deleteMessage).build();

        tatooine = Planet.builder()
                .name(TATOOINE)
                .id(TATOOINE_ID)
                .climate(CLIMATE_TEMPERATE)
                .appearIn(TATOOINE_APPEARS)
                .terrain(TERRAIN_MOUNTAIN).build();

        jakku = Planet.builder()
                .name(JAKKU)
                .id(JAKKU_ID)
                .climate(CLIMATE_TEMPERATE)
                .appearIn(JAKKU_APPEARS)
                .terrain(TERRAIN_MOUNTAIN).build();

        hoth = Planet.builder()
                .name(HOTH)
                .id(HOTH_ID)
                .climate(CLIMATE_TEMPERATE)
                .appearIn(HOTH_APPEARS)
                .terrain(TERRAIN_MOUNTAIN).build();

        hothReq = PlanetRequest.builder()
                .name(HOTH)
                .climate(CLIMATE_TEMPERATE)
                .terrain(TERRAIN_MOUNTAIN).build();

        emptyReq = PlanetRequest.builder()
                .name("")
                .climate(null)
                .terrain(" ").build();

        hothConverted = Planet.builder()
                .name(HOTH)
                .climate(CLIMATE_TEMPERATE)
                .terrain(TERRAIN_MOUNTAIN).build();

        naboo = Planet.builder()
                .name(NABOO)
                .id(NABOO)
                .climate(CLIMATE_TEMPERATE)
                .appearIn(NABOO_APPEARS)
                .terrain(TERRAIN_MOUNTAIN).build();


        when(planetService.findById(TATOOINE_ID)).thenReturn(tatooine);
        when(planetService.getNumberMoviesWherePlanetShowedUp(TATOOINE)).thenReturn(TATOOINE_APPEARS);
        when(planetService.findById(UNKNOWN_ID)).thenThrow(ServiceException.class);
        when(planetService.getNumberMoviesWherePlanetShowedUp(JAKKU)).thenReturn(JAKKU_APPEARS);
        when(planetService.findByName(JAKKU)).thenReturn(jakku);
        when(planetService.findByName(UNKNOWN)).thenThrow(ServiceException.class);
        when(planetService.save(hothConverted)).thenReturn(hoth);
        when(planetService.delete(NABOO_ID)).thenReturn(Boolean.TRUE);
        when(planetService.delete(UNKNOWN_ID)).thenThrow(ServiceException.class);
        when(planetService.findAll()).thenReturn(Arrays.asList(jakku, naboo, tatooine));

    }

    @Test
    public void testIfGivenIdThenFindById() {
        PlanetResponse testable = planetFacade.findById(TATOOINE_ID);
        assertEquals(tatooine.getId(), testable.getId());
        assertEquals(tatooine.getName(), testable.getName());
        assertEquals(tatooine.getTerrain(), testable.getTerrain());
        assertEquals(tatooine.getClimate(), testable.getClimate());
        assertEquals(tatooine.getAppearIn(), testable.getAppearIn());
    }

    @Test(expected = ServiceException.class)
    public void testIfGivenIdThatNotExistThenThrowsException() {
        planetFacade.findById(UNKNOWN_ID);
    }

    @Test
    public void testIfGivenNameThenFindByName(){
        PlanetResponse testable = planetFacade.findByName(JAKKU);

        assertEquals(jakku.getId(), testable.getId());
        assertEquals(jakku.getName(), testable.getName());
        assertEquals(jakku.getTerrain(), testable.getTerrain());
        assertEquals(jakku.getClimate(), testable.getClimate());
        assertEquals(jakku.getAppearIn(), testable.getAppearIn());
    }

    @Test(expected = ServiceException.class)
    public void testIfGivenNameThatNotExistThenThrowException(){
        planetFacade.findByName(UNKNOWN);
    }

    @Test
    public void testIfGivenNothingThenFindAll(){
        List<PlanetResponse> testable = planetFacade.findAll();
        assertEquals(3, testable.size());
    }

    @Test
    public void testIfGivenPlanetThenCreateIt(){
        PlanetResponse testable = planetFacade.create(hothReq);

        assertEquals(hoth.getId(), testable.getId());
        assertEquals(hoth.getName(), testable.getName());
        assertEquals(hoth.getTerrain(), testable.getTerrain());
        assertEquals(hoth.getClimate(), testable.getClimate());
        assertEquals(hoth.getAppearIn(), testable.getAppearIn());
    }

    @Test
    public void testIfGivenIdThenDeleteIt(){
        assertEquals(genericResponse, planetFacade.delete(NABOO_ID));
    }

    @Test(expected = ServiceException.class)
    public void testIfGivenAnIdThatNotExistThenThrowException(){
        planetFacade.delete(UNKNOWN_ID);
    }

    @Test(expected = ServiceException.class)
    public void testIfGivenAPlanetRequestThenIsInvalid(){
        planetFacade.create(emptyReq);
    }

}
