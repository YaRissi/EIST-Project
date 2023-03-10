package de.tum.in.ase.insertteamnamehere.rest;

import de.tum.in.ase.insertteamnamehere.model.PriceCategory;
import de.tum.in.ase.insertteamnamehere.model.Restaurant;
import de.tum.in.ase.insertteamnamehere.model.RestaurantService;
import de.tum.in.ase.insertteamnamehere.model.RestaurantType;
import de.tum.in.ase.insertteamnamehere.service.RestaurantServiceRest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
public class RestaurantResource {
    private final RestaurantServiceRest restaurantService;

    public RestaurantResource(RestaurantServiceRest restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("restaurants")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }


    @PostMapping("restaurants")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        if (restaurant.getRestaurantID() != null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(restaurantService.saveRestaurant(restaurant));
    }

    @PutMapping("restaurant/{restaurantID}")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant restaurant){

        return ResponseEntity.ok(restaurantService.saveRestaurant(restaurant));
    }

    @DeleteMapping("restaurant/{restaurantID}")
    public ResponseEntity<Void> deleteRestaurant (@PathVariable("restaurantID")UUID restaurantID){
        restaurantService.deleteRestaurant(restaurantID);
        return ResponseEntity.noContent().build();
    }
}

