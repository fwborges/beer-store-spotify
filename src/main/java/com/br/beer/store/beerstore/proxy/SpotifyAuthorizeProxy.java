package com.br.beer.store.beerstore.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("https://accounts.spotify.com/")
public interface SpotifyAuthorizeProxy {

    @GetMapping("/authorize")
    SpotifyAuthorizeResponse getAuthorize(@RequestParam(value = "client_id") String clientId,
                                          @RequestParam(value = "response_type") String responseType,
                                          @RequestParam(value = "redirect_uri") String redirectUri);


}
