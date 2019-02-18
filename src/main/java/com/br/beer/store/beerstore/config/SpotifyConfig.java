package com.br.beer.store.beerstore.config;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;

@Configuration
@PropertySource("classpath:application.properties")
public class SpotifyConfig {

    @Value("${app.spotify.client.id}")
    private String clientId;

    @Value("${app.spotify.client.secret}")
    private String clientSecret;

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
