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

    public Reservation saveReservation(Reservation reservation) {
        var optionalReservation = reservations.stream().filter(existingReservation -> existingReservation.getID().equals(reservation.getID())).findFirst();
        if (optionalReservation.isEmpty()) {
            reservation.setID(UUID.randomUUID());
            reservations.add(reservation);
            return reservation;
        } else {
            var existingReservation = optionalReservation.get();
            //existingReservation.setTable(reservation.setTable());
            return existingReservation;
        }


    }

    public void deleteReservation(UUID ID) {
        this.reservations.removeIf(q -> q.getStringId().equals(ID.toString()));


    }

    public List<Reservation> getAllReservations() {
        return reservations.stream().toList();
    }

}
