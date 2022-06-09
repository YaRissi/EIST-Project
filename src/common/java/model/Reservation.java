package model;

import model.user.User;

public class Reservation {

    private User user;
    private int numberOfPeople;
    private TimeSlot timeslot;
    private Table table;

    public Reservation(User user, TimeSlot timeSlot, Table table, int numberOfPeople) {
        this.user = user;
        timeslot = timeSlot;
        this.table = table;
        this.numberOfPeople = numberOfPeople;
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
}

