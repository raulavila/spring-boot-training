package io.pivotal.training.service;

import io.pivotal.training.errors.NutNotFoundException;
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
import static org.hamcrest.Matchers.notNullValue;
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
        Nut createdNut = mock(Nut.class);
        when(nutsRepository.save(almond)).thenReturn(createdNut);

        Nut savedNut = nutsService.create(almond);

        verify(nutsRepository).save(almond);
        assertThat(savedNut, is(createdNut));
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

    @Test
    public void deleteNutById() throws Exception {
        Long nutId = 1L;
        when(nutsRepository.exists(nutId)).thenReturn(true);

        boolean result = nutsService.delete(nutId);

        verify(nutsRepository).delete(nutId);
        assertThat(result, is(true));
    }

    @Test
    public void deleteNotExistingNut() throws Exception {
        Long nutId = 1L;
        when(nutsRepository.exists(nutId)).thenReturn(false);

        boolean result = nutsService.delete(nutId);

        verify(nutsRepository, never()).delete(nutId);
        assertThat(result, is(false));
    }

    @Test
    public void getNut() throws Exception {
        Long nutId = 1L;
        Nut expectedNut = mock(Nut.class);
        when(nutsRepository.exists(nutId)).thenReturn(true);
        when(nutsRepository.getOne(nutId)).thenReturn(expectedNut);

        Nut nut = nutsService.get(nutId);

        assertThat(nut, is(notNullValue()));
        assertThat(nut, is(expectedNut));
    }

    @Test(expected = NutNotFoundException.class)
    public void getNotExistingNut() throws Exception {
        Long nutId = 42L;
        when(nutsRepository.exists(nutId)).thenReturn(false);

        nutsService.get(nutId);
    }
}
