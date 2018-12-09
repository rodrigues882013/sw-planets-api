package com.application.swplanetsapi.web.controller;

import com.application.swplanetsapi.domain.facade.PlanetFacade;
import com.application.swplanetsapi.web.dto.internal.GenericResponse;
import com.application.swplanetsapi.web.dto.internal.PlanetRequest;
import com.application.swplanetsapi.web.dto.internal.PlanetResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class PlanetControllerTest {

    @InjectMocks
    private PlanetController controller;

    @Mock
    private PlanetFacade facade;

    private static String TATOOINE_ID = "1dasd151d11da5s";
    private static String TATOOINE= "TATOOINE";
    private static final Integer TATOOINE_APPEARS = 7;
    private static final String CLIMATE_TEMPERATE = "temperate";
    private static final String TERRAIN_MOUNTAIN = "grasslands, mountains";

    private PlanetRequest tatooineReq;




    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        controller = new PlanetController(facade);

        tatooineReq = PlanetRequest.builder()
                .name(TATOOINE)
                .climate(CLIMATE_TEMPERATE)
                .terrain(TERRAIN_MOUNTAIN).build();
    }

    @Test
    public void testActionFindById() {

        doReturn(mock(PlanetResponse.class)).when(facade).findById(TATOOINE_ID);
        ResponseEntity response = controller.findById(TATOOINE_ID);

        verify(facade).findById(TATOOINE_ID);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testActionFindAll() {

        doReturn(mock(List.class)).when(facade).findAll();
        ResponseEntity response = controller.findAll(null);

        verify(facade).findAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testActionFindByName() {

        doReturn(mock(PlanetResponse.class)).when(facade).findByName(TATOOINE);
        ResponseEntity response = controller.findAll(TATOOINE);

        verify(facade).findByName(TATOOINE);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testActionDeleteById() {

        doReturn(mock(GenericResponse.class)).when(facade).delete(TATOOINE_ID);
        ResponseEntity response = controller.delete(TATOOINE_ID);

        verify(facade).delete(TATOOINE_ID);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testActionUpdate() {

        doReturn(mock(PlanetResponse.class)).when(facade).update(TATOOINE_ID, tatooineReq);
        ResponseEntity response = controller.update(TATOOINE_ID, tatooineReq);

        verify(facade).update(TATOOINE_ID, tatooineReq);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testActionCreate() {

        doReturn(mock(PlanetResponse.class)).when(facade).create(tatooineReq);
        ResponseEntity response = controller.create(tatooineReq);

        verify(facade).create(tatooineReq);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

}
