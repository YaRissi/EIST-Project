package de.tum.in.ase.insertteamnamehere.model;
import java.util.ArrayList;
import java.util.List;

public class Calendar {
    List<CalendarField> events;

    public Calendar(){
        events = new ArrayList<>();
    }

    public void createNewEvent(Reservation reservation){
        events.add(new CalendarField(reservation.getDate(), reservation.getTimeslot()));
    }

    public List<CalendarField> getEvents() {
        return events;
    }

    public void setEvents(List<CalendarField> events) {
        this.events = events;
    }
}
