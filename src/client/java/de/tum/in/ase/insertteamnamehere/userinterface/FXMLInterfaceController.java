package de.tum.in.ase.insertteamnamehere.userinterface;

import com.dlsc.gmapsfx.GoogleMapView;
import com.dlsc.gmapsfx.MapComponentInitializedListener;
import com.dlsc.gmapsfx.javascript.object.*;
import de.tum.in.ase.insertteamnamehere.model.PriceCategory;
import de.tum.in.ase.insertteamnamehere.model.Restaurant;
import de.tum.in.ase.insertteamnamehere.model.RestaurantService;
import de.tum.in.ase.insertteamnamehere.util.SortingOptions;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;


public class FXMLInterfaceController implements Initializable, MapComponentInitializedListener {

    @FXML
    public GoogleMapView MapView = new GoogleMapView();

    private List<Restaurant> searchResult;

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
    private CheckBox vegeterianCheckBox;

    // Choice Boxen
    @FXML
    private ChoiceBox<PriceCategory> priceClassChoiceBox = new ChoiceBox<>();
    @FXML
    private ChoiceBox<SortingOptions.SortField> sortingFieldChoiceBox = new ChoiceBox<>();
    @FXML
    private ChoiceBox<SortingOptions.SortingOrder> sortingOrderChoiceBox = new ChoiceBox<>();

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
    private Button backToResultView;


    @FXML
    private void handleSearchButtonEvent(ActionEvent event) {
        //TODO Implement Search
        String input = searchBar.getText();

        System.out.println(input);
    }

    public void handleApplyButtonEvent() {
        // TODO Applies filter options
    }

    public void switchToMapView(ActionEvent event) throws IOException {
        URL myFXML = getClass().getClassLoader().getResource("fxml/MapView.fxml");
        FXMLLoader loader = new FXMLLoader(myFXML);
        Parent root = (Parent) loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToResultView(ActionEvent event) throws IOException {
        URL myFXML = getClass().getClassLoader().getResource("fxml/ResultView.fxml");
        FXMLLoader loader = new FXMLLoader(myFXML);
        Parent root = (Parent) loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        priceClassChoiceBox.getItems().addAll(PriceCategory.AFFORDABLE, PriceCategory.AVERAGE, PriceCategory.EXPENSIVE);
        sortingFieldChoiceBox.getItems().addAll(SortingOptions.SortField.AVERAGE_RATING, SortingOptions.SortField.DISTANCE,
                SortingOptions.SortField.FREE_TIME_SLOTS, SortingOptions.SortField.PRIZE_CATEGORY,
                SortingOptions.SortField.RESTAURANT_TYPE);
        sortingOrderChoiceBox.getItems().addAll(SortingOptions.SortingOrder.ASCENDING,
                SortingOptions.SortingOrder.DESCENDING);
        MapView.addMapInitializedListener(this);
    }

    @Override
    public void mapInitialized() {
        LatLong joeSmithLocation = new LatLong(48.13534994382753, 11.579664625269427);


        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(48.13534994382753, 11.579664625269427))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);

        GoogleMap map = MapView.createMap(mapOptions);

        //Add markers to the map
        for(Restaurant r : searchResult) {
            LatLong position = new LatLong(r.getLocation().getLat(),r.getLocation().getLon());

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(position);

            Marker newMarker = new Marker(markerOptions);
            map.addMarker(newMarker);

            InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
            infoWindowOptions.content(r.getName()+ "\n" + r.getAddress() + "\n" +r.getWebsite() +"\n"+ r.getAverageRating());
            InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);

            fredWilkeInfoWindow.open(map, newMarker);
        }
    }
}

