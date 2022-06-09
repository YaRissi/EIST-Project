package model;

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

    private boolean timeIsValid(int hour, int minute) {
        if (hour > 24 || hour < 0) {
            throw new IllegalArgumentException("This hour does not exist!");
        }
        if (minute > 60 || minute < 0) {
            throw new IllegalArgumentException("This minute does not exist!");
        }
        return true;
    }

    public void setOpen(int hour, int minute) {
        if (timeIsValid(hour, minute)) {
            open = LocalTime.of(hour, minute);
        }
    }

    public void setClosed(int hour, int minute) {
        if (timeIsValid(hour, minute)) {
            closed = LocalTime.of(hour, minute);
        }
    }

    public boolean isCoinciding(LocalTime start, LocalTime stop, List<TimeSlot> timeSlots) {
        for (int i = 0; i < timeSlots.size(); i++) {
            TimeSlot currentSlot = timeSlots.get(i);
            if (currentSlot.getOpen().equals(start)) {
                return false;
            } else if (currentSlot.getClosed().equals(stop)) {
                return false;
            } else if (currentSlot.getOpen().compareTo(start) < 0 && currentSlot.getClosed().compareTo(stop) > 0) {
                return false;
            } else if (currentSlot.getOpen().compareTo(start) > 0 && currentSlot.getClosed().compareTo(stop) < 0) {
                return false;
            }
        }
        return true;
    }
}
