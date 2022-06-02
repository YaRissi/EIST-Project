package model;

public class Reservation {

    private String name;

    private int numberOfPeople;

    private TimeSlot timeslot;

    private Table table;
    public Reservation(String people, TimeSlot timeSlot, Table table,int numberOfPeople) {
        this.name = people;
        timeslot = timeSlot;
        this.table = table;
        this.numberOfPeople = numberOfPeople;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

