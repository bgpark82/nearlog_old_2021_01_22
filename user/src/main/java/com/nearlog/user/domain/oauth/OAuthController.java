package com.nearlog.user.domain.oauth;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class OAuthController {

    private final Gson gson;

    @GetMapping("/oauth2/callback")
    public OAuthToken callback(@RequestParam String code) {
        String credential = "test:test";
        String encodedCredentials = new String(Base64.encodeBase64(credential.getBytes()));

        // Authorization : Basic ew3gb8
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization","Basic " + encodedCredentials);
        // code : Beyi
        // grant_type : authorization_code
        // redirect_uri : http://localhost:8080/oauth2/callback
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("grant_type","authorization_code");
        params.add("redirect_uri","http://localhost:8082/oauth2/callback");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = new RestTemplate()
                .postForEntity("http://localhost:8082/oauth/token", request, String.class);
        if(response.getStatusCode() == HttpStatus.OK) {
            return gson.fromJson(response.getBody(), OAuthToken.class);
        }
        return null;
    }
}
