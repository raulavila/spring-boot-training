package io.pivotal.training.controller;

import io.pivotal.training.errors.NutNotFoundException;
import io.pivotal.training.service.NutsService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class NutsControllerTest {

    private MockMvc mvc;

    @Mock
    NutsService nutsService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mvc = MockMvcBuilders.standaloneSetup(new NutsController(nutsService))
                    .setControllerAdvice(new ErrorHandler())
                    .build();
    }

    @Test
    public void test() throws Exception {
        when(nutsService.get(42L)).thenThrow(new NutNotFoundException("Could not find nut with id: 42"));

        mvc.perform(get("/nuts/42").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.message", is("Could not find nut with id: 42")));
    }

}
