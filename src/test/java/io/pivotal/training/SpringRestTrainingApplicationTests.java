package io.pivotal.training;

import io.pivotal.training.repository.NutsRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static javassist.CtMethod.ConstParameter.string;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringRestTrainingApplication.class)
@WebAppConfiguration
@Transactional
public class SpringRestTrainingApplicationTests {

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
		mvc.perform(post("/nuts").content("{\"name\":\"almond\"}")
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
		mvc.perform(post("/nuts").content("{\"name\":\"almond\"}")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isCreated());
	}

	@Test
	public void createNut_returnsLocationHeader() throws Exception {
		mvc.perform(post("/nuts").content("{\"name\":\"almond\"}")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isCreated())
				.andExpect(header().string(HttpHeaders.LOCATION, endsWith("/nuts/1")));
	}
}
