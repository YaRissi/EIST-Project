package de.tum.in.ase.insertteamnamehere.util;

import de.tum.in.ase.insertteamnamehere.model.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.*;

public class JSONParse {
    private JSONObject restaurantObject = new JSONObject();
    private final DataProcessing dataProcessing = new DataProcessing();

    public JSONParse() {
        readJson();
    }

    public List<TimeSlot> getTimes(Restaurant restaurant, int index) {
        List<TimeSlot> timeSlots = new ArrayList<>();
        int size = restaurant.getOpeningTimes().size();
        if (size != 0 && size > index) {
            return restaurant.getOpeningTimes().get(index);
        }
        return timeSlots;
    }


    public void writeJson(Restaurant restaurant) {
        JSONObject restaurantDetails = new JSONObject();
        restaurantDetails.put("Name", restaurant.getName());
        String x = "";
        String y = "";
        if (restaurant.getLocation() != null) {
            x = String.valueOf(restaurant.getLocation().getLat());
            y = String.valueOf(restaurant.getLocation().getLon());
        }
        restaurantDetails.put("x-postion", x);
        restaurantDetails.put("y-postion", y);
        restaurantDetails.put("adress", String.valueOf(restaurant.getAddress()));
        restaurantDetails.put("RestaurantType", dataProcessing.convertRestaurtantTyptoString(restaurant.getRestaurantType()));
        restaurantDetails.put("PriceCategory", restaurant.getPriceCategory().toString());
        restaurantDetails.put("Tables", dataProcessing.convertTablesToStringDatabase(restaurant.getTables()));
        restaurantDetails.put("Monday", dataProcessing.convertOpeningToString(getTimes(restaurant, 0)));
        restaurantDetails.put("Tuesday", dataProcessing.convertOpeningToString(getTimes(restaurant, 1)));
        restaurantDetails.put("Wednesday", dataProcessing.convertOpeningToString(getTimes(restaurant, 2)));
        restaurantDetails.put("Thursday", dataProcessing.convertOpeningToString(getTimes(restaurant, 3)));
        restaurantDetails.put("Friday", dataProcessing.convertOpeningToString(getTimes(restaurant, 4)));
        restaurantDetails.put("Saturday", dataProcessing.convertOpeningToString(getTimes(restaurant, 5)));
        restaurantDetails.put("Sunday", dataProcessing.convertOpeningToString(getTimes(restaurant, 6)));
        restaurantDetails.put("Rating", String.valueOf(restaurant.getAverageRating()));
        String website = restaurant.getWebsite();
        if(website == null) website = "";
        restaurantDetails.put("Reviews", dataProcessing.convertReviewstoString(restaurant.getReviews()));
        restaurantDetails.put("Website", website);

        //System.out.println(restaurant.getOpeningTimes().get(6));


        restaurantObject.put(restaurant.getRestaurantID().toString(), restaurantDetails);

        try (FileWriter file = new FileWriter("src/client/resources/data/database.json")) {
            file.write(restaurantObject.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getRestaurantObject() {
        return restaurantObject;
    }

    public void setRestaurantObject(JSONObject restaurantObject) {
        this.restaurantObject = restaurantObject;
    }

    public void readJson() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src/client/resources/data/database.json"));

            JSONObject jsonObject = (JSONObject) obj;
            restaurantObject.clear();

            for (Object key : jsonObject.keySet()) {
                restaurantObject.put(key.toString(), jsonObject.get(key));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void clearJson() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("src/client/resources/data/database.json");
        writer.print("{}");
        writer.close();
        restaurantObject.clear();
    }

    public List<Restaurant> parseRestaurant() {
        List<Restaurant> restaurantList = new ArrayList<>();
        for (Object key : restaurantObject.keySet()) {
            UUID id = UUID.fromString(key.toString());
            JSONObject jsonObject = (JSONObject) restaurantObject.get(key);
            String name = jsonObject.get("Name").toString();
            String xpostion = jsonObject.get("x-postion").toString();
            String ypostion = jsonObject.get("y-postion").toString();
            String adress = jsonObject.get("adress").toString();
            String restaurantType = jsonObject.get("RestaurantType").toString();
            String priceCategory = jsonObject.get("PriceCategory").toString();
            String Tables = jsonObject.get("Tables").toString();
            String Monday = jsonObject.get("Monday").toString();
            String Tuesday = jsonObject.get("Tuesday").toString();
            String Wednesday = jsonObject.get("Wednesday").toString();
            String Thursday = jsonObject.get("Thursday").toString();
            String Friday = jsonObject.get("Friday").toString();
            String Saturday = jsonObject.get("Saturday").toString();
            String Sunday = jsonObject.get("Sunday").toString();
            String rating = jsonObject.get("Rating").toString();
            String reviews = jsonObject.get("Reviews").toString();
            String website = jsonObject.get("Website").toString();

            List<RestaurantType> restaurantTypes = dataProcessing.getRestaurantTypeFromString(restaurantType);
            Coord coord;

            if (xpostion.isEmpty()) coord = null;
            else coord = new Coord(Float.parseFloat(xpostion), Float.parseFloat(ypostion));

            RestaurantType restaurantType1 = null;

            if (restaurantTypes.size() > 0) restaurantType1 = restaurantTypes.get(0);

            Set<Table> tableSet = dataProcessing.getTablesFromStringDatabase(Tables);
            Restaurant restaurant = new Restaurant(name, coord, adress
                    , restaurantType1, PriceCategory.valueOf(priceCategory),
                    tableSet, null);

            restaurant.setWebsite(website);

            for (Table table:tableSet) {
                table.setRestaurant(restaurant);
            }

            if (restaurantTypes.size() > 1) {
                restaurant.addRestaurantType(restaurantTypes.get(1));
            }
            dataProcessing.addAllTimeSlotstoRestaurant(Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday, restaurant);
            restaurant.setRestaurantID(id);
            dataProcessing.addAllReviewsToRestaurtant(reviews,restaurant);

            if (!rating.isBlank()) {
                restaurant.setAverageRating(Float.parseFloat(rating));
            }
            restaurantList.add(restaurant);
        }
        return restaurantList;
    }
}
