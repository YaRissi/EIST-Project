package de.tum.in.ase.insertteamnamehere.rest;

import de.tum.in.ase.insertteamnamehere.model.Reservation;
import de.tum.in.ase.insertteamnamehere.service.ReservationService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})

public class ReservationResource {
    private final ReservationService reservationService;

    public ReservationResource(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    @GetMapping("reservations")
    public ResponseEntity<List<Reservation>> getAllReservations(@RequestParam("secret") String secret) {
        if (!"SecretKey".equals(secret)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @PostMapping("reservations")
    public ResponseEntity<Reservation> createNote(@RequestBody Reservation reservation) {
        if (reservation.getID() != null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(reservationService.saveReservation(reservation));
    }

    @PutMapping("reservations/{reservationID}")
    public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation updatedReservation, @PathVariable("reservationID") UUID reservationID) {
        if (!updatedReservation.getID().equals(reservationID)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(reservationService.saveReservation(updatedReservation));
    }

    @DeleteMapping("reservations/{noteID}")
    public ResponseEntity<Void> deleteReservation(@PathVariable("reservationID") UUID reservationID) {
        reservationService.deleteReservation(reservationID);
        return ResponseEntity.noContent().build();
    }


}
