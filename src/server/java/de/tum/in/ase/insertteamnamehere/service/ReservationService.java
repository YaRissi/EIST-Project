package de.tum.in.ase.insertteamnamehere.service;

import de.tum.in.ase.insertteamnamehere.model.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReservationService {

    private final List<Reservation> reservations;

    public ReservationService() {
        this.reservations = new ArrayList<>();
    }

    public Reservation saveReservation(Reservation reservation){
        return null;
    }
    public Reservation deleteReservation(UUID ID){
        return null;

    }

    public List<Reservation> getAllReservations(){
        return null;
    }

}
