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
import java.time.LocalTime;
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
    @FXML
    private ChoiceBox<LocalTime> startTimeChoiceBox;
    @FXML
    private ChoiceBox<LocalTime> endTimeChoiceBox;

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
    public void handleShowAllButton(ActionEvent event) { //ShowAllButton shows concrete Restaurants
        resultView.getChildren().clear();;

        //interfaceService.initialiseRestaurants();
        //for (Restaurant restaurant : interfaceService.getRestaurants()) {
            //resultView.getChildren().add(createRestaurantObject(restaurant));
        //}
        Database database = new Database();
        System.out.println("Database loaded");
        System.out.printf(database.getRestaurants().toString());
        showOnlyFiftyRestaurants(new Database().getRestaurants(), resultView);
        //showOnlyFiftyRestaurants(interfaceService.getRestaurants(), resultView);
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

        int maxDistance = distanceSpinner.getValue();

        listOfRestaurants = interfaceService.filterDistance(listOfRestaurants, sortingOrder, maxDistance);

        /*
            if (sortField.equals(SortingOptions.SortField.FREE_TIME_SLOTS)) {
                listOfRestaurants = interfaceService.filterRating(null, 0);
            }
        } */

        resultView.getChildren().clear();
        showOnlyFiftyRestaurants(listOfRestaurants, resultView);
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

    private static void showOnlyFiftyRestaurants(List<Restaurant> listOfRestaurants, VBox resultView) {
        if (listOfRestaurants.size() > 50) {
            for (int i = 0; i < 50; i++) {
                resultView.getChildren().add(createRestaurantObject(listOfRestaurants.get(i)));
            }
        } else {
            for (Restaurant restaurant : listOfRestaurants) {
                resultView.getChildren().add(createRestaurantObject(restaurant));
            }
        }
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

        priceClassChoiceBox.getItems().addAll(null, PriceCategory.AFFORDABLE, PriceCategory.AVERAGE, PriceCategory.EXPENSIVE);
        sortingOrderChoiceBox.getItems().addAll(null, SortingOptions.SortingOrder.ASCENDING,
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

        //interfaceService.setRestaurants(jsonParse.parseRestaurant());
    }

}



