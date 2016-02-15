package io.pivotal.training.service;

import io.pivotal.training.model.Nut;
import io.pivotal.training.repository.NutsRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

public class NutsServiceTest {

    @Mock
    NutsRepository nutsRepository;

    @InjectMocks
    NutsService nutsService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void create() {
        Nut almond = createNut("almond");

        nutsService.create(almond);

        verify(nutsRepository).save(almond);
    }

    private Nut createNut(String name) {
        return new Nut(name);
    }

    @Test
    public void list_whenNone() {
        assertThat(nutsService.list(), is(empty()));
        verify(nutsRepository).findAll();
    }

    @Test
    public void list_whenOne() {
        List<Nut> expectedNutsList = mock(List.class);

        when(nutsRepository.findAll()).thenReturn(expectedNutsList);

        List<Nut> nutsList = nutsService.list();

        assertThat(nutsList, is(expectedNutsList));
    }
}
