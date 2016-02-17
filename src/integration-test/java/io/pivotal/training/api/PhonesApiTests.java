package io.pivotal.training.api;

import io.pivotal.training.SpringTrainingApplication;
import io.pivotal.training.model.Phone;
import io.pivotal.training.repository.PhoneRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringTrainingApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:1234")
public class PhonesApiTests {

    private final String BASE_URL = "http://localhost:1234/phones/";
    private RestTemplate rest;

    @Autowired
    private PhoneRepository phoneRepository;

    @Before
    public void setUp() throws Exception {
        rest = new TestRestTemplate();
    }

    @After
    public void tearDown() throws Exception {
        phoneRepository.deleteAll();
    }

    @Test
    public void listPhones_whenNone() {
        ResponseEntity<Phone[]> response =
                rest.getForEntity(BASE_URL, Phone[].class, Collections.emptyMap());

        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        Phone[] phones = response.getBody();
        assertThat(phones.length, is(0));
    }


    @Test
    public void listPhones_whenThereIsOne() throws Exception {
        Phone phone = new Phone("iPhone");

        ResponseEntity<Phone> postResponse =
                rest.postForEntity(BASE_URL, phone, Phone.class, Collections.emptyMap());

        assertThat(postResponse.getStatusCode(), is(HttpStatus.CREATED));

        ResponseEntity<Phone[]> response =
                rest.getForEntity(BASE_URL, Phone[].class, Collections.emptyMap());

        List<Phone> phones = Arrays.asList(response.getBody());

        assertThat(phones, contains(phone));
    }
}
