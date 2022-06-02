package model;

import java.awt.*;
import java.sql.Time;
import java.time.*;
import java.util.*;
import java.util.List;

public class Restaurant {
    private List<RestaurantType> restaurantType;
    private Set<Table> tables;
    private Point location;
    private List<String> reviews;
    private List<Integer> ratings;
    private PriceCategory priceCategory;
    private List<TimeSlot>[] openingTimes; //openingTimes[0] = opening times on Monday, e.g. 11-14 & 17-21
    private String website;
    private Collection<Reservation> reservations;
    private boolean open;

    public Restaurant(Point location, RestaurantType restaurantType, PriceCategory priceCategory, Set<Table> tables, List<TimeSlot>[] openingTimes) {
        this.location = location;
        this.restaurantType = new ArrayList<>();
        this.restaurantType.add(restaurantType);
        this.priceCategory = priceCategory;
        this.tables = tables;
        if (openingTimes.length != 7) {
            throw new IllegalArgumentException("There are 7 days in a week!");
        } else {
            this.openingTimes = openingTimes;
        }
        reservations = new ArrayList<>();

    }

    public void reserveTable(String name, Table table, TimeSlot timeSlot, int NumberOfPeople) {
        if (table.isReserved()) {
            throw new IllegalArgumentException("This table is already reserved, please pick another!");
        }else if(NumberOfPeople > table.getMaxNumberOfPeople())
            throw new IllegalArgumentException("There are to many people for this Table. Please choose another one or lower the number of people for this table");
        else {
            table.setReserved(true);
            addReservation(new Reservation(name,timeSlot,table,NumberOfPeople));
        }
    }

    public Integer getAverageRating(List<Integer> ratings) {
        int size = ratings.size();
        if (size == 0) {
            return 0;
        }
        int allRatings = ratings.stream().reduce(Integer::sum).get();
        return allRatings / size;
    }

    public List<RestaurantType> getRestaurantType() {
        return restaurantType;
    }

    public void setRestaurantType(List<RestaurantType> restaurantType) {
        this.restaurantType = restaurantType;
    }

    public void addRestaurantType(RestaurantType restaurantType) {
        if (this.restaurantType.size() > 3) {
            throw new IllegalArgumentException("You cannot set more than three restaurant types!");
        } else {
            this.restaurantType.add(restaurantType);
        }
    }

    public Set<Table> getTables() {
        return tables;
    }

    public void setTables(Set<Table> tables) {
        this.tables = tables;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public List<String> getReviews() {
        return reviews;
    }

    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
    }

    public void addReview(String review) {
        reviews.add(review);
    }

    public List<Integer> getRatings() {
        return ratings;
    }

    public void setRatings(List<Integer> ratings) {
        this.ratings = ratings;
    }

    public void addRating(Integer rating) {
        ratings.add(rating);
    }

    public PriceCategory getPriceCategory() {
        return priceCategory;
    }

    public void setPriceCategory(PriceCategory priceCategory) {
        this.priceCategory = priceCategory;
    }

    public List<TimeSlot>[] getOpeningTimes() {
        return openingTimes;
    }

    public void setOpeningTimes(List<TimeSlot>[] openingTimes) {
        this.openingTimes = openingTimes;
    }

    public void addOpeningTimes(LocalTime start, LocalTime stop, DayOfWeek dayOfWeek) {
        for (int i = 0; i < 7; i++) {
            if (openingTimes[i] == null) {
                openingTimes[i] = new ArrayList<>();
            }
        }
        switch (dayOfWeek) {
            case MONDAY -> openingTimes[0].add(new TimeSlot(start, stop));
            case TUESDAY -> openingTimes[1].add(new TimeSlot(start, stop));
            case WEDNESDAY -> openingTimes[2].add(new TimeSlot(start, stop));
            case THURSDAY -> openingTimes[3].add(new TimeSlot(start, stop));
            case FRIDAY -> openingTimes[4].add(new TimeSlot(start, stop));
            case SATURDAY -> openingTimes[5].add(new TimeSlot(start, stop));
            case SUNDAY -> openingTimes[6].add(new TimeSlot(start, stop));
        }
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Collection<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Collection<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
