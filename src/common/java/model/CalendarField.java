package model;

import java.util.GregorianCalendar;

public class CalendarField {
    private GregorianCalendar date;
    private TimeSlot timeSlot;

    public CalendarField(GregorianCalendar date,TimeSlot timeSlot){
        this.date = date;
        this.timeSlot = timeSlot;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }
}
