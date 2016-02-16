package io.pivotal.training.service;

import io.pivotal.training.model.Phone;
import io.pivotal.training.repository.PhoneRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PhoneServiceTest {

    @Mock
    private PhoneRepository phoneRepository;

    @InjectMocks
    private PhoneService phoneService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createPhone() throws Exception {
        Phone phone = mock(Phone.class);
        Phone expectedPhone = mock(Phone.class);
        when(phoneRepository.save(phone)).thenReturn(expectedPhone);

        Phone createdPhone = phoneService.createPhone(phone);

        verify(phoneRepository).save(phone);
        assertThat(createdPhone, is(expectedPhone));
    }

    @Test
    public void getPhones() throws Exception {
        List<Phone> expectedPhones = mock(List.class);
        when(phoneRepository.findAll()).thenReturn(expectedPhones);

        List<Phone> phones = phoneService.getPhones();

        assertThat(phones, is(expectedPhones));
    }
}

