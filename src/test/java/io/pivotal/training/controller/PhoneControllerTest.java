package io.pivotal.training.controller;

import io.pivotal.training.model.Phone;
import io.pivotal.training.service.PhoneService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class PhoneControllerTest {

    private MockMvc mvc;

    @Mock
    PhoneService phoneService;

    @InjectMocks
    private PhoneController phoneController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mvc = MockMvcBuilders.standaloneSetup(phoneController)
                .build();
    }

    @Test
    public void getPhones() throws Exception {
        Phone phone = new Phone("iPhone");
        List<Phone> phones = Arrays.asList(phone);
        when(phoneService.getPhones()).thenReturn(phones);

        mvc.perform(get("/phones").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string("[{\"name\":\"iPhone\"}]"));
    }

    @Test
    public void createPhone() throws Exception {
        mvc.perform(post("/phones").content("{\"name\":\"iPhone\"}")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());

        verify(phoneService).createPhone(any(Phone.class));
    }

}
