package de.tum.in.ase.insertteamnamehere.util;

import de.tum.in.ase.insertteamnamehere.model.*;

import java.util.*;

public class RestaurantFactory {

    public static List<Restaurant> createRandomRestaurants() {
        int min = 16;
        int max = 49;
        Random random = new Random();
        int numberOfRestaurants = random.nextInt(max + min) + min;
        List<Restaurant> dummyList = new ArrayList<>();
        for (int i = 0; i < numberOfRestaurants; i++) {
            List<RestaurantType> restaurantTypes = createRandomRestaurantType();
            Restaurant dummy = new Restaurant(createRandomRestaurantName(restaurantTypes), null, "" + i, null, createRandomPriceCategory(), createRandomTables(), null);
            dummy.setRatings(createRandomRating());
            dummy.setAverageRating(dummy.getAverageRating(dummy.getRatings()));
            dummy.setRestaurantType(restaurantTypes);
            dummy.setReviews(createRandomReviews());
            dummyList.add(dummy);
        }
        return dummyList;
    }
    private static List<RestaurantType> createRandomRestaurantType() {
        int min1 = 1;
        int max1 = 1;
        Random random1 = new Random();
        int numberOfRestaurantTypes = random1.nextInt(max1 + min1) + min1;
        List<RestaurantType> listOfRestaurantTypes = new ArrayList<>();
        for (int i = 0; i < numberOfRestaurantTypes; i++) {
            int min2 = 1;
            int max2 = 16;
            Random random2 = new Random();
            int restaurantType = random2.nextInt(max2 + min2) + min2;
            switch (restaurantType) {
                case 1 -> listOfRestaurantTypes.add(RestaurantType.ITALIAN);
                case 2 -> listOfRestaurantTypes.add(RestaurantType.GERMAN);
                case 3 -> listOfRestaurantTypes.add(RestaurantType.CHINESE);
                case 4 -> listOfRestaurantTypes.add(RestaurantType.INDIAN);
                case 5 -> listOfRestaurantTypes.add(RestaurantType.JAPANESE);
                case 6 -> listOfRestaurantTypes.add(RestaurantType.GREEK);
                case 7 -> listOfRestaurantTypes.add(RestaurantType.THAI);
                case 8 -> listOfRestaurantTypes.add(RestaurantType.TURKISH);
                case 9 -> listOfRestaurantTypes.add(RestaurantType.MEXICAN);
                case 10 -> listOfRestaurantTypes.add(RestaurantType.BAR_RESTAURANT);
                case 11 -> listOfRestaurantTypes.add(RestaurantType.CAFE);
                case 12 -> listOfRestaurantTypes.add(RestaurantType.VEGETARIAN);
                case 13 -> listOfRestaurantTypes.add(RestaurantType.BURGER);
                case 14 -> listOfRestaurantTypes.add(RestaurantType.PIZZA);
                case 15 -> listOfRestaurantTypes.add(RestaurantType.SUSHI_BAR);
                case 16 -> listOfRestaurantTypes.add(RestaurantType.NOODLE_BAR);
                case 17 -> listOfRestaurantTypes.add(RestaurantType.VIETNAMESE);
                default -> {
                }
            }
        }
        return listOfRestaurantTypes;
    }

    private static List<Integer> createRandomRating() {
        int min = 1;
        int max = 4;
        List<Integer> ratings = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            ratings.add(random.nextInt(max + min) + min);
        }
        return ratings;
    }

    private static PriceCategory createRandomPriceCategory() {
        int min = 1;
        int max = 2;
        Random random = new Random();
        int priceCategory = random.nextInt(max + min) + min;
        switch (priceCategory) {
            case 1 -> {
                return PriceCategory.AFFORDABLE;
            }
            case 2 -> {
                return PriceCategory.AVERAGE;
            }
            case 3 -> {
                return PriceCategory.EXPENSIVE;
            }
            default -> {
                return null;
            }
        }
    }

    private static String createRandomRestaurantName(List<RestaurantType> restaurantTypes) {
        StringBuilder name = new StringBuilder();
        for (RestaurantType restaurantType : restaurantTypes) {
            switch (restaurantType) {
                case ITALIAN -> name.append("Italian ");
                case GERMAN -> name.append("German ");
                case CAFE -> name.append("Cafe ");
                case CHINESE -> name.append("Chinese ");
                case INDIAN -> name.append("Indian ");
                case JAPANESE -> name.append("Japanese ");
                case GREEK -> name.append("Greek ");
                case THAI -> name.append("Thai ");
                case TURKISH -> name.append("Turkish ");
                case MEXICAN -> name.append("Mexican ");
                case BAR_RESTAURANT -> name.append("Bar ");
                case VEGETARIAN -> name.append("Vegetarian ");
                case BURGER -> name.append("Burger ");
                case PIZZA -> name.append("Pizza ");
                case SUSHI_BAR -> name.append("Sushi Bar ");
                case NOODLE_BAR -> name.append("Noodle Bar ");
                case VIETNAMESE -> name.append("Vietnamese ");
                default -> name.append("");
            }
        }
        return name + "Restaurant";
    }

    private static Set<Table> createRandomTables() {
        int min = 1;
        int max = 10;
        Random random = new Random();
        int numberOfTables = random.nextInt(max + min) + min;
        Set<Table> listOfTables = new HashSet<>();
        for (int i = 0; i < numberOfTables; i++) {
            listOfTables.add(new Table(random.nextInt(max + min) + min, createRandomRestaurants().get(0)));
        }
        return listOfTables;
    }

    private static List<Review> createRandomReviews() {
        List<Review> reviews = new ArrayList<>();
        int min = 1;
        int max = 5;
        Random random = new Random();
        int numberOfReviews = random.nextInt(max + min) + min;

        for(int i = 0; i<numberOfReviews; i++) {
            reviews.add(new Review(createRandomName(), createRandomContent()));
        }
        return reviews;
    }

    private static String createRandomName() {
        int min = 1;
        int max = 5;
        Random random = new Random();
        int randomName = random.nextInt(max + min) + min;
        switch (randomName) {
            case 1 -> {
                return "Bob der Baumeister";
            }
            case 2 -> {
                return "Mickey Mouse";
            }
            case 3 -> {
                return "The Power Rangers";
            }
            case 4 -> {
                return "Donald Duck";
            }
            default -> {
                return "EIST";
            }
        }
    }

    private static String createRandomContent() {
        int min = 1;
        int max = 6;
        Random random = new Random();
        int randomName = random.nextInt(max + min) + min;
        switch (randomName) {
            case 1 -> {
                return "War super lecker!";
            }
            case 2 -> {
                return "Würde ich jedem empfehlen!";
            }
            case 3 -> {
                return "War ein bisschen zu salzig...";
            }
            case 4 -> {
                return "Die Ente war gut c:";
            }
            case 5 -> {
                return "Wursti die Wurst XD";
            }
            default -> {
                return "EIST ist ein tolles Fach!";
            }
        }
    }
}
