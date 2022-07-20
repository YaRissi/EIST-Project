package de.tum.in.ase.insertteamnamehere.userinterface;

import de.tum.in.ase.insertteamnamehere.model.*;
import de.tum.in.ase.insertteamnamehere.user.User;
import de.tum.in.ase.insertteamnamehere.util.JSONParse;
import de.tum.in.ase.insertteamnamehere.util.SortingOptions;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class FXMLInterfaceController implements Initializable {
    @FXML
    public Button LoginButton;
    @FXML
    private Button button;
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
    @FXML
    private CheckBox vietnameseCheckBox;

    // Choice Boxen
    @FXML
    private ChoiceBox<PriceCategory> priceClassChoiceBox = new ChoiceBox<>();
    @FXML
    private ChoiceBox<SortingOptions.SortField> sortingFieldChoiceBox = new ChoiceBox<>();
    @FXML
    private ChoiceBox<SortingOptions.SortingOrder> sortingOrderChoiceBox = new ChoiceBox<>();
    @FXML
    private Spinner<Integer> minRatingSpinner;
    @FXML
    private Spinner<Integer> distanceSpinner;

    // Search Bar
    @FXML
    private TextField searchBar = new TextField();

    // Buttons
    @FXML
    private Button searchButton;
    @FXML
    private Button backButton;
    @FXML
    private Button applyButton;
    @FXML
    private Button mapViewButton;
    @FXML
    private Button showAllButton;

    @FXML
    private VBox resultView;

    private final User user = new User("Bob", null, null);
    private final RestaurantService interfaceService = new RestaurantService(user);


    @FXML
    private void handleSearchButtonEvent(ActionEvent event) {
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

    private static Pane createRestaurantObject(Restaurant restaurant) {
        VBox restaurantBox = new VBox();
        restaurantBox.setPadding(new Insets(5, 0, 10, 15));

        Text name = new Text(restaurant.getName());

        name.setFont(new Font(15));
        Text rating = new Text("Rating: " + restaurant.getAverageRating());
        Text price = new Text("Price Class: " + restaurant.getPriceCategory().toString());
        Text category = new Text("Restaurant type: " + restaurant.getRestaurantType().toString());
        Button toViewButton = new Button("View");
        toViewButton.setOnAction(e -> {
            try {
                RestaurantObject.display(restaurant);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        restaurantBox.getChildren().addAll(name, rating, price, category, toViewButton);
        return restaurantBox;
    }

    public void handleApplyButtonEvent() {
        List<RestaurantType> restaurantTypesToSearch = new ArrayList<>();
        SortingOptions.SortingOrder sortingOrder = sortingOrderChoiceBox.getValue();

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
            restaurantTypesToSearch.add(RestaurantType.VEGETARIAN);
        }
        if (vietnameseCheckBox.isSelected()) {
            restaurantTypesToSearch.add(RestaurantType.VIETNAMESE);
        }

        List<Restaurant> listOfRestaurants = interfaceService.filterType(sortingOrder, restaurantTypesToSearch);

        PriceCategory priceCategory = priceClassChoiceBox.getValue();

        listOfRestaurants = interfaceService.filterPrize(listOfRestaurants, sortingOrder, priceCategory);

        int minRating = minRatingSpinner.getValue();

        listOfRestaurants = interfaceService.filterRating(listOfRestaurants, sortingOrder, minRating);
        // TODO Implement Filter Distance and Opening Times
           /*
            if (sortField.equals(SortingOptions.SortField.DISTANCE)) {
                listOfRestaurants = interfaceService.filterRating(null, 0);
            }
            if (sortField.equals(SortingOptions.SortField.FREE_TIME_SLOTS)) {
                listOfRestaurants = interfaceService.filterRating(null, 0);
            }
        } */

        resultView.getChildren().clear();
        for (Restaurant restaurant : listOfRestaurants) {
            resultView.getChildren().add(createRestaurantObject(restaurant));
        }

        System.out.println("Funktioniert... noch nicht! Apply!!! :D");
    }


    public void switchToMapView(ActionEvent event) throws IOException {
        URL myFXML = getClass().getClassLoader().getResource("fxml/MapView.fxml");
        FXMLLoader loader = new FXMLLoader(myFXML);
        Parent root = (Parent) loader.load();
        FXMLMapViewController mapViewController = loader.getController();
        mapViewController.initMapAndControls();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void openToLogin(ActionEvent event) throws IOException {
        URL myFXML = getClass().getClassLoader().getResource("fxml/login.fxml");
        FXMLLoader loader = new FXMLLoader(myFXML);
        Parent root = (Parent) loader.load();
        Stage stageOld = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageOld.close();
        Stage stage = new Stage();
        stage.setTitle("Login");
        Scene scene = new Scene(root, 400, 200);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5);
        spinnerValueFactory.setValue(0);
        minRatingSpinner.setValueFactory(spinnerValueFactory);

        SpinnerValueFactory<Integer> spinnerValueFactoryDistance = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 250);
        spinnerValueFactoryDistance.setValue(0);
        distanceSpinner.setValueFactory(spinnerValueFactoryDistance);

        priceClassChoiceBox.getItems().addAll(PriceCategory.AFFORDABLE, PriceCategory.AVERAGE, PriceCategory.EXPENSIVE);
        sortingOrderChoiceBox.getItems().addAll(SortingOptions.SortingOrder.ASCENDING,
                SortingOptions.SortingOrder.DESCENDING);

        //interfaceService.setRestaurants(createRandomRestaurants());
        JSONParse jsonParse = new JSONParse();
        /*try {
            jsonParse.clearJson();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Database database = new Database();
        database.addAllRestaurants();
        for (Restaurant r:database.getRestaurants()) {
            r.setTables(createRandomTables());
            r.setRatings(createRandomRating());
            r.setReviews(createRandomReviews());
            jsonParse.writeJson(r);
        }
        for (Restaurant r:createRandomRestaurants()) {
            jsonParse.writeJson(r);
        }
        System.out.println(createRandomRestaurants());*/

        interfaceService.setRestaurants(jsonParse.parseRestaurant());


    }


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
            listOfTables.add(new Table(random.nextInt(max + min) + min, "" + i));
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
        int max = 5;
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
            default -> {
                return "EIST ist ein tolles Fach!";
            }
        }
    }

}



