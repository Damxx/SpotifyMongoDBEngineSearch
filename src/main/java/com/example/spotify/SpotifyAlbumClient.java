package com.example.spotify;

import com.example.spotify.model.Spotify;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;

@Controller
public class SpotifyAlbumClient {


    @GetMapping("/album/{authorName}")
    @ResponseBody
    public Spotify getAlbums(OAuth2Authentication details, Principal principal, @PathVariable String authorName){

        final String tokenValueJWT = ((OAuth2AuthenticationDetails)details.getDetails()).getTokenValue();


        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Bearer "+tokenValueJWT);
        HttpEntity entity = new HttpEntity(httpHeaders);

        ResponseEntity<Spotify> exchange = restTemplate.exchange("https://api.spotify.com/v1/search?q="+authorName+"&type=track&market=US&limit=10&offset=5",
                HttpMethod.GET,
                entity,
                Spotify.class);

        
                exchange.getBody().getTracks().getItems().get(0).getName();

        return exchange.getBody();
    }

}
