package de.tum.in.ase.insertteamnamehere.controller;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class RestaurantController {

    private final WebClient webClient;

    public RestaurantController(){
        this.webClient = WebClient.builder().build();
    }
}
