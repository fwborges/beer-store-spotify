package com.br.beer.store.beerstore.service;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SpotifyService {

    private SpotifyApi spotifyApi;

    private ClientCredentials spotifyCredentials;

    @Autowired
    public SpotifyService(SpotifyApi spotifyApi, ClientCredentials spotifyCredentials) {

        this.spotifyApi = spotifyApi;
        this.spotifyCredentials = spotifyCredentials;
    }

    public PlaylistSimplified getPlaylistByName(String name) {

        try {

            spotifyApi.setAccessToken(spotifyCredentials.getAccessToken());

            Paging<PlaylistSimplified> playlists = spotifyApi.searchPlaylists(name)
                                                            .limit(1)
                                                            .build()
                                                            .execute();

            if(playlists.getTotal() > 0) {
                return playlists.getItems()[0];
            }

        } catch (IOException | SpotifyWebApiException e) {
            e.printStackTrace();
        }

        return new PlaylistSimplified.Builder().build();
    }

    public List<PlaylistTrack> getTracks(String playlistId) {

        spotifyApi.setAccessToken(spotifyCredentials.getAccessToken());

        try {
            return Arrays.asList(spotifyApi.getPlaylistsTracks(playlistId)
                    .fields("items(track(name,href, artists))")
                    .build()
                    .execute()
                    .getItems());
        } catch (IOException | SpotifyWebApiException e) {
            e.printStackTrace();
        }


        return new ArrayList<>();
    }






}
