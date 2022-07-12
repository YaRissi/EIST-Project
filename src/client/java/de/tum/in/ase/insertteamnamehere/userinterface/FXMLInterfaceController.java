package de.tum.in.ase.insertteamnamehere.userinterface;

import de.tum.in.ase.insertteamnamehere.model.*;
import de.tum.in.ase.insertteamnamehere.util.SortingOptions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;

public class FXMLInterfaceController implements Initializable {
    // Alle Check Boxen
    @FXML
    private CheckBox barRestaurantCheckBox;
    @FXML
    private CheckBox burgerCheckBox;
    @FXML
    private CheckBox cafeCheckBox;
    @FXML
    private CheckBox chineseCheckBox;
    @FXML
    private CheckBox germanCheckBox;
    @FXML
    private CheckBox greekCheckBox;
    @FXML
    private CheckBox indianCheckBox;
    @FXML
    private CheckBox italianCheckBox;
    @FXML
    private CheckBox japaneseCheckBox;
    @FXML
    private CheckBox mexicanCheckBox;
    @FXML
    private CheckBox noodleBarCheckBox;
    @FXML
    private CheckBox pizzaCheckBox;
    @FXML
    private CheckBox sushiBarCheckBox;
    @FXML
    private CheckBox thaiCheckBox;
    @FXML
    private CheckBox turkishCheckBox;
    @FXML
    private CheckBox vegetarianCheckBox;
    // Choice Boxen
    @FXML
    private ChoiceBox<PriceCategory> priceClassChoiceBox;
    @FXML
    private ChoiceBox<SortingOptions.SortField> sortingFieldChoiceBox;
    @FXML
    private ChoiceBox<SortingOptions.SortingOrder> sortingOrderChoiceBox;
    @FXML
    private TextField searchBar;
    // Buttons
    @FXML
    private Button searchButton;
    @FXML
    private Button showAllButton;
    @FXML
    private Button applyButton;
    @FXML
    private VBox resultView;
    private final RestaurantService interfaceService = new RestaurantService();

    // --------------------------------------- Methoden ----------------------------------------------
    @FXML
    public void handleSearchButtonEvent(ActionEvent event) {
        resultView.getChildren().clear();
        String input = searchBar.getText();
        List<Restaurant> listOfRestaurants = new ArrayList<>();
        try {
            listOfRestaurants = interfaceService.search(input);
        } catch (IllegalArgumentException e) {
            Text emptyMessage = new Text("No search results found...");
            resultView.getChildren().add(emptyMessage);
        }
        for (Restaurant restaurant : listOfRestaurants) {
            resultView.getChildren().add(createRestaurantObject(restaurant));
        }
    }

    @FXML
    public void handleShowAllButton(ActionEvent event) {
        resultView.getChildren().clear();
        for (Restaurant restaurant : interfaceService.getRestaurants()) {
            resultView.getChildren().add(createRestaurantObject(restaurant));
        }
    }

    // Erzeugt die einzelnen Restaurants im Interface
    private static Pane createRestaurantObject(Restaurant restaurant) {
        // TODO Optimize Layout
        VBox overview = new VBox();
        Text name = new Text(restaurant.getName());
        Text rating = new Text("Rating: " + restaurant.getAverageRating()); // new Text(restaurant.getAverageRating());
        Text price = new Text(restaurant.getPriceCategory().toString());
        Text category = new Text(restaurant.getRestaurantType().toString());
        Button toView = new Button("View");
        toView.setOnAction(e -> {
            try {
                RestaurantObject.display(restaurant);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        overview.getChildren().addAll(name, rating, price, category, toView);
        return overview;
    }

    @FXML
    public void handleApplyButtonEvent(ActionEvent event) {
        // TODO Applies filter options and displays
        List<RestaurantType> restaurantTypesToSearch = new ArrayList<>();
        if (barRestaurantCheckBox.isSelected()) {
            restaurantTypesToSearch.add(RestaurantType.BAR_RESTAURANT);
        }
        if (burgerCheckBox.isSelected()) {
            restaurantTypesToSearch.add(RestaurantType.BURGER);
        }
        if (cafeCheckBox.isSelected()) {
            restaurantTypesToSearch.add(RestaurantType.CAFE);
        }
        if (chineseCheckBox.isSelected()) {
            restaurantTypesToSearch.add(RestaurantType.CHINESE);
        }
        if (germanCheckBox.isSelected()) {
            restaurantTypesToSearch.add(RestaurantType.GERMAN);
        }
        if (greekCheckBox.isSelected()) {
            restaurantTypesToSearch.add(RestaurantType.GREEK);
        }
        if (indianCheckBox.isSelected()) {
            restaurantTypesToSearch.add(RestaurantType.INDIAN);
        }
        if (italianCheckBox.isSelected()) {
            restaurantTypesToSearch.add(RestaurantType.ITALIAN);
        }
        if (japaneseCheckBox.isSelected()) {
            restaurantTypesToSearch.add(RestaurantType.JAPANESE);
        }
        if (mexicanCheckBox.isSelected()) {
            restaurantTypesToSearch.add(RestaurantType.MEXICAN);
        }
        if (noodleBarCheckBox.isSelected()) {
            restaurantTypesToSearch.add(RestaurantType.NOODLE_BAR);
        }
        if (pizzaCheckBox.isSelected()) {
            restaurantTypesToSearch.add(RestaurantType.PIZZA);
        }
        if (sushiBarCheckBox.isSelected()) {
            restaurantTypesToSearch.add(RestaurantType.SUSHI_BAR);
        }
        if (thaiCheckBox.isSelected()) {
            restaurantTypesToSearch.add(RestaurantType.THAI);
        }
        if (turkishCheckBox.isSelected()) {
            restaurantTypesToSearch.add(RestaurantType.TURKISH);
        }
        if (vegetarianCheckBox.isSelected()) {
            restaurantTypesToSearch.add(RestaurantType.TURKISH);
        }

        List<Restaurant> listOfRestaurants = interfaceService.filterType(null, null);
        // Als Eingabe Liste von RestaurantTypes benötigt

        PriceCategory priceCategory = priceClassChoiceBox.getValue();
        if (priceCategory.equals(PriceCategory.AVERAGE)) {
            listOfRestaurants = interfaceService.filterPrize(null, null);
            // Als Eingabe Liste von Restaurants und eine PriceCategory benötigt
        }
        if (priceCategory.equals(PriceCategory.EXPENSIVE)) {
            listOfRestaurants = interfaceService.filterPrize(null, null);
        }
        if (priceCategory.equals(PriceCategory.AFFORDABLE)) {
            listOfRestaurants = interfaceService.filterPrize(null, null);
        }

        SortingOptions.SortField sortField = sortingFieldChoiceBox.getValue();
        if (sortField.equals(SortingOptions.SortField.AVERAGE_RATING)) {
            listOfRestaurants = interfaceService.filterRating(null, 0);
        }
        if (sortField.equals(SortingOptions.SortField.DISTANCE)) {
            listOfRestaurants = interfaceService.filterRating(null, 0);
        }
        if (sortField.equals(SortingOptions.SortField.FREE_TIME_SLOTS)) {
            listOfRestaurants = interfaceService.filterRating(null, 0);
        }
        if (sortField.equals(SortingOptions.SortField.RESTAURANT_TYPE)) {
            listOfRestaurants = interfaceService.filterRating(null, 0);
        }

        SortingOptions.SortingOrder sortingOrder = sortingOrderChoiceBox.getValue();

        resultView.getChildren().clear();
        for (Restaurant restaurant : listOfRestaurants) {
            resultView.getChildren().add(createRestaurantObject(restaurant));
        }

        System.out.println("Funktioniert... noch nicht! Apply!!! :D");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        priceClassChoiceBox.getItems().addAll(null, PriceCategory.AFFORDABLE, PriceCategory.AVERAGE, PriceCategory.EXPENSIVE);
        sortingFieldChoiceBox.getItems().addAll(null, SortingOptions.SortField.AVERAGE_RATING, SortingOptions.SortField.DISTANCE,
                SortingOptions.SortField.FREE_TIME_SLOTS, SortingOptions.SortField.PRIZE_CATEGORY,
                SortingOptions.SortField.RESTAURANT_TYPE);
        sortingOrderChoiceBox.getItems().addAll(null, SortingOptions.SortingOrder.ASCENDING,
                SortingOptions.SortingOrder.DESCENDING);
        interfaceService.setRestaurants(createRandomRestaurants());
    }

    // --------------------------------------- Zum Testen ----------------------------------------------
    public static List<Restaurant> createRandomRestaurants() {
        int min = 16;
        int max = 49;
        Random random = new Random();
        int numberOfRestaurants = random.nextInt(max + min) + min;
        List<Restaurant> dummyList = new ArrayList<>();
        for (int i = 0; i < numberOfRestaurants; i++) {
            List<RestaurantType> restaurantTypes = createRandomRestaurantType();
            Restaurant dummy = new Restaurant(createRandomRestaurantName(restaurantTypes), null,"" + i, null, createRandomPriceCategory(), createRandomTables(), null);
            dummy.setAverageRating(createRandomRating());
            dummy.setRestaurantType(restaurantTypes);
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

    private static int createRandomRating() {
        int min = 1;
        int max = 4;
        Random random = new Random();
        int rating = random.nextInt(max + min) + min;
        return rating;
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

    public static Set<Table> createRandomTables() {
        int min = 1;
        int max = 10;
        Random random = new Random();
        int numberOfTables = random.nextInt(max + min) + min;
        Set<Table> listOfTables = new HashSet<>();
        for(int i = 0; i<numberOfTables; i++) {
            listOfTables.add(new Table(random.nextInt(max + min) + min, ""+i));
        }
        return listOfTables;
    }
}
