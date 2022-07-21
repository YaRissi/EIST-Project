package de.tum.in.ase.insertteamnamehere.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class Table {
    private boolean reserved;
    private final UUID tableID;

    private Restaurant restaurant;

    private final int maxNumberOfPeople;

    public Table(int maxNumberOfPeople, Restaurant restaurant) {
        this.maxNumberOfPeople = maxNumberOfPeople;
        this.restaurant = restaurant;
        this.reserved = false;
        this.tableID = UUID.randomUUID();
    }

    public int getMaxNumberOfPeople() {
        return maxNumberOfPeople;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }


    public UUID getTableID() {
        return tableID;
    }


    public boolean addReservation(Reservation reservation) {
        return restaurant.getReservations().add(reservation);
    }
    public boolean deleteReservation(Reservation reservation) {
        return restaurant.getReservations().remove(reservation);
    }

    public List<Reservation> getReservationsForTable() {
        return restaurant.getReservations().stream().filter(r -> r.getTable().getTableID().equals(this.getTableID())).collect(Collectors.toList());
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<TimeSlot> getAvailableTimeSlots(LocalDate date) {
        List<TimeSlot> availableTimes = restaurant.getOpeningTimes().get(date.getDayOfWeek().getValue() - 1);
        List<TimeSlot> reservedTimes = getReservationsForTable().stream().map(r -> r.getTimeslot()).toList();

        for(int i = 0; i < reservedTimes.size(); i++) {

            TimeSlot reserved = reservedTimes.get(i);
            TimeSlot openingTimeSlot = reserved.isInBounds(availableTimes);

            if(openingTimeSlot != null) {

                LocalTime availableStart = openingTimeSlot.getOpen();
                LocalTime reservedStart = reserved.getOpen();

                if(reservedStart.compareTo(availableStart) == 0) {

                    TimeSlot one = new TimeSlot(reservedStart, reserved.getClosed());
                    TimeSlot two = new TimeSlot(reserved.getClosed(), openingTimeSlot.getClosed());

                    availableTimes.remove(openingTimeSlot);
                    availableTimes.add(one);
                    availableTimes.add(two);

                } else if(reservedStart.compareTo(availableStart) > 0) {
                    TimeSlot one = new TimeSlot(availableStart, reservedStart);
                    TimeSlot two = new TimeSlot(reservedStart, reserved.getClosed());
                    TimeSlot three = new TimeSlot(reserved.getClosed(), openingTimeSlot.getClosed());

                    availableTimes.remove(openingTimeSlot);
                    availableTimes.add(one);
                    availableTimes.add(two);
                    availableTimes.add(three);
                }
            }
        }
        return availableTimes;
    }

}
