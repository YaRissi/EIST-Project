package de.tum.in.ase.insertteamnamehere.controller;

import de.tum.in.ase.insertteamnamehere.model.Restaurant;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class RestaurantController {

    private final WebClient webClient;
    private final List<Restaurant> RestaurantList;

    public RestaurantController(){
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8080/")
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        this.RestaurantList = new ArrayList<>();

    }

    public void addRestaurant(Restaurant restaurant, Consumer<List<Restaurant>> RestautantConsumer){
        webClient.put().
                uri("restaurants")
                .bodyValue(restaurant)
                .retrieve()
                .bodyToMono(Restaurant.class)
                .onErrorStop()
                .subscribe(newRestautant ->
                    {RestaurantList.add(newRestautant);
                    //RestaurantConsumer.accept(RestaurantList);

                });

    }
    public void updateRestaurant(){
        webClient.put().uri("www.de");

    }
    public void deleteRestaurant(){

    }
    public void getAllRestaurants(){

    }
}
