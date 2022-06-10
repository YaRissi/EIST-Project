package de.tum.in.ase.insertteamnamehere.controller;

import de.tum.in.ase.insertteamnamehere.model.Restaurant;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

public class RestaurantController {

    private final WebClient webClient;
    private final List<Restaurant> RestaurtList;

    public RestaurantController(){
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8080/")
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        this.RestaurtList = new ArrayList<>();

    }

    public void addRestaurant(){

    }
    public void updateRestaurant(){

    }
    public void deleteRestaurant(){

    }
    public void getAllRestaurants(){

    }
}
