package de.tum.in.ase.insertteamnamehere.model;

import de.tum.in.ase.insertteamnamehere.util.SortingOptions;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class RestaurantService {
    private List<Restaurant> restaurants;

    public RestaurantService(){
        restaurants = new ArrayList<>();
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
            }
        }
        if(toSearch.isEmpty()){
            throw new IllegalArgumentException("Es wurden keine mit deiner Suchanfrage übereinstimmenden Restaurants gefunden.");
        }
        else {
            for (Restaurant r : restaurants) {
                if (r.getRestaurantType().equals(toSearch)) {
                    results.add(r);
                }
            }
        }
        return results;
    }

    private void filterControl(SortingOptions sortingOptions, int maxDistance, int maxPrice){
        switch (sortingOptions.getSortField()) {
            case RESTAURANT_TYPE -> filterType(sortingOptions.getSortingOrder());
            case DISTANCE -> filterDistance(sortingOptions.getSortingOrder(), maxDistance);
            case PRIZE_CATEGORY -> filterPrize(sortingOptions.getSortingOrder(), maxPrice);
            case AVERAGE_RATING -> filterRating(sortingOptions.getSortingOrder());
            case FREE_TIME_SLOTS -> filterTimeSlots(sortingOptions.getSortingOrder());
        }
    }

    //Location Parameter benötigt
    public List<Restaurant> filterDistance(SortingOptions.SortingOrder sortingOrder, int maxDistance){
        List<Restaurant> resultList = new ArrayList<>();
        for(Restaurant r : restaurants){
            if(r.getDistanceToUser() <= maxDistance){
                resultList.add(r);
            }
        }
        if(sortingOrder == SortingOptions.SortingOrder.ASCENDING){
            resultList.sort(new Comparator<Restaurant>() {
                @Override
                public int compare(Restaurant r1, Restaurant r2) {
                    return r1.getDistanceToUser() - r2.getDistanceToUser();
                }
            });
        }
        else{
            resultList.sort(new Comparator<Restaurant>() {
                @Override
                public int compare(Restaurant r1, Restaurant r2) {
                    return r2.getDistanceToUser() - r1.getDistanceToUser();
                }
            });
        }
        return resultList;
    }

    public List<Restaurant> filterType(SortingOptions.SortingOrder sortingOrder){
        return null;
    }

    public List<Restaurant> filterPrize(SortingOptions.SortingOrder sortingOrder, int maxPrice){
        return null;
    }

    public List<Restaurant> filterRating(SortingOptions.SortingOrder sortingOrder){
        return null;
    }

    public List<Restaurant> filterTimeSlots(SortingOptions.SortingOrder sortingOrder){
        return null;
    }

    //---------------------------------- Weitere Funktionen -------------------------------------------



}
