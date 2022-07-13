package de.tum.in.ase.insertteamnamehere.model;

import java.io.FileNotFoundException;
import java.sql.*;
import java.time.LocalTime;
import java.util.*;
import java.util.List;

public class DatabaseProcessing {
    private List<Restaurant> restaurants;
    Connection connection;


    public List<Restaurant> getRestaurants() throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM restaurantdatabase.restaurants;");
        return buildRestaurants(resultSet);
    }

    public Restaurant getRestaurant(String id, String restaurantName) throws SQLException, FileNotFoundException {
        restaurants = new ArrayList<>();
        if (id == null && restaurantName == null) {
            throw new IllegalArgumentException("You have to specify which restaurant you want!");
        }

        if (restaurantName != null) {
            ResultSet results = connection.createStatement().executeQuery("SELECT * FROM restaurantdatabase.restaurants WHERE name = " + restaurantName + ";");
            restaurants = buildRestaurants(results);
        } else {
            ResultSet results = connection.createStatement().executeQuery("SELECT * FROM restaurantdatabase.restaurants WHERE restaurant_id = " + id + ";");
            restaurants = buildRestaurants(results);
        }

        if (restaurants.size() == 0) {
            throw new FileNotFoundException("This restaurant does not yet exist in our database.");
        }

        return restaurants.get(0);
    }

    private List<Restaurant> buildRestaurants(ResultSet resultSet) throws SQLException {
        restaurants = new ArrayList<>();

        int i = 0;
        while (i < resultSet.getFetchSize()) {
            String name = resultSet.getString("name");
            String restaurantId = resultSet.getString("restaurant_id");
            //Point location =
            String address = resultSet.getString("address");

            String restaurantTypeString = resultSet.getString("restauranttype").toLowerCase();
            RestaurantType restaurantType;
            switch (restaurantTypeString) {
                case "italian" -> restaurantType = RestaurantType.ITALIAN;
                case "german" -> restaurantType = RestaurantType.GERMAN;
                case "chinese" -> restaurantType = RestaurantType.CHINESE;
                case "indian" -> restaurantType = RestaurantType.INDIAN;
                case "japanese" -> restaurantType = RestaurantType.JAPANESE;
                case "greek" -> restaurantType = RestaurantType.GREEK;
                case "thai" -> restaurantType = RestaurantType.THAI;
                case "turkish" -> restaurantType = RestaurantType.TURKISH;
                case "mexican" -> restaurantType = RestaurantType.MEXICAN;
                case "bar_restaurant" -> restaurantType = RestaurantType.BAR_RESTAURANT;
                case "cafe" -> restaurantType = RestaurantType.CAFE;
                case "vegetarian" -> restaurantType = RestaurantType.VEGETARIAN;
                case "burger" -> restaurantType = RestaurantType.BURGER;
                case "pizza" -> restaurantType = RestaurantType.PIZZA;
                case "sushi_bar" -> restaurantType = RestaurantType.SUSHI_BAR;
                case "noodle_bar" -> restaurantType = RestaurantType.NOODLE_BAR;
                case "vietnamese" -> restaurantType = RestaurantType.VIETNAMESE;
                default ->
                        throw new IllegalArgumentException("The restaurant type has not been saved in the database correctly!");
            }

            String priceCategoryString = resultSet.getString("pricecategory").toLowerCase();
            PriceCategory priceCategory;
            switch (priceCategoryString) {
                case "affordable" -> priceCategory = PriceCategory.AFFORDABLE;
                case "average" -> priceCategory = PriceCategory.AVERAGE;
                case "expensive" -> priceCategory = PriceCategory.EXPENSIVE;
                default ->
                        throw new IllegalArgumentException("The price category was not set correctly in the database");
            }
            Set<Table> tables = getTablesForId(restaurantId);
            List<List<TimeSlot>> timeSlots = getTimeSlotsForId(restaurantId);

            Restaurant restaurant = new Restaurant(restaurantId, null, address, restaurantType, priceCategory, tables, timeSlots);

            restaurants.add(restaurant);
        }
        return restaurants;
    }

    private List<List<TimeSlot>> getTimeSlotsForId(String id) throws SQLException {
        List<List<TimeSlot>> timeSlots = new ArrayList<>(7);
        if (id == null) {
            throw new IllegalArgumentException("You have to specify which restaurant you want!");
        }
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM restaurantdatabase.restaurants WHERE restaurant_id = " + id + ";");
        int i = 0;
        while (i < resultSet.getFetchSize()) {
            timeSlots.add(0, turnStringToTimeSlots(resultSet.getString("monday")));
            timeSlots.add(1, turnStringToTimeSlots(resultSet.getString("tuesday")));
            timeSlots.add(2, turnStringToTimeSlots(resultSet.getString("wednesday")));
            timeSlots.add(3, turnStringToTimeSlots(resultSet.getString("thursday")));
            timeSlots.add(4, turnStringToTimeSlots(resultSet.getString("friday")));
            timeSlots.add(5, turnStringToTimeSlots(resultSet.getString("saturday")));
            timeSlots.add(6, turnStringToTimeSlots(resultSet.getString("sunday")));
        }
        return timeSlots;
    }

    private List<TimeSlot> turnStringToTimeSlots(String time) {
        String[] slotsOneDay = time.split("&");
        List<TimeSlot> timeSlots = new ArrayList<>();
        for (int i = 0; i < slotsOneDay.length; i++) {
            String current = slotsOneDay[i];
            String[] times = current.split("-");
            if (times.length != 2) {
                throw new IllegalArgumentException("There must be exactly one start and end time for the time slot!");
            }
            for (int j = 0; j < times.length; j++) {
                LocalTime start = LocalTime.of(Integer.getInteger(times[0].substring(0, times[0].lastIndexOf(":"))), Integer.getInteger(times[0].substring(times[0].lastIndexOf(":") + 1)));
                LocalTime end = LocalTime.of(Integer.getInteger(times[1].substring(0, times[1].lastIndexOf(":"))), Integer.getInteger(times[1].substring(times[1].lastIndexOf(":") + 1)));
                TimeSlot slot = new TimeSlot(start, end);
                timeSlots.add(slot);
            }
        }
        return timeSlots;
    }

    private Set<Table> getTablesForId(String id) throws SQLException {
        Set<Table> tables = new HashSet<>();
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM restaurantdatabase.tables WHERE restaurant_id = " + id + ";");

        int i = 0;
        while (i < resultSet.getFetchSize()) {
            Table currentTable = new Table(resultSet.getInt("max_people"), resultSet.getString("table_id"));
            tables.add(currentTable);
        }

        return tables;
    }

    public boolean addRestaurant(Restaurant restaurant) {
        String name = restaurant.getName();
        UUID id = (UUID) restaurant.getRestaurantID();
        String location = restaurant.getLocation().toString();
        String address = restaurant.getAddress();

        String restaurantType = "";
        int i = 0;
        while (i < restaurant.getRestaurantType().size()) {
            if (i > 0) {
                restaurantType += ", ";
            }
            restaurantType += restaurant.getRestaurantType().get(i).toString().toLowerCase();
            i++;
        }

        String priceCategory = restaurant.getPriceCategory().toString().toLowerCase();

        try {
            connection.createStatement().execute("INSERT INTO restaurantdatabase.restaurants \n VALUES (" + "'" + name + "' , " +
                    "'" + id + "' , " + "'" + location + "' , " + "'" + address + "' , " + "'" + restaurantType + "' , " + "'" + priceCategory + "'" + null + ";");
            return true;
        } catch (SQLException sqlException) {
            return false;
        }
    }

    public boolean deleteRestaurant(Restaurant restaurant) {
        try {
            connection.createStatement().execute("DELETE FROM restaurantdatabase.restaurants WHERE restaurant_id = " + restaurant.getRestaurantID() + ";");
            return true;
        } catch (SQLException sqlException) {
            return false;
        }
    }

    public void setName(String name, Restaurant restaurant) throws SQLException {
        connection.createStatement().execute("UPDATE restaurantdatabase.restaurants \n SET name = " + name +
                "\n WHERE restaurant_id = " + restaurant.getRestaurantID() + ";");
    }

    public String getName(Restaurant restaurant) throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT FROM restaurantdatabase.restaurants WHERE restaurant_id = " + restaurant.getRestaurantID() + ";");
        return resultSet.getString("name");
    }

    public void setRestaurantId(String restaurantId, Restaurant restaurant) throws SQLException {
        connection.createStatement().execute("UPDATE restaurantdatabase.restaurants \n SET restaurant_id = " + restaurantId +
                "\n WHERE name = " + restaurant.getName() + ";");
    }

    public String getRestaurantId(Restaurant restaurant) throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT FROM restaurantdatabase.restaurants WHERE restaurant_id = " + restaurant.getRestaurantID() + ";");
        return resultSet.getString("restaurant_id");
    }

    public void setAddress(String address, Restaurant restaurant) throws SQLException {
        connection.createStatement().execute("UPDATE restaurantdatabase.restaurants \n SET address = " + address +
                "\n WHERE restaurant_id = " + restaurant.getRestaurantID() + ";");
    }

    public String getAddress(Restaurant restaurant) throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT FROM restaurantdatabase.restaurants WHERE restaurant_id = " + restaurant.getRestaurantID() + ";");
        return resultSet.getString("address");
    }

    //TODO
    public void setLocation() {

    }

    public void setRestaurantType(RestaurantType restaurantType, Restaurant restaurant) throws SQLException {
        String restType = restaurantType.toString();
        connection.createStatement().execute("UPDATE restaurantdatabase.restaurants \n SET restauranttype = " + restType +
                "\n WHERE restaurant_id = " + restaurant.getRestaurantID() + ";");
    }

    public List<RestaurantType> getRestaurantType(Restaurant restaurant) throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT FROM restaurantdatabase.restaurants WHERE restaurant_id = " + restaurant.getRestaurantID() + ";");
        String[] types = resultSet.getString("restauranttype").toUpperCase().split(", ");
        List<RestaurantType> typesList = new ArrayList<>();
        for (int i = 0; i < types.length; i++) {
            typesList.add(RestaurantType.valueOf(types[i]));
        }
        return typesList;
    }

    public void setPriceCategory(PriceCategory priceCategory, Restaurant restaurant) throws SQLException {
        String priceCat = priceCategory.toString();
        connection.createStatement().execute("UPDATE restaurantdatabase.restaurants \n SET pricecategory = " + priceCat +
                "\n WHERE restaurant_id = " + restaurant.getRestaurantID() + ";");
    }

    public PriceCategory getPriceCategory(Restaurant restaurant) throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT FROM restaurantdatabase.restaurants WHERE restaurant_id = " + restaurant.getRestaurantID() + ";");
        return PriceCategory.valueOf(resultSet.getString("pricecategory").toUpperCase());
    }

    public String getWebsite(Restaurant restaurant) throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT FROM restaurantdatabase.restaurants WHERE restaurant_id = " + restaurant.getRestaurantID() + ";");
        return resultSet.getString("website");
    }

    public void setWebsite(String website, Restaurant restaurant) throws SQLException {
        connection.createStatement().execute("UPDATE restaurantdatabase.restaurants \n SET website = " +
                website + "\n WHERE restaurant_id = " + restaurant.getRestaurantID() + ";");
    }

    public Set<Table> getTables(Restaurant restaurant) throws SQLException {
        Set<Table> tables = new HashSet<>();
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT FROM restaurantdatabase.restaurants WHERE restaurant_id = " + restaurant.getRestaurantID() + ";");
        for (int i = 0; i < resultSet.getFetchSize(); i++) {
            tables.add(new Table(resultSet.getInt("max_people"), resultSet.getString("table_id")));
        }
        return tables;
    }

    public boolean addTable(Restaurant restaurant, Table table) {
        String tableId = table.getTableId();
        UUID restaurantId = (UUID) restaurant.getRestaurantID();
        int maxPeople = table.getMaxNumberOfPeople();

        try {
            connection.createStatement().execute("INSERT INTO restaurantdatabase.tables \n VALUES (" + "'" + tableId + "' , " +
                    "'" + restaurantId + "' , '" + maxPeople + "' ;");
            return true;
        } catch (SQLException sqlException) {
            return false;
        }
    }

    public boolean deleteTable(Restaurant restaurant, Table table) {
        try {
            connection.createStatement().execute("DELETE FROM restaurantdatabase.tables WHERE table_id = " + table.getTableId() + ";");
            return true;
        } catch (SQLException sqlException) {
            return false;
        }
    }

    //TODO
    public Connection establishConnection() {
        return null;
        //DataSource
    }


    /*

        //Die zwei auskommentierten Zahlen sind Koordinaten --> je nach Implementierung der Karte evtl. später für Location nutzbar
        //1
        Restaurant takumiViktualienmarkt
        //48.13534994382753, 11.579664625269427


        //2
        Restaurant sausalitos
        //48.135987192119714, 11.578892149131507


        //3
        Restaurant hansImGlueckTal
        //48.13609259598959, 11.57834849796949


        //4
        Restaurant ratskeller
        //48.13767428633818, 11.576067956695082


        //5
        Restaurant augustinerAmDom
        //48.13835446413484, 11.574061664334922


        //6
        Restaurant cafeZeitgeist
        //48.15143292984926, 11.57662948875597


        //7
        Restaurant loStudente
        //48.15079784757689, 11.57575859493323


        //8
        Restaurant tacoCompany
        //48.15046921948561, 11.578340843506178



        //9
        Restaurant teaTime
        //48.151698006111474, 11.576700821271507


        //10
        Restaurant twentyPho
        //48.15107031643462, 11.56382077285266


        //11
        Restaurant chiThu
        //48.16205020074638, 11.585947938528614


        //12
        Restaurant laTazzaDoro
        //48.15969689206116, 11.582469217443164


        //13
        Restaurant rischart
        //48.159626676068655, 11.585797951238305


        //14
        Restaurant munMun
        //48.16276825147988, 11.586589371003813


        //15
        Restaurant newEra
        //48.16269234168049, 11.587227992819823


     */
}
