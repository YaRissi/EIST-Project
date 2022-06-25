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
    private final List<Restaurant> restaurantList;

    public RestaurantController() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8080/")
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        this.restaurantList = new ArrayList<>();

    }

    public void addRestaurant(Restaurant restaurant, Consumer<List<Restaurant>> restaurantConsumer) {
        webClient.put().
                uri("restaurants")
                .bodyValue(restaurant)
                .retrieve()
                .bodyToMono(Restaurant.class)
                .onErrorStop()
                .subscribe(newRestaurant ->
                {
                    restaurantList.add(newRestaurant);
                    restaurantConsumer.accept(restaurantList);

                });

    }

    public void updateRestaurant(Restaurant restaurant, Consumer<List<Restaurant>> restaurantConsumer) {
        webClient.put()
                .uri("restaurants/" + restaurant.getRestaurantID())
                .bodyValue(restaurant)
                .retrieve()
                .bodyToMono(Restaurant.class)
                .onErrorStop()
                .subscribe(newRestaurant -> {
                    restaurantList.replaceAll(oldRestaurant -> oldRestaurant.getRestaurantID().equals(newRestaurant.getRestaurantID()) ? newRestaurant : oldRestaurant);
                    restaurantConsumer.accept(restaurantList);
                });
    }

    public void deleteRestaurant(Restaurant restaurant, Consumer<List<Restaurant>> restaurantConsumer) {
        webClient.delete()
                .uri("restaurants/" + restaurant.getRestaurantID())
                .retrieve()
                .toBodilessEntity()
                .onErrorStop()
                .subscribe(v -> {
                    restaurantList.remove(restaurant);
                    restaurantConsumer.accept(restaurantList);
                });

    }

    public void getAllRestaurants(Consumer<List<Restaurant>> restaurantConsumer) {
        webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("restaurants")
                        .queryParam("secret", "SecretKey")
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Restaurant>>() {
                })
                .onErrorStop()
                .subscribe(newRestaurants -> {
                    restaurantList.clear();
                    restaurantList.addAll(newRestaurants);
                    restaurantConsumer.accept(restaurantList);
                });

    }
}
