package de.tum.in.ase.insertteamnamehere.service;

import de.tum.in.ase.insertteamnamehere.model.Restaurant;

import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceRest {

    private final List<Restaurant> restaurantList;

    public RestaurantServiceRest() {
        this.restaurantList = new ArrayList<>();

    }
    public Restaurant saveRestaurant(Restaurant restaurant){
        var optionalRestaurant = restaurantList.stream().filter(existingRestaurant -> existingRestaurant.getRestaurantID().equals(restaurant.getRestaurantID())).findFirst();
        if (optionalRestaurant.isEmpty()){
            //restaurant.setRestaurantID(UUID.randomUUID());
            restaurantList.add(restaurant);
            return restaurant;
        }else {
            var existingRestaurant = optionalRestaurant.get();
            existingRestaurant.setName(restaurant.getName());
            // hier müssen glaube ich noch die ganzen attribute des Restaurants hinzugefügt werden (Name, Offnungszeiten, Tische etc)
            return existingRestaurant;
        }
    }
    public void deleteRestaurant(UUID restaurantID){
        this.restaurantList.removeIf(restaurant -> restaurant.getRestaurantID().equals(restaurantID));

    }
    public List<Restaurant> getAllRestaurants(){
        //return restaurantList.stream().toList();
        return Collections.unmodifiableList(this.restaurantList);
    }


}
