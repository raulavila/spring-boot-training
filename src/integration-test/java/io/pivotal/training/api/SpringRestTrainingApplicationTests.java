package io.pivotal.training.api;

import io.pivotal.training.SpringTrainingApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringTrainingApplication.class)
@WebAppConfiguration
@Transactional
public class SpringRestTrainingApplicationTests {

	public static final String ALMOND_JSON = "{\"name\":\"almond\"}";

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void listAllNuts_WhenThereAreNone() throws Exception {
		mvc.perform(get("/nuts").accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(content().string("[]"));
	}

	@Test
	public void listAllNuts_WhenThereIsOne() throws Exception {
		mvc.perform(post("/nuts").content(ALMOND_JSON)
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isCreated());

		mvc.perform(get("/nuts").accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].name", is("almond")));
	}

	@Test
	public void createNut() throws Exception {
		mvc.perform(post("/nuts").content(ALMOND_JSON)
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isCreated());
	}

	@Test
	public void createNut_returnsLocationHeader() throws Exception {
		mvc.perform(post("/nuts").content(ALMOND_JSON)
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isCreated())
				.andExpect(header().string(HttpHeaders.LOCATION, endsWith("/nuts/1")));
	}

	@Test
	public void removeNut() throws Exception {
		MvcResult mvcResult = mvc.perform(post("/nuts").content(ALMOND_JSON)
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isCreated())
				.andReturn();

		String nutUri = mvcResult.getResponse().getHeader(HttpHeaders.LOCATION);

		mvc.perform(delete(nutUri))
				.andExpect(status().isNoContent());

		mvc.perform(get("/nuts").accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(content().string("[]"));
	}

	@Test
	public void removedNotExistingNut() throws Exception {
		mvc.perform(delete("/nuts/12345"))
				.andExpect(status().isNotFound());

	}

	@Test
	public void getNut() throws Exception {
		MvcResult mvcResult = mvc.perform(post("/nuts").content(ALMOND_JSON)
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isCreated())
				.andReturn();

		String nutUri = mvcResult.getResponse().getHeader(HttpHeaders.LOCATION);

		mvc.perform(get(nutUri).accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.id", is(any(Integer.class))))
				.andExpect(jsonPath("$.name", is("almond")));
	}

	@Test
	public void getNotExistingNut() throws Exception {
		mvc.perform(get("/nuts/42").accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isNotFound())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.message", is("Could not find nut with id: 42")));
	}
}
