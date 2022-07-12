package de.tum.in.ase.insertteamnamehere.model;

import de.tum.in.ase.insertteamnamehere.user.User;

import java.util.GregorianCalendar;
import java.util.UUID;

public class Reservation {

    private User user;
    private int numberOfPeople;
    private TimeSlot timeslot;
    private Table table;
    private UUID ID;
    private GregorianCalendar date;

    public Reservation(User user, TimeSlot timeSlot, Table table, int numberOfPeople, UUID ID, GregorianCalendar date) {
        this.user = user;
        timeslot = timeSlot;
        this.table = table;
        this.numberOfPeople = numberOfPeople;
        this.ID = ID;
        this.date = date;
    }

    // Weitere Methoden


    // Benötige Getter und Setter

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TimeSlot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(TimeSlot timeslot) {
        this.timeslot = timeslot;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public UUID getID() {
        return ID;
    }

    public void setID(UUID ID) {
        this.ID = ID;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public String getStringId() {
        return ID.toString();
    }
}

