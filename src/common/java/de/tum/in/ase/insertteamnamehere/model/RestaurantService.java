package de.tum.in.ase.insertteamnamehere.model;
import de.tum.in.ase.insertteamnamehere.user.User;
import de.tum.in.ase.insertteamnamehere.util.SortingOptions;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class RestaurantService {
    private List<Restaurant> restaurants;
    private User user;

    public RestaurantService(User user){
        restaurants = new ArrayList<>();
        this.user = user;
    }

    public boolean addRestaurant(Restaurant restaurant){
        return restaurants.add(restaurant);
    }

    public boolean deleteRestaurant(Restaurant restaurant){
        return restaurants.remove(restaurant);
    }

    //---------------------------------- Suche nach Restaurants -------------------------------------------

    public List<Restaurant> search(String input){
        String[] searched = input.toLowerCase(Locale.ROOT).split(" ");
        List<RestaurantType> toSearch = new ArrayList<>();
        List<Restaurant> results = new ArrayList<>();
        for(int i = 0; i < searched.length; i++) {
            switch (searched[i]) {
                case "italian" -> toSearch.add(RestaurantType.ITALIAN);
                case "german" -> toSearch.add(RestaurantType.GERMAN);
                case "chinese" -> toSearch.add(RestaurantType.CHINESE);
                case "indian" -> toSearch.add(RestaurantType.INDIAN);
                case "japanese" -> toSearch.add(RestaurantType.JAPANESE);
                case "greek" -> toSearch.add(RestaurantType.GREEK);
                case "thai" -> toSearch.add(RestaurantType.THAI);
                case "turkish" -> toSearch.add(RestaurantType.TURKISH);
                case "mexican" -> toSearch.add(RestaurantType.MEXICAN);
                case "bar" -> toSearch.add(RestaurantType.BAR_RESTAURANT);
                case "cafe" -> toSearch.add(RestaurantType.CAFE);
                case "vegetarian" -> toSearch.add(RestaurantType.VEGETARIAN);
                case "burger" -> toSearch.add(RestaurantType.BURGER);
                case "pizza" -> toSearch.add(RestaurantType.PIZZA);
                case "sushi" -> toSearch.add(RestaurantType.SUSHI_BAR);
                case "noodles" -> toSearch.add(RestaurantType.NOODLE_BAR);
                case "vietnamese" -> toSearch.add(RestaurantType.VIETNAMESE);
            }
        }
        if(toSearch.isEmpty()){
            throw new IllegalArgumentException("Es wurden keine mit deiner Suchanfrage übereinstimmenden Restaurants gefunden.");
        }
        else {
            for (Restaurant r : restaurants) {
                for(RestaurantType tr : r.getRestaurantType()){
                    for(RestaurantType ts : toSearch) {
                        if(tr.equals(ts)){
                            results.add(r);
                        }
                    }
                }
            }
        }
        return results;
    }

    //Location Parameter benötigt
    public List<Restaurant> filterDistance(SortingOptions.SortingOrder sortingOrder, int maxDistance){
        List<Restaurant> resultList = new ArrayList<>();
        for(Restaurant r : restaurants){
            if(r.getDistanceTo(user.getLocation()) <= maxDistance){
                resultList.add(r);
            }
        }
        if(sortingOrder == SortingOptions.SortingOrder.ASCENDING){
            resultList.sort(new Comparator<Restaurant>() {
                @Override
                public int compare(Restaurant r1, Restaurant r2) {
                    return (int)(r1.getDistanceTo(user.getLocation()) - r2.getDistanceTo(user.getLocation()));
                }
            });
        }
        else{
            resultList.sort(new Comparator<Restaurant>() {
                @Override
                public int compare(Restaurant r1, Restaurant r2) {
                    return (int)(r2.getDistanceTo(user.getLocation()) - r1.getDistanceTo(user.getLocation()));
                }
            });
        }
        return resultList;
    }

    public List<Restaurant> filterType(SortingOptions.SortingOrder sortingOrder, List<RestaurantType> types){
        List<Restaurant> resultList = new ArrayList<>();
        for(Restaurant r : restaurants){
            for(RestaurantType t : r.getRestaurantType()){
                for(RestaurantType type : types){
                    if(t.equals(type)){
                        r.incrementCorrespondence();
                    }
                }
            }
            if(r.getCorrespondence() > 0){
                resultList.add(r);
            }
        }
        if(sortingOrder == SortingOptions.SortingOrder.ASCENDING){
            resultList.sort(new Comparator<Restaurant>() {
                @Override
                public int compare(Restaurant r1, Restaurant r2) {
                    return r1.getCorrespondence() - r2.getCorrespondence();
                }
            });
        }
        else{
            resultList.sort(new Comparator<Restaurant>() {
                @Override
                public int compare(Restaurant r1, Restaurant r2) {
                    return r2.getCorrespondence() - r1.getCorrespondence();
                }
            });
        }
        return resultList;
    }

    public List<Restaurant> filterPrize(SortingOptions.SortingOrder sortingOrder, PriceCategory priceCategory){
        List<Restaurant> resultList = new ArrayList<>();
        for(Restaurant r : restaurants){
            if(r.getPriceCategory().equals(priceCategory)){
                resultList.add(r);
            }
        }
        if(sortingOrder == SortingOptions.SortingOrder.ASCENDING){
            resultList.sort(new Comparator<Restaurant>() {
                @Override
                public int compare(Restaurant r1, Restaurant r2) {
                    return r1.getName().compareTo(r2.getName());
                }
            });
        }
        else{
            resultList.sort(new Comparator<Restaurant>() {
                @Override
                public int compare(Restaurant r1, Restaurant r2) {
                    return r2.getName().compareTo(r1.getName());
                }
            });
        }
        return resultList;
    }

    public List<Restaurant> filterRating(SortingOptions.SortingOrder sortingOrder, int minRating){
        List<Restaurant> resultList = new ArrayList<>();
        for(Restaurant r: restaurants){
            if(r.getAverageRating(r.getRatings()) >= minRating){
                resultList.add(r);
            }
        }
        if(sortingOrder == SortingOptions.SortingOrder.ASCENDING){
            resultList.sort(new Comparator<Restaurant>() {
                @Override
                public int compare(Restaurant r1, Restaurant r2) {
                    return (int)(r1.getAverageRating(r1.getRatings()) - r2.getAverageRating(r2.getRatings()));
                }
            });
        }
        else{
            resultList.sort(new Comparator<Restaurant>() {
                @Override
                public int compare(Restaurant r1, Restaurant r2) {
                    return (int)(r2.getAverageRating(r2.getRatings()) - r1.getAverageRating(r1.getRatings()));
                }
            });
        }
        return resultList;
    }

    public List<Restaurant> filterTimeSlots(SortingOptions.SortingOrder sortingOrder, LocalTime start, LocalTime end ){
        List<Restaurant> resultList = new ArrayList<>();
        TimeSlot timeslot = new TimeSlot(start, end);
        for(Restaurant r : restaurants){
            for(List<TimeSlot> a : r.getOpeningTimes()){
                for(TimeSlot t : a){
                    if(t.equals(timeslot)){
                        resultList.add(r);
                    }
                }
            }
        }
        return resultList;
    }

    //---------------------------------- Weitere Funktionen -------------------------------------------



}
