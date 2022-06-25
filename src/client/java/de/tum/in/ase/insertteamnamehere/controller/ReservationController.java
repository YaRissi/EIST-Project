package de.tum.in.ase.insertteamnamehere.controller;

import de.tum.in.ase.insertteamnamehere.model.Reservation;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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

    public void addReservation(Reservation r, Consumer<List<Reservation>> reservationConsumer){
        webClient.post()
                .uri("reservation")
                .bodyValue(r)
                .retrieve()
                .bodyToMono(Reservation.class)
                .onErrorStop()
                .subscribe(newReservation -> {
                    reservationList.add(newReservation);
                    reservationConsumer.accept(reservationList);
                });

    }
    public void updateReservation(Reservation r, Consumer<List<Reservation>> reservationConsumer){
        webClient.put()
                .uri("reservation/" + r.getStringId())
                .bodyValue(r)
                .retrieve()
                .bodyToMono(Reservation.class)
                .onErrorStop()
                .subscribe(newReservation -> {
                    reservationList.replaceAll(oldReservation -> oldReservation.getStringId().equals(newReservation.getStringId()) ? newReservation : oldReservation);
                    reservationConsumer.accept(reservationList);
                });

    }
    public void deleteReservation(Reservation r, Consumer<List<Reservation>> reservationConsumer){
        webClient.delete()
                .uri("reservation/" + r.getStringId())
                .retrieve()
                .toBodilessEntity()
                .onErrorStop()
                .subscribe(v -> {
                    reservationList.remove(r);
                    reservationConsumer.accept(reservationList);
                });

    }
    public void getAllReservations(Consumer<List<Reservation>> reservationConsumer){
        webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("reservation")
                        .queryParam("secret", "SecretKey")
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Reservation>>() {})
                .onErrorStop()
                .subscribe(newNotes -> {
                    reservationList.clear();
                    reservationList.addAll(newNotes);
                    reservationConsumer.accept(reservationList);
                });


    }
}
