package com.br.beer.store.beerstore.service;

import com.br.beer.store.beerstore.adapter.TemperatureSearchAdapter;
import com.br.beer.store.beerstore.dto.TemperatureSearchResponse;
import com.br.beer.store.beerstore.entity.beer.BeerStyle;
import com.br.beer.store.beerstore.exception.EntityNotFoundException;
import com.br.beer.store.beerstore.repository.BeerRepository;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeerSpotifyService {

    private BeerRepository repository;

    private SpotifyService spotifyService;

    private TemperatureSearchAdapter adapter;

    @Autowired
    public BeerSpotifyService(BeerRepository repository, SpotifyService spotifyService, TemperatureSearchAdapter adapter) {
        this.repository = repository;
        this.spotifyService = spotifyService;
        this.adapter = adapter;
    }

    public TemperatureSearchResponse findStyleByTemperature(Double temperature) {

        Optional<BeerStyle> beerStyleOpt = repository.findByAverageTemperature(temperature);

        BeerStyle beerStyle = beerStyleOpt.orElseThrow(EntityNotFoundException::new);

        PlaylistSimplified playlist = spotifyService.getPlaylistByName(beerStyle.getBeerStyle());

        List<PlaylistTrack> tracks = spotifyService.getTracks(playlist.getId());

        return adapter.adapt(beerStyle, playlist, tracks);
    }
}
