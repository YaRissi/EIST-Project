package de.tum.in.ase.insertteamnamehere.controller;

import de.tum.in.ase.insertteamnamehere.model.Reservation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

public class ReservationController {
    private final WebClient webClient;
    private final List<Reservation> reservationList;

    public ReservationController() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8080/")
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        this.reservationList = new ArrayList<>();
    }

    public void addReservation(){

    }
    public void updateReservation(){

    }
    public void deleteReservation(){

    }
    public void getAllReservations(){

    }
}
