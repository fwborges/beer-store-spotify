package com.br.beer.store.beerstore.config;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class SpotifyConfig {

    private static final String clientId = "160628bf46684eba8b14bab4f6bef42d";
    private static final String clientSecret = "395d7fdf6ac04e338c566236808e4461";


    @Bean
    public ClientCredentials clientCredentials() throws IOException, SpotifyWebApiException {

        return spotifyApi().clientCredentials()
                .build()
                .execute();
    }

    @Bean
    public SpotifyApi spotifyApi() {

        return new SpotifyApi.Builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .build();
    }


}
