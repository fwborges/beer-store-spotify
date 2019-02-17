package com.br.beer.store.beerstore.repository;

import com.br.beer.store.beerstore.entity.beer.BeerStyle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BeerRepository extends MongoRepository<BeerStyle, Long> {

    Optional<BeerStyle> findByBeerStyleIgnoreCase(String beerStyle);

    List<BeerStyle> findByAverageTemperatureOrderByBeerStyleAsc(Double temperature);
}
