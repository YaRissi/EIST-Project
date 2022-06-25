package de.tum.in.ase.insertteamnamehere.userinterface;

import de.tum.in.ase.insertteamnamehere.model.PriceCategory;
import de.tum.in.ase.insertteamnamehere.util.SortingOptions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;


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
    private CheckBox vegeterianCheckBox;

    // Choice Boxen
    @FXML
    private ChoiceBox<PriceCategory> priceClassChoiceBox;
    @FXML
    private ChoiceBox<SortingOptions.SortField> sortingFieldChoiceBox;
    @FXML
    private ChoiceBox<SortingOptions.SortingOrder> sortingOrderChoiceBox;

    // Serach Bar
    @FXML
    private TextField searchBar;

    // Buttons
    @FXML
    private Button searchButton;
    @FXML
    private Button backButton;
    @FXML
    private Button applyButton;

    @FXML
    private void handleSearchButtonEvent(ActionEvent event) {
        //TODO Implement Search
        String input = searchBar.getText();

        System.out.println(input);
    }

    public void handleApplyButtonEvent() {
        // TODO Applies filter options
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        priceClassChoiceBox.getItems().addAll(PriceCategory.AFFORDABLE, PriceCategory.AVERAGE, PriceCategory.EXPENSIVE);
        sortingFieldChoiceBox.getItems().addAll(SortingOptions.SortField.AVERAGE_RATING, SortingOptions.SortField.DISTANCE,
                SortingOptions.SortField.FREE_TIME_SLOTS, SortingOptions.SortField.PRIZE_CATEGORY,
                SortingOptions.SortField.RESTAURANT_TYPE);
        sortingOrderChoiceBox.getItems().addAll(SortingOptions.SortingOrder.ASCENDING,
                SortingOptions.SortingOrder.DESCENDING);
    }
}

