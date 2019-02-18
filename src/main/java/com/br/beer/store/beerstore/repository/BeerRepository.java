package com.br.beer.store.beerstore.repository;

import com.br.beer.store.beerstore.entity.beer.BeerStyle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BeerRepository extends CrudRepository<BeerStyle, Long> {

    Optional<BeerStyle> findByBeerStyleIgnoreCase(String beerStyle);

    List<BeerStyle> findAll();

    @Query(value = "SELECT * FROM BEER_STYLE ORDER BY ABS( AVERAGE_TEMPERATURE - :temperature), BEER_STYLE ASC limit 1", nativeQuery = true)
    Optional<BeerStyle> findByAverageTemperature(@Param("temperature")Double temperature);
}
