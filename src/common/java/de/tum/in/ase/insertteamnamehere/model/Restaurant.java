package de.tum.in.ase.insertteamnamehere.model;

import de.tum.in.ase.insertteamnamehere.user.User;
import de.tum.in.ase.insertteamnamehere.util.Coord;

import java.time.*;
import java.util.*;
import java.util.List;

public class Restaurant {
    public boolean getID;
    private UUID restaurantID;
    private String name;
    private List<RestaurantType> restaurantType;
    private Set<Table> tables;
    private Coord location;
    private String address;
    private List<Review> reviews;
    private List<Integer> ratings;
    private PriceCategory priceCategory;
    private List<List<TimeSlot>> openingTimes;
    private String website;
    private List<Reservation> reservations;
    private boolean open;
    private float averageRating;

    private int correspondence;
    //private int averageRating;

    public Restaurant(String name, Coord location, String address, RestaurantType restaurantType, PriceCategory priceCategory, Set<Table> tables, List<List<TimeSlot>> openingTimes) {
        this.name = name;
        this.restaurantID = UUID.randomUUID();
        this.location = location;
        this.address = address;
        this.restaurantType = new ArrayList<>();
        this.restaurantType.add(restaurantType);
        this.priceCategory = priceCategory;
        this.tables = tables;
        this.reviews = new ArrayList<>();
        this.ratings = new ArrayList<>();
        if (openingTimes == null) {
            this.openingTimes = new ArrayList<>(7);
        } else if (openingTimes.size() != 7) {
            throw new IllegalArgumentException("There are 7 days in a week!");
        } else {
            this.openingTimes = openingTimes;
        }
        reservations = new ArrayList<>();
    }

    public void reserveTable(User user, Table table, TimeSlot timeSlot, int NumberOfPeople, UUID ID, GregorianCalendar date) {
        if (table.isReserved()) {
            throw new IllegalArgumentException("This table is already reserved, please pick another!");
        }else if(NumberOfPeople > table.getMaxNumberOfPeople())
            throw new IllegalArgumentException("There are to many people for this Table. Please choose another one or lower the number of people for this table");
        else {
            table.setReserved(true);
            addReservation(new Reservation(user,timeSlot,table,NumberOfPeople, ID, date));
        }
    }

    public float getAverageRating(List<Integer> ratings) {
        int size = ratings.size();
        if (size == 0) {
            return averageRating;
        }
        double allRatings = ratings.stream().reduce(Integer::sum).get();
        if(!Float.isNaN(averageRating)) averageRating= (float) ((averageRating + (allRatings / size)) / 2);
        return averageRating;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RestaurantType> getRestaurantType() {
        return restaurantType;
    }

    public void setRestaurantType(List<RestaurantType> restaurantType) {
        this.restaurantType = restaurantType;
    }

    public void addRestaurantType(RestaurantType restaurantType) {
        if (this.restaurantType.size() >= 2) {
            throw new IllegalArgumentException("You cannot set more than two restaurant types!");
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

    public Coord getLocation() {
        return location;
    }

    public void setLocation(Coord location) {
        this.location = location;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void addReview(Review review) {
        reviews.add(0, review);
    }

    public List<Integer> getRatings() {
        return ratings;
    }

    public void setRatings(List<Integer> ratings) {
        this.ratings = ratings;
    }

    public void addRating(Integer rating) {
        if (rating > 5 || rating < 1) {
            throw new IllegalArgumentException("Ratings must be between 1 and 5!");
        }
        ratings.add(rating);
    }

    public PriceCategory getPriceCategory() {
        return priceCategory;
    }

    public void setPriceCategory(PriceCategory priceCategory) {
        this.priceCategory = priceCategory;
    }

    public List<List<TimeSlot>> getOpeningTimes() {
        return openingTimes;
    }

    public void setOpeningTimes(List<List<TimeSlot>> openingTimes) {
        this.openingTimes = openingTimes;
    }

    public void addOpeningTimes(LocalTime start, LocalTime stop, DayOfWeek dayOfWeek) {
        if (openingTimes.size() < 7) {
            for (int i = 0; i < 7; i++) {
                try {
                    if (openingTimes.get(i) == null) {
                        openingTimes.add(i, new ArrayList<>());
                    }
                } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    openingTimes.add(i, new ArrayList<>());
                }
            }
        }
        switch (dayOfWeek) {
            case MONDAY -> {
                if (!timesCoinciding(start, stop, dayOfWeek)) {
                    openingTimes.get(0).add(new TimeSlot(start, stop));
                }
            }
            case TUESDAY -> {
                if (!timesCoinciding(start, stop, dayOfWeek)) {
                    openingTimes.get(1).add(new TimeSlot(start, stop));
                }
            }
            case WEDNESDAY -> {
                if (!timesCoinciding(start, stop, dayOfWeek)) {
                    openingTimes.get(2).add(new TimeSlot(start, stop));
                }
            }
            case THURSDAY -> {
                if (!timesCoinciding(start, stop, dayOfWeek)) {
                    openingTimes.get(3).add(new TimeSlot(start, stop));
                }
            }
            case FRIDAY -> {
                if (!timesCoinciding(start, stop, dayOfWeek)) {
                    openingTimes.get(4).add(new TimeSlot(start, stop));
                }
            }
            case SATURDAY -> {
                if (!timesCoinciding(start, stop, dayOfWeek)) {
                    openingTimes.get(5).add(new TimeSlot(start, stop));
                }
            }
            case SUNDAY -> {
                if (!timesCoinciding(start, stop, dayOfWeek)) {
                    openingTimes.get(6).add(new TimeSlot(start, stop));
                }
            }
        }
    }

    public boolean timesCoinciding(LocalTime start, LocalTime stop, DayOfWeek dayOfWeek) {
        if (dayOfWeek == null) {
            throw new IllegalArgumentException("Please choose a day of the week.");
        }
        switch (dayOfWeek) {
            case MONDAY -> {
                return openingTimes.get(0).stream().anyMatch(timeSlot -> timeSlot.isCoinciding(start, stop, openingTimes.get(0)));
            }
            case TUESDAY -> {
                return openingTimes.get(1).stream().anyMatch(timeSlot -> timeSlot.isCoinciding(start, stop, openingTimes.get(1)));
            }
            case WEDNESDAY -> {
                return openingTimes.get(2).stream().anyMatch(timeSlot -> timeSlot.isCoinciding(start, stop, openingTimes.get(2)));
            }
            case THURSDAY -> {
                return openingTimes.get(3).stream().anyMatch(timeSlot -> timeSlot.isCoinciding(start, stop, openingTimes.get(3)));
            }
            case FRIDAY -> {
                return openingTimes.get(4).stream().anyMatch(timeSlot -> timeSlot.isCoinciding(start, stop, openingTimes.get(4)));
            }
            case SATURDAY -> {
                return openingTimes.get(5).stream().anyMatch(timeSlot -> timeSlot.isCoinciding(start, stop, openingTimes.get(5)));
            }
            case SUNDAY -> {
                return openingTimes.get(6).stream().anyMatch(timeSlot -> timeSlot.isCoinciding(start, stop, openingTimes.get(6)));
            }
            default -> throw new IllegalArgumentException("Please choose a valid day of the week!");
        }
    }

    public double getDistanceTo(Coord p2){
        var earthRadius = 6271;
        var dLat = this.location.getLat() - p2.getLat();
        var dLon = this.location.getLon() - p2.getLon();
        var a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(this.location.getLat()*(Math.PI/180)) * Math.cos(p2.getLat() * (Math.PI/180)) *
                        Math.sin(dLon/2) * Math.sin(dLon/2);
        var c = 2 * Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
        var distance = earthRadius * c;
        return distance;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRestaurantID(UUID ID) {
        this.restaurantID = ID;
    }


    public UUID getRestaurantID() {
        return restaurantID;
    }

    public void incrementCorrespondence() {
        this.correspondence++;
    }

    public int getCorrespondence() {
        return correspondence;
    }

    /*public void setAverageRating(int averageRating) {
        this.averageRating = averageRating;
    }*/

    public float getAverageRating() {
        return getAverageRating(ratings);
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

}
