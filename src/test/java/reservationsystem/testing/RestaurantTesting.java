package reservationsystem.testing;


import de.tum.in.ase.insertteamnamehere.model.*;
import de.tum.in.ase.insertteamnamehere.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class RestaurantTesting {

    public static Restaurant testRestaurant;
    public static User testUser;

    @BeforeEach
    public void setup() {
        testRestaurant = new Restaurant("Test", "TES", null, "nowhere Lane 11",
                RestaurantType.PIZZA, PriceCategory.EXPENSIVE, null, null);
        testUser = new User("Test User", new RestaurantService());
    }


    //Constructor ****************************************************************************************
    @Test
    public void testConstructorOpeningTimesNull() {
        assertEquals(new ArrayList<ArrayList<TimeSlot>>(7), testRestaurant.getOpeningTimes());
    }

    @Test
    public void testConstructorOpeningTimesSmallerSeven() {
        List<List<TimeSlot>> timeSlots = new ArrayList<>(6);
        List<TimeSlot> timeslot = new ArrayList<>();
        timeslot.add(new TimeSlot(LocalTime.of(9, 15), LocalTime.of(13, 15)));
        for (int i = 0; i < 6; i++) {
            timeSlots.add(timeslot);
        }
        assertThrows(IllegalArgumentException.class, () -> {
            Restaurant restaurant = new Restaurant("Test", "TES", null, "nowhere Lane 11",
                    RestaurantType.PIZZA, PriceCategory.EXPENSIVE, null, timeSlots);
        });
    }

    @Test
    public void testConstructorOpeningTimesBiggerSeven() {
        List<List<TimeSlot>> timeSlots = new ArrayList<>(8);
        List<TimeSlot> timeslot = new ArrayList<>();
        timeslot.add(new TimeSlot(LocalTime.of(10, 15), LocalTime.of(22, 15)));
        for (int i = 0; i < 8; i++) {
            timeSlots.add(timeslot);
        }
        assertThrows(IllegalArgumentException.class, () -> {
            Restaurant restaurant = new Restaurant("Test", "TES", null, "nowhere Lane 11",
                    RestaurantType.PIZZA, PriceCategory.EXPENSIVE, null, timeSlots);
        });
    }


    //reserveTable ****************************************************************************************
    @Test
    public void testReserveTable() {
        UUID id = UUID.randomUUID();
        Table table = new Table(4, id);
        TimeSlot timeSlot = new TimeSlot(LocalTime.of(9, 30), LocalTime.of(10, 30));
        testRestaurant.reserveTable(testUser, table, timeSlot, 4, id, GregorianCalendar.from(ZonedDateTime.of(LocalDate.now(), timeSlot.getOpen(), ZoneId.systemDefault())));
        assertTrue(table.isReserved());
        assertEquals(GregorianCalendar.from(ZonedDateTime.of(LocalDate.now(), timeSlot.getOpen(), ZoneId.systemDefault())), testRestaurant.getReservations().get(0).getDate());
        assertEquals(id, testRestaurant.getReservations().get(0).getID());
        assertEquals(timeSlot, testRestaurant.getReservations().get(0).getTimeslot());
    }

    @Test
    public void testReserveReservedTable() {
        UUID id = UUID.randomUUID();
        Table table = new Table(4, id);
        table.setReserved(true);
        TimeSlot timeSlot = new TimeSlot(LocalTime.of(9, 30), LocalTime.of(10, 30));
        assertThrows(IllegalArgumentException.class, () -> {
            testRestaurant.reserveTable(testUser, table, timeSlot, 4, id, GregorianCalendar.from(ZonedDateTime.of(LocalDate.now(), timeSlot.getOpen(), ZoneId.systemDefault())));
        });
        assertEquals(0, testRestaurant.getReservations().size());
    }

    @Test
    public void testReserveTableTooManyPeople() {
        UUID id = UUID.randomUUID();
        Table table = new Table(5, id);
        TimeSlot timeSlot = new TimeSlot(LocalTime.of(9, 30), LocalTime.of(10, 30));
        assertThrows(IllegalArgumentException.class, () -> {
            testRestaurant.reserveTable(testUser, table, timeSlot, 6, id, GregorianCalendar.from(ZonedDateTime.of(LocalDate.now(), timeSlot.getOpen(), ZoneId.systemDefault())));
        });
        assertEquals(0, testRestaurant.getReservations().size());
    }

    //getAverageRating *************************************************************************************
    @Test
    public void testGetAverageRating() {
        List<Integer> ratings = new ArrayList<>();
        ratings.add(1);
        ratings.add(5);
        ratings.add(4);
        ratings.add(3);
        ratings.add(2);
        ratings.add(1);

        assertEquals(2.6666666666666666666666667, testRestaurant.getAverageRating(ratings), 1e10 - 10);
    }

    @Test
    public void testGetAverageRatingNoRatings() {
        List<Integer> ratings = new ArrayList<>();

        assertEquals(Double.NaN, testRestaurant.getAverageRating(ratings));
    }

    //addRestaurantType *************************************************************************************
    @Test
    public void testAddRestaurantType() {
        testRestaurant.addRestaurantType(RestaurantType.ITALIAN);
        assertEquals(2, testRestaurant.getRestaurantType().size());
    }

    @Test
    public void testAddRestaurantTypeBiggerTwo() {
        testRestaurant.addRestaurantType(RestaurantType.ITALIAN);
        assertThrows(IllegalArgumentException.class, () -> {
            testRestaurant.addRestaurantType(RestaurantType.VEGETARIAN);
        });
        assertEquals(2, testRestaurant.getRestaurantType().size());
    }

    //addOpeningTimes ***************************************************************************************
    @Test
    public void testAddOpeningTimes() {
        TimeSlot timeSlot1 = new TimeSlot(LocalTime.of(12, 0), LocalTime.of(14, 0));
        testRestaurant.addOpeningTimes(LocalTime.of(12, 0), LocalTime.of(14, 0), DayOfWeek.MONDAY);
        testRestaurant.addOpeningTimes(LocalTime.of(15, 0), LocalTime.of(17, 0), DayOfWeek.MONDAY);
        testRestaurant.addOpeningTimes(LocalTime.of(12, 0), LocalTime.of(14, 0), DayOfWeek.THURSDAY);
        testRestaurant.addOpeningTimes(LocalTime.of(12, 0), LocalTime.of(14, 0), DayOfWeek.FRIDAY);
        TimeSlot expected = new TimeSlot(LocalTime.of(12, 0), LocalTime.of(14, 0));

        assertEquals(expected.getOpen(), testRestaurant.getOpeningTimes().get(0).get(0).getOpen());
        assertEquals(LocalTime.of(17, 0), testRestaurant.getOpeningTimes().get(0).get(1).getClosed());
        assertEquals(expected.getClosed(), testRestaurant.getOpeningTimes().get(3).get(0).getClosed());
        assertEquals(expected.getOpen(), testRestaurant.getOpeningTimes().get(4).get(0).getOpen());
        assertEquals(0, testRestaurant.getOpeningTimes().get(2).size());
    }

    @Test
    public void testAddOpeningTimesTimeCoinciding() {
        testRestaurant.addOpeningTimes(LocalTime.of(12, 0), LocalTime.of(14, 0), DayOfWeek.MONDAY);
        testRestaurant.addOpeningTimes(LocalTime.of(13, 0), LocalTime.of(17, 0), DayOfWeek.MONDAY);

        assertEquals(1, testRestaurant.getOpeningTimes().get(0).size());
    }

    //timesCoinciding ***************************************************************************************
    @Test
    public void testTimesCoinciding() {
        testRestaurant.addOpeningTimes(LocalTime.of(12, 0), LocalTime.of(14, 0), DayOfWeek.MONDAY);
        assertTrue(testRestaurant.timesCoinciding(LocalTime.of(13, 0), LocalTime.of(17, 0), DayOfWeek.MONDAY));
    }

    @Test
    public void testTimesCoincidingNoDayOfWeek() {
        testRestaurant.addOpeningTimes(LocalTime.of(12, 0), LocalTime.of(14, 0), DayOfWeek.MONDAY);
        assertThrows(IllegalArgumentException.class, () -> {
            testRestaurant.timesCoinciding(LocalTime.of(10, 0), LocalTime.of(13, 0), null);
        });
    }

    @Test
    public void testTimesCoincidingSameTime() {
        testRestaurant.addOpeningTimes(LocalTime.of(12, 0), LocalTime.of(14, 0), DayOfWeek.MONDAY);
        assertFalse(testRestaurant.timesCoinciding(LocalTime.of(14, 0), LocalTime.of(17, 0), DayOfWeek.MONDAY));
    }

    //addRating **********************************************************************************************
    @Test
    public void testAddRating() {
        testRestaurant.addRating(1);
        assertEquals(1, testRestaurant.getRatings().get(0));
        testRestaurant.addRating(5);
        assertEquals(5, testRestaurant.getRatings().get(1));
    }

    @Test
    public void testAddRatingBiggerFive() {
        assertThrows(IllegalArgumentException.class, () -> {
            testRestaurant.addRating(6);
        });
    }

    @Test
    public void testAddRatingSmallerOne() {
        assertThrows(IllegalArgumentException.class, () -> {
            testRestaurant.addRating(0);
        });
    }
}
