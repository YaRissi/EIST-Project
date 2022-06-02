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
        setNumberOfPeople(numberOfPeople);
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        //Ich hab diesen check erstmal in dieser Klasse implementiert
        //Im Nachhinein kann man ihn auch in die Restaurant-Klasse übertragen
        if(numberOfPeople > table.getMaxNumberOfPeople())
            throw new IllegalArgumentException("There are to many people for this Table. Please choose another one or lower the number of people for this table");
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

