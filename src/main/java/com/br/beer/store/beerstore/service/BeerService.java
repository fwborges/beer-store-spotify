package com.br.beer.store.beerstore.service;

import com.br.beer.store.beerstore.entity.beer.BeerStyle;
import com.br.beer.store.beerstore.exception.EntityDuplicatedException;
import com.br.beer.store.beerstore.exception.EntityNotFoundException;
import com.br.beer.store.beerstore.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeerService {

    private BeerRepository repository;

    @Autowired
    public BeerService(BeerRepository repository) {
        this.repository = repository;
    }

    public void save(BeerStyle beerStyle) {

        Optional<BeerStyle> existingBeerStyle = repository.findByBeerStyleIgnoreCase(beerStyle.getBeerStyle());

        if(existingBeerStyle.isPresent()) {
            throw new EntityDuplicatedException("Beer style already exists");
        }

        beerStyle.calculateAvarageTemperature();
        repository.save(beerStyle);
    }

    public void update(Long beerStyleId, BeerStyle newBeerStyle) {

        Optional<BeerStyle> beerStyle = repository.findById(beerStyleId);

        if(beerStyle.isPresent()) {
            BeerStyle style = beerStyle.get();

            style.setBeerStyle(newBeerStyle.getBeerStyle());
            style.setMaxTemperature(newBeerStyle.getMaxTemperature());
            style.setMinTemperature(newBeerStyle.getMinTemperature());

            style.calculateAvarageTemperature();

            repository.save(style);
        } else {
            throw new EntityNotFoundException("Beer Style not found");
        }
    }

    public BeerStyle findByStyle(String styleName) {

        Optional<BeerStyle> beerStyle = repository.findByBeerStyleIgnoreCase(styleName);

        return beerStyle.orElseThrow(EntityNotFoundException::new);
    }

    public List<BeerStyle> findAll() {

        return repository.findAll();
    }

    public void delete(Long beerStyleId) {

        repository.deleteById(beerStyleId);
    }
}
