package de.tum.in.ase.insertteamnamehere.model;
import de.tum.in.ase.insertteamnamehere.user.User;
import de.tum.in.ase.insertteamnamehere.util.Coord;
import de.tum.in.ase.insertteamnamehere.util.SortingOptions;


import java.awt.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class RestaurantService {
    private List<Restaurant> restaurants = new ArrayList<>();
    private User user;
    private List<Restaurant> restaurantList;

    public RestaurantService(User user){
        //restaurants = new ArrayList<>();
        this.restaurants = initialiseRestaurants();
        this.user = user;
    }
    public List<Restaurant> initialiseRestaurants(){
        Restaurant r1 = new Restaurant
                ("Gate- kitchen",new Coord(1,2),"Lichtenbergstraße 8", RestaurantType.BAR_RESTAURANT, PriceCategory.AVERAGE,null,null);
        addRestaurant(r1);
        addRestaurant(r1);
        Restaurant r2 = new Restaurant
                ("Lokitos", new Coord(2,3),"Sonnenweg 21",RestaurantType.BAR_RESTAURANT, PriceCategory.AVERAGE,null,null);
        addRestaurant(r1);
        Restaurant r3 = new Restaurant
                ("The FACULTY",new Coord(4,2),"Walter- von- Dyck Str 12",RestaurantType.CAFE,PriceCategory.AFFORDABLE,null,null);
        addRestaurant(r3);
        Restaurant r4 = new Restaurant
                ("Cafe Herr Lichtenberg",new Coord(4,4),"Lichtenbergerstr 6",RestaurantType.CAFE, PriceCategory.EXPENSIVE,null,null);
        addRestaurant(r4);
        Restaurant r5 = new Restaurant
                ("Olymps Restaurant", new Coord(5,4),"Wielandstraße 3",RestaurantType.BAR_RESTAURANT,PriceCategory.EXPENSIVE,null,null);
        addRestaurant(r5);
        Restaurant r6 = new Restaurant
                ("Garchinger Augustiner",null,"Freisinger Landstraße 4",RestaurantType.GERMAN,PriceCategory.AVERAGE,null,null);
        addRestaurant(r6);
        Restaurant r7 = new Restaurant
                ("Poseidon",null,"Freisinger Landtraße 3",RestaurantType.BURGER,PriceCategory.EXPENSIVE,null,null);
        addRestaurant(r7);
        Restaurant r8 = new Restaurant
                ("Onkel Luu`s Imbiss", null,"Boltzmannstraße 11",RestaurantType.CAFE,PriceCategory.AVERAGE,null,null);
        addRestaurant(r8);
        Restaurant r9 = new  Restaurant
                ("The lonely Broccoli & Terrasse", null,"Leopoldstraße 170",RestaurantType.VEGETARIAN,PriceCategory.EXPENSIVE,null,null);
        addRestaurant(r9);
        Restaurant r10 = new Restaurant
                ("El Greco", null,"Schleßheimer Str. 17", RestaurantType.GREEK, PriceCategory.AVERAGE, null,null);
        addRestaurant(r10);
        Restaurant r11= new Restaurant
                ("Nano Sushi",null,"Dieselstraße 28",RestaurantType.SUSHI_BAR,PriceCategory.EXPENSIVE,null,null);
        addRestaurant(r11);
        Restaurant r12= new Restaurant
                ("Arabisch Indisches Restaurant Akrams Eching", null,"Obere Hauptstraße 6",RestaurantType.INDIAN, PriceCategory.AVERAGE,null,null);
        addRestaurant(r12);
        Restaurant r13= new Restaurant
                ("Ristorante Pizzeria Da Umberto", null,"Schleißheimerstr. 40",RestaurantType.PIZZA, PriceCategory.AVERAGE,null,null);
        addRestaurant(r13);
        Restaurant r14= new Restaurant
                ("Ristorante Pizzeria Roma", null,"Rathausplatz 7",RestaurantType.PIZZA, PriceCategory.AFFORDABLE,null,null);
        addRestaurant(r14);
        Restaurant r15= new Restaurant
                ("Rabiang Thai Restaurant",null,"Georgenschwaigstrße 25",RestaurantType.THAI,PriceCategory.AVERAGE,null,null);
        addRestaurant(r15);
        Restaurant r16= new Restaurant
                ("La Boheme Schwabing",null,"Leopoldstraße 180",RestaurantType.BAR_RESTAURANT,PriceCategory.EXPENSIVE,null,null);
        addRestaurant(r16);
        Restaurant r17= new Restaurant(
                "Hans im Glück",null,"Leopoldstraße 250", RestaurantType.BURGER,PriceCategory.AVERAGE, null,null);
        addRestaurant(r17);
        Restaurant r18= new Restaurant
                ("Longgrain Thai Cuisine", null,"Belgradstraße 45", RestaurantType.THAI,PriceCategory.AFFORDABLE,null,null);
        addRestaurant(r18);
        Restaurant r19= new Restaurant
                ("Trattoria La Piazza",null,"Kölner Platz 7",RestaurantType.ITALIAN, PriceCategory.EXPENSIVE,null,null);
        addRestaurant(r19);
        Restaurant r20= new Restaurant
                ("Korfu bei Dimi", null,"Lerchenauerstr 14", RestaurantType.GREEK,PriceCategory.AVERAGE, null,null);
        addRestaurant(r20);
        return restaurants;
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

        if (types.isEmpty()) {
            return restaurants;
        }
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

    public List<Restaurant> filterPrize(List<Restaurant> listOfRestaurants, SortingOptions.SortingOrder sortingOrder, PriceCategory priceCategory){
        if (listOfRestaurants.isEmpty()) {
            listOfRestaurants.addAll(restaurants);
        }
        if (priceCategory == null) {
            return listOfRestaurants;
        }
        List<Restaurant> resultList = new ArrayList<>();
        for(Restaurant r : listOfRestaurants){
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

    public List<Restaurant> filterRating(List<Restaurant> listOfRestaurants, SortingOptions.SortingOrder sortingOrder, int minRating){
        if (listOfRestaurants.isEmpty()) {
            listOfRestaurants.addAll(restaurants);
        }
        if (minRating == 0) {
            return listOfRestaurants;
        }
        List<Restaurant> resultList = new ArrayList<>();
        for(Restaurant r: listOfRestaurants){
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

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}
