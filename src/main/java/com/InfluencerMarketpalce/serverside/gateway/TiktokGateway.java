package com.InfluencerMarketpalce.serverside.gateway;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TiktokGateway {
    RestTemplate restTemplate = new RestTemplate();

    public String callGetVideoInfo(String url, String key, String host) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.add("X-RapidAPI-Key", key);
        headers.add("X-RapidAPI-Host", host);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        String response = "";
        try{
            response = restTemplate.exchange(url,
                    HttpMethod.GET, entity, String.class
            ).getBody();
        } catch(Exception e){
            e.printStackTrace();
        }
        return response;
    }
}
