package de.tum.in.ase.insertteamnamehere.user;

import de.tum.in.ase.insertteamnamehere.model.Reservation;
import de.tum.in.ase.insertteamnamehere.model.Restaurant;
import de.tum.in.ase.insertteamnamehere.model.RestaurantService;
import de.tum.in.ase.insertteamnamehere.util.Coord;

import java.util.List;
import java.util.UUID;

public class User {
    private String name;
    private UUID userID;
    private Reservation reservation;
    private RestaurantService restaurantService; // User ruft Methoden des RestaurantService auf
    private String email;
    private Coord location;

    public User(String name, RestaurantService restaurantService, Coord location) {
        userID = UUID.randomUUID();
        this.name = name;
        this.restaurantService = restaurantService;
        this.location = location;
    }

    // Methoden des RestaurantService, die der User aufrufen kann

    public List<Restaurant> search(String eingabe) {
        return restaurantService.search(eingabe);
    }

    // Benötigte Getter und Setter

    public String getName() {
        return name;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public RestaurantService getRestaurantService() {
        return restaurantService;
    }

    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }


    public Coord getLocation() {
        return location;
    }

    public void setLocation(Coord location) {
        this.location = location;
    }
}
