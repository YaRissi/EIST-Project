package reservationsystem.testing;

import model.TimeSlot;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TimeSlotTesting {

    //timeIsValid ************************************************************************************************
    @Test
    public void testTimeIsValidValidTime() {
        assertTrue(TimeSlot.timeIsValid(10, 60));
    }

    @Test
    public void testTimeIsValidInvalidHour() {
        assertThrows(IllegalArgumentException.class, () -> {
            TimeSlot.timeIsValid(25, 20);
        });
    }

    @Test
    public void testTimeIsValidInvalidMinute() {
        assertThrows(IllegalArgumentException.class, () -> {
            TimeSlot.timeIsValid(12, -1);
        });
    }

    @Test
    public void testTimeIsValidZeroOClock() {
        assertTrue(TimeSlot.timeIsValid(24, 0));
    }

    @Test
    public void testTimeIsValid24AfterMidnight() {
        assertThrows(IllegalArgumentException.class, () -> {
            TimeSlot.timeIsValid(24, 1);
        });
    }

    //determineTime *********************************************************************************************
    @Test
    public void testDetermineTimeHour24() {
        TimeSlot timeSlot = new TimeSlot(LocalTime.of(20, 30), LocalTime.of(23, 15));
        timeSlot.setClosed(24, 0);
        assertEquals(LocalTime.of(0, 0), timeSlot.getOpen());
    }

    @Test
    public void testDetermineTimeMinute60() {
        TimeSlot timeSlot = new TimeSlot(LocalTime.of(20, 30), LocalTime.of(23, 15));
        timeSlot.setOpen(19, 60);
        assertEquals(LocalTime.of(20, 0), timeSlot.getOpen());
    }

    @Test
    public void testDetermineTimeNormal() {
        TimeSlot timeSlot = new TimeSlot(LocalTime.of(20, 30), LocalTime.of(23, 15));
        timeSlot.setOpen(19, 45);
        assertEquals(LocalTime.of(19, 45), timeSlot.getOpen());
    }

    //isCoinciding **********************************************************************************************

    @Test
    public void testIsCoincidingFalse() {
        LocalTime start = LocalTime.of(15, 0);
        LocalTime stop = LocalTime.of(21, 0);

        LocalTime start1 = LocalTime.of(22, 0);
        LocalTime stop1 = LocalTime.of(23, 0);

        TimeSlot timeSlot = new TimeSlot(start, stop);
        List<TimeSlot> list = new ArrayList<>();
        list.add(timeSlot);

        assertFalse(timeSlot.isCoinciding(start1, stop1, list));
    }

    @Test
    public void testIsCoincidingTrue() {
        LocalTime start = LocalTime.of(15, 0);
        LocalTime stop = LocalTime.of(21, 0);

        LocalTime start1 = LocalTime.of(16, 0);
        LocalTime stop1 = LocalTime.of(23, 0);

        TimeSlot timeSlot = new TimeSlot(start, stop);
        List<TimeSlot> list = new ArrayList<>();
        list.add(timeSlot);

        assertTrue(timeSlot.isCoinciding(start1, stop1, list));
    }

    @Test
    public void testIsCoincidingEdge() {
        LocalTime start = LocalTime.of(15, 0);
        LocalTime stop = LocalTime.of(21, 0);

        LocalTime start1 = LocalTime.of(21, 0);
        LocalTime stop1 = LocalTime.of(23, 0);

        TimeSlot timeSlot = new TimeSlot(start, stop);
        List<TimeSlot> list = new ArrayList<>();
        list.add(timeSlot);

        assertTrue(timeSlot.isCoinciding(start1, stop1, list));
    }
}
