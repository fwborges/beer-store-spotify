package com.br.beer.store.beerstore.service;

import com.br.beer.store.beerstore.entity.beer.BeerStyle;
import com.br.beer.store.beerstore.exception.EntityDuplicatedException;
import com.br.beer.store.beerstore.exception.EntityNotFoundException;
import com.br.beer.store.beerstore.repository.BeerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BeerServiceTest {

    private BeerService service;

    @Mock
    private BeerRepository repository;

    @Before
    public void setUp() {

        service = new BeerService(repository);
    }

    @Test
    public void shouldCallSaveMethod() {

        BeerStyle beerStyle = getBeerStyle();

        service.save(beerStyle);

        verify(repository).save(beerStyle);
    }

    @Test(expected = EntityDuplicatedException.class)
    public void shouldThrowsDuplicatedStyleException() {

        BeerStyle beerStyle = getBeerStyle();

        when(repository.findByBeerStyleIgnoreCase(beerStyle.getBeerStyle())).thenReturn(Optional.of(new BeerStyle()));

        service.save(beerStyle);
    }

    @Test
    public void shouldCallUpdateMethod() {

        BeerStyle beerStyle = getBeerStyle();
        when(repository.findById(1l)).thenReturn(Optional.of(new BeerStyle()));

        service.update(1l, beerStyle);

        verify(repository).save(any());
    }

    @Test(expected = EntityNotFoundException.class)
    public void shouldThrowsEntityNotFoundExceptionOnUpdate() {

        BeerStyle beerStyle = getBeerStyle();
        when(repository.findById(1l)).thenReturn(Optional.empty());

        service.update(1l, beerStyle);
    }

    @Test
    public void shouldGetBeerByStyle() {

        BeerStyle beerStyle = getBeerStyle();

        when(repository.findByBeerStyleIgnoreCase("example")).thenReturn(Optional.of(beerStyle));

        BeerStyle style = service.findByStyle("example");

        assertEquals("Example", style.getBeerStyle());
        assertEquals( new Double(0.0), style.getMinTemperature());
        assertEquals(new Double(3.0), style.getMaxTemperature());
    }

    @Test(expected = EntityNotFoundException.class)
    public void shouldThrowsEntityNotFoundExceptionWhenGetBeerByStyle() {

        BeerStyle beerStyle = getBeerStyle();

        when(repository.findByBeerStyleIgnoreCase("example")).thenReturn(Optional.empty());

        service.findByStyle("Example");
    }

    @Test(expected = EntityNotFoundException.class)
    public void shouldThrowsEntityNotFoundExceptionOnDelete() {

        BeerStyle beerStyle = getBeerStyle();
        when(repository.findById(1l)).thenReturn(Optional.empty());

        service.delete(1l);
    }

    private BeerStyle getBeerStyle() {

        BeerStyle beerStyle = new BeerStyle();
        beerStyle.setBeerStyle("Example");
        beerStyle.setMinTemperature(0.0);
        beerStyle.setMaxTemperature(3.0);
        return beerStyle;
    }
}
