package de.tum.in.ase.insertteamnamehere.service;

import de.tum.in.ase.insertteamnamehere.model.Reservation;

import java.util.*;

import org.springframework.stereotype.Service;

@Service
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
            existingReservation.setTable(existingReservation.getTable());
            existingReservation.setNumberOfPeople(existingReservation.getNumberOfPeople());
            existingReservation.setTimeslot(existingReservation.getTimeslot());
            existingReservation.setUser(existingReservation.getUser());
            return existingReservation;
        }


    }

    public void deleteReservation(UUID ID) {
        this.reservations.removeIf(q -> q.getID().equals(ID));


    }

    public List<Reservation> getAllReservations() {
        //
        //return reservations.stream().toList();
        return Collections.unmodifiableList(this.reservations);
    }

}
