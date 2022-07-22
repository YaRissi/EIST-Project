package de.tum.in.ase.insertteamnamehere.util;

import de.tum.in.ase.insertteamnamehere.model.*;
import org.apache.commons.lang3.StringUtils;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;

public class DataProcessing {

    public DataProcessing() {
    }


    public Set<Table> getTablesFromString(String input) throws NumberFormatException {
        if (!input.isBlank()) {
            String[] table = input.split("-");
            Set<Table> tables = new HashSet<>();
            for (String s : table) {
                if (s.contains(" ")) s = s.split(" ")[0];
                Table table1 = new Table(Integer.parseInt(s), null);
                tables.add(table1);
            }
            return tables;
        }
        return null;
    }

    public Set<Table> getTablesFromStringDatabase(String input) throws NumberFormatException {
        if (!input.isBlank()) {
            String[] table = input.split("§");
            Set<Table> tables = new HashSet<>();
            for (String s : table) {
                String[] parts = s.split("-");
                Table table1 = new Table(Integer.parseInt(parts[5]), null);
                table1.setTableID(UUID.fromString(parts[0]+"-"+parts[1]+"-"+parts[2]+"-"+parts[3]+"-"+parts[4]));
                tables.add(table1);
            }
            return tables;
        }
        return null;
    }

    public List<RestaurantType> getRestaurantTypeFromString(String input) {
        List<RestaurantType> restaurantTypes = new ArrayList<>();
        if (input.contains(",")) {
            for (String s : input.split(",")) {
                restaurantTypes.add(RestaurantType.valueOf(s));
            }
        } else {
            if (!input.isBlank()) restaurantTypes.add(RestaurantType.valueOf(input));
        }
        return restaurantTypes;
    }

    public void addAllTimeSlotstoRestaurant(String Monday, String Tuesday, String Wednesday, String Thursday, String Friday
            , String Saturday, String Sunday, Restaurant restaurant) {
        addTimeSlotToRestaurant(Monday, restaurant, DayOfWeek.MONDAY);
        addTimeSlotToRestaurant(Tuesday, restaurant, DayOfWeek.TUESDAY);
        addTimeSlotToRestaurant(Wednesday, restaurant, DayOfWeek.WEDNESDAY);
        addTimeSlotToRestaurant(Thursday, restaurant, DayOfWeek.THURSDAY);
        addTimeSlotToRestaurant(Friday, restaurant, DayOfWeek.FRIDAY);
        addTimeSlotToRestaurant(Saturday, restaurant, DayOfWeek.SATURDAY);
        addTimeSlotToRestaurant(Sunday, restaurant, DayOfWeek.SUNDAY);
    }

    public void addTimeSlotToRestaurant(String input, Restaurant restaurant, DayOfWeek dayOfWeek) {
        if (input.isBlank()) return;
        String[] timeslots = input.split(",");
        List<TimeSlot> endTimesolts = new ArrayList<>();
        for (String t : timeslots) {
            String[] times = t.split("-");
            List<LocalTime> startend = new ArrayList<>();
            for (String s : times) {
                int hour = 0;
                int minute = 0;
                if (s.contains(":")) {
                    hour = Integer.parseInt(s.split(":")[0]);
                    minute = Integer.parseInt(s.split(":")[1]);
                } else {
                    hour = Integer.parseInt(s);
                }
                LocalTime localTime = LocalTime.of(hour, minute);
                startend.add(localTime);
            }
            TimeSlot timeSlot = new TimeSlot(startend.get(0), startend.get(1));
            endTimesolts.add(timeSlot);
        }
        for (TimeSlot timeslot : endTimesolts) {
            restaurant.addOpeningTimes(timeslot.getOpen(), timeslot.getClosed(), dayOfWeek);
        }
    }

    public String convertRestaurtantTyptoString(List<RestaurantType> restaurantTypes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (RestaurantType re : restaurantTypes) {
            stringBuilder.append(re.toString()).append(",");
        }
        return StringUtils.chop(stringBuilder.toString());
    }

    public String convertTablesToString(Set<Table> tableSet) {
        StringBuilder stringBuilder = new StringBuilder();
        if (tableSet != null) {
            for (Table table : tableSet) {
                stringBuilder.append(table.getMaxNumberOfPeople()).append("-");
            }
            return StringUtils.chop(stringBuilder.toString());
        }
        return "";
    }

    public String convertTablesToStringDatabase(Set<Table> tableSet) {
        StringBuilder stringBuilder = new StringBuilder();
        if (tableSet != null) {
            for (Table table : tableSet) {
                stringBuilder.append(table.getTableID().toString()).append("-").append(table.getMaxNumberOfPeople()).append("§");
            }
            return StringUtils.chop(stringBuilder.toString());
        }
        return "";
    }

    public String convertOpeningToString(List<TimeSlot> timeSlots) {
        StringBuilder stringBuilder = new StringBuilder();
        for (TimeSlot timeSlot : timeSlots) {
            stringBuilder.append(timeSlot.getOpen().toString()).append("-").append(timeSlot.getClosed().toString()).append(",");
        }
        return StringUtils.chop(stringBuilder.toString());
    }

    public String convertReviewstoString(List<Review> reviews) {
        if (reviews.isEmpty()) return "";
        StringBuilder stringBuilder = new StringBuilder();
        for (Review review : reviews) {
            stringBuilder.append(review.getNumberOfLikes()).append("~").append(review.getName()).append("~").append(review.getContent()).append("§");
        }
        return StringUtils.chop(stringBuilder.toString());
    }

    public void addAllReviewsToRestaurtant(String input, Restaurant restaurant) {
        List<Review> reviews = new ArrayList<>();
        if (!input.isBlank()) {
            for (String review : input.split("§")) {
                Review review1;
                if (review.split("~").length == 3) {
                    review1 = new Review(review.split("~")[1], review.split("~")[2]);
                    review1.setNumberOfLikes(Integer.parseInt(review.split("~")[0]));
                }else review1 = new Review(review.split("~")[0], review.split("~")[1]);
                reviews.add(review1);
            }
        }
        restaurant.setReviews(reviews);
    }
}
