package model.user;

import model.Reservation;
import model.Restaurant;
import model.RestaurantService;

import java.util.List;

public class User {
    private String name;
    private Reservation reservation;
    private RestaurantService restaurantService; // User ruft Methoden des RestaurantService auf
    private String email;

    public User(String name, RestaurantService restaurantService,String email) {
        this.name = name;
        this.restaurantService = restaurantService;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
