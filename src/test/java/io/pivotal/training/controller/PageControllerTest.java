package io.pivotal.training.controller;

import io.pivotal.training.model.Address;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PageControllerTest {

    private MockMvc mvc;

    private PageController pageController = new PageController();

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(pageController)
                .setViewResolvers(getMockViewResolver())
                .build();
    }

    private ViewResolver getMockViewResolver() throws Exception{
        ViewResolver viewResolver = mock(ViewResolver.class);
        when(viewResolver.resolveViewName(any(String.class), any(Locale.class))).thenReturn(mock(View.class));
        return viewResolver;
    }

    @Test
    public void showHomePage() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }

    @Test
    public void showHelloPage() throws Exception {
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(view().name("hello"));
    }

    @Test
    public void showFormPage() throws Exception {
        mvc.perform(get("/createAddress"))
                .andExpect(status().isOk())
                .andExpect(view().name("createAddress"))
                .andExpect(model().attribute("address", instanceOf(Address.class)));
    }

    @Test
    public void postForm() throws Exception {
        mvc.perform(post("/address")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "Raul")
                        .param("surname", "Avila")
                        .param("postcode", "SW1A 1AA"))
                .andExpect(status().isOk())
                .andExpect(view().name("address"))
                .andExpect(model().attribute("address", equalTo(new Address("Raul", "Avila", "SW1A 1AA"))));
    }
}
