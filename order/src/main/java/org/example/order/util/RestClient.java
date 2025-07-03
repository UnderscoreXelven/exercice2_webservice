package org.example.order.util;

import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class RestClient<T> {

    private final RestTemplate template;
    private final HttpHeaders headers;
    private final String urlApi;

    public RestClient(String urlApi, RestTemplate restTemplate){
        this.urlApi = urlApi;
        this.template = restTemplate;
        this.headers = new HttpHeaders();
        this.headers.setContentType(MediaType.APPLICATION_JSON);
        this.headers.setAccept(List.of(MediaType.APPLICATION_JSON));
    }

    public T get(Class<T> responseType){
        try {
            HttpEntity<String> requestEntity = new HttpEntity<>("",headers);
            ResponseEntity<T> response = template.exchange(urlApi, HttpMethod.GET,requestEntity,responseType);
            return response.hasBody() ? response.getBody() : null;
        }catch (RestClientException e){
            return null;
        }
    }
}
