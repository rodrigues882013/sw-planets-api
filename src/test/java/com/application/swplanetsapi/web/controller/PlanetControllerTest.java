package com.application.swplanetsapi.web.controller;

import com.application.swplanetsapi.domain.facade.PlanetFacade;
import com.application.swplanetsapi.web.controller.PlanetController;
import com.application.swplanetsapi.web.dto.internal.GenericResponse;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class PlanetControllerTest {

    @InjectMocks
    private PlanetController controller;

    @Mock
    private PlanetFacade facade;

    private static String TATOOINE_ID = "1dasd151d11da5s";
    private static String TATOOINE= "TATOOINE";


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        controller = new PlanetController(facade);
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

}
