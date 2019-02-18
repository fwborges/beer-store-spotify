package com.br.beer.store.beerstore.adapter;

import com.br.beer.store.beerstore.dto.Playlist;
import com.br.beer.store.beerstore.dto.TemperatureSearchResponse;
import com.br.beer.store.beerstore.dto.Track;
import com.br.beer.store.beerstore.entity.beer.BeerStyle;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TemperatureSearchAdapter {

    public TemperatureSearchResponse adapt(BeerStyle beerStyle, PlaylistSimplified playlistSimplified, List<PlaylistTrack> tracks) {

        List<Track> trackList = tracks.stream()
                                      .map(track -> new Track.Builder()
                                                                    .withName(track.getTrack().getName())
                                                                    .withLink(track.getTrack().getHref())
                                                                    .withArtist(track.getTrack().getArtists()[0].getName())
                                                                    .build())
                                      .collect(Collectors.toList());

        Playlist playlist = new Playlist.Builder()
                                        .withName(playlistSimplified.getName())
                                        .withTracks(trackList)
                                        .build();


        TemperatureSearchResponse.Builder responseBuilder = new TemperatureSearchResponse.Builder(beerStyle.getBeerStyle());


        return responseBuilder.withPlaylist(playlist).build();
    }
}
