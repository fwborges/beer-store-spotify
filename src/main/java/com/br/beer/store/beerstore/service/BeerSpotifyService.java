package com.br.beer.store.beerstore.service;

import com.br.beer.store.beerstore.entity.beer.BeerStyle;
import com.br.beer.store.beerstore.proxy.SpotifyAuthorizeProxy;
import com.br.beer.store.beerstore.proxy.SpotifyAuthorizeResponse;
import com.br.beer.store.beerstore.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeerSpotifyService {

    private BeerRepository repository;

    private SpotifyAuthorizeProxy authorizeProxy;

    @Autowired
    public BeerSpotifyService(BeerRepository repository, SpotifyAuthorizeProxy authorizeProxy) {
        this.repository = repository;
        this.authorizeProxy = authorizeProxy;
    }

    public void findStyleByTemperature(Double temperature) {

        SpotifyAuthorizeResponse authorize = authorizeProxy.getAuthorize("160628bf46684eba8b14bab4f6bef42d", "code", "localhost:8080");

        List<BeerStyle> beerStyleList = repository.findByAverageTemperatureOrderByBeerStyleAsc(temperature);

        //consultar api spotify
        //converter para response
    }
}
