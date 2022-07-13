package de.tum.in.ase.insertteamnamehere.model;

import java.time.LocalTime;
import java.util.List;

public class TimeSlot {
    private LocalTime open;
    private LocalTime closed;

    public TimeSlot(LocalTime open, LocalTime closed) {
        setOpen(open.getHour(), open.getMinute());
        setClosed(closed.getHour(), closed.getMinute());
    }

    public LocalTime getOpen() {
        return open;
    }

    public LocalTime getClosed() {
        return closed;
    }

    public static boolean timeIsValid(int hour, int minute) {
        if (hour > 24 || hour < 0 || (hour == 24 && minute > 0)) {
            throw new IllegalArgumentException("This hour does not exist!");
        }
        if (minute > 60 || minute < 0) {
            throw new IllegalArgumentException("This minute does not exist!");
        }
        return true;
    }

    public void setOpen(int hour, int minute) {
        open = determineTime(hour, minute);
    }

    public void setClosed(int hour, int minute) {
        closed = determineTime(hour, minute);
    }

    private LocalTime determineTime(int hour, int minute) {
        if (timeIsValid(hour, minute)) {
            if (hour == 24 && minute == 0) {
                hour = 0;
            } else if (minute == 60) {
                hour = hour + 1;
                minute = 0;
            }
        }
        return LocalTime.of(hour, minute);
    }

    public boolean isCoinciding(LocalTime start, LocalTime stop, List<TimeSlot> timeSlots) {
        for (int i = 0; i < timeSlots.size(); i++) {
            TimeSlot currentSlot = timeSlots.get(i);
            if (currentSlot.getOpen().equals(start)) {
                return true;
            } else if (currentSlot.getClosed().equals(stop)) {
                return true;
            } else if (currentSlot.getOpen().compareTo(start) < 0 && currentSlot.getClosed().compareTo(stop) > 0) {
                return true;
            } else if (currentSlot.getOpen().compareTo(start) > 0 && currentSlot.getClosed().compareTo(stop) < 0) {
                return true;
            } else if (start.compareTo(currentSlot.getClosed()) < 0 && stop.compareTo(currentSlot.getOpen()) > 0) {
                return true;
            } else if (currentSlot.getOpen().compareTo(stop) < 0 && currentSlot.getClosed().compareTo(start) > 0) {
                return true;
            }
        }
        return false;
    }
}
