package de.tum.in.ase.insertteamnamehere.userinterface;

import de.tum.in.ase.insertteamnamehere.model.PriceCategory;
import de.tum.in.ase.insertteamnamehere.model.Restaurant;
import de.tum.in.ase.insertteamnamehere.model.RestaurantType;
import de.tum.in.ase.insertteamnamehere.model.Table;
import de.tum.in.ase.insertteamnamehere.util.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.UUID;

public class FXMLAdminController implements Initializable {

    //private final RestaurantController restaurantController = new RestaurantController();

    @FXML
    public TextField restaurantName;
    @FXML
    public TextField xposition;
    @FXML
    public TextField yposition;
    @FXML
    public TextField adresse;
    @FXML
    public TextField tables;
    @FXML
    public TextField Monday;
    @FXML
    public TextField Tuesday;
    @FXML
    public TextField Wednesday;
    @FXML
    public TextField Thursday;
    @FXML
    public TextField Friday;
    @FXML
    public TextField Saturday;
    @FXML
    public TextField Sunday;


    @FXML
    public TextField website;

    @FXML
    public VBox restaurantList;
    @FXML
    public TextField rating;


    @FXML
    private ChoiceBox<RestaurantType> restaurantType = new ChoiceBox<>();


    @FXML
    private ChoiceBox<PriceCategory> priceClassChoiceBox = new ChoiceBox<>();

    public void showAllRestaurant(ActionEvent event) throws IOException, InterruptedException {
        JSONParse jsonParse = new JSONParse();
        List<Restaurant> restaurants = jsonParse.parseRestaurant();
        restaurantList.getChildren().clear();
        for (Restaurant r : restaurants) {
            Button button = new Button(r.getName());
            button.setId(r.getRestaurantID().toString());
            button.setOnAction(showRestaurantDetails(event, r));
            restaurantList.getChildren().add(button);
        }
    }


    public EventHandler<ActionEvent> showRestaurantDetails(ActionEvent event, Restaurant restaurant) {

        return event1 -> {
            TextField restaurantInfoName = new TextField();
            TextField xpositionInfo = new TextField();
            TextField ypositionInfo = new TextField();
            TextField adresseInfo = new TextField();
            TextField websiteInfo = new TextField();
            TextField ratingInfo = new TextField();
            ChoiceBox<RestaurantType> restaurantTypeInfo = new ChoiceBox<>();
            ChoiceBox<PriceCategory> priceClassChoiceBoxInfo = new ChoiceBox<>();
            restaurantTypeInfo.getItems().addAll(RestaurantType.values());
            priceClassChoiceBoxInfo.getItems().addAll(PriceCategory.values());
            TextField tablesInfo = new TextField();
            TextField MondayInfo = new TextField();
            TextField TuesdayInfo = new TextField();
            TextField WednesdayInfo = new TextField();
            TextField ThursdayInfo = new TextField();
            TextField FridayInfo = new TextField();
            TextField SaturdayInfo = new TextField();
            TextField SundayInfo = new TextField();

            HBox hBox = new HBox();

            Button button = new Button("Update Restaurant");
            Button button1 = new Button("Delete Restaurant");

            hBox.getChildren().addAll(button, button1);

            VBox vBox = new VBox();
            vBox.getChildren().addAll(restaurantInfoName, xpositionInfo, ypositionInfo, adresseInfo, websiteInfo, ratingInfo
                    , restaurantTypeInfo, priceClassChoiceBoxInfo, tablesInfo, MondayInfo, TuesdayInfo
                    , WednesdayInfo, ThursdayInfo, FridayInfo, SaturdayInfo, SundayInfo, hBox);

            restaurantInfoName.setText(restaurant.getName());
            restaurantInfoName.setPromptText("Name");
            if (restaurant.getLocation() != null) {
                xpositionInfo.setText(String.valueOf(restaurant.getLocation().getLat()));
                ypositionInfo.setText(String.valueOf(restaurant.getLocation().getLon()));
            }
            xpositionInfo.setPromptText("yposition");
            ypositionInfo.setPromptText("yposition");
            adresseInfo.setText(restaurant.getAddress());
            adresseInfo.setPromptText("Adress");
            websiteInfo.setText(restaurant.getWebsite());
            websiteInfo.setPromptText("Website");
            if (!Float.isNaN(restaurant.getAverageRating())) {
                ratingInfo.setText(String.valueOf(restaurant.getAverageRating()));
            }

            ratingInfo.setPromptText("Rating");
            restaurantTypeInfo.setValue(restaurant.getRestaurantType().get(0));
            priceClassChoiceBoxInfo.setValue(restaurant.getPriceCategory());
            DataProcessing dataProcessing = new DataProcessing();
            tablesInfo.setText(dataProcessing.convertTablesToString(restaurant.getTables()));
            tablesInfo.setPromptText("Tables");
            JSONParse jsonParse = new JSONParse();
            MondayInfo.setText(dataProcessing.convertOpeningToString(jsonParse.getTimes(restaurant, 0)));
            MondayInfo.setPromptText("Monday");
            TuesdayInfo.setText(dataProcessing.convertOpeningToString(jsonParse.getTimes(restaurant, 1)));
            TuesdayInfo.setPromptText("Tuesday");
            WednesdayInfo.setText(dataProcessing.convertOpeningToString(jsonParse.getTimes(restaurant, 2)));
            WednesdayInfo.setPromptText("Wednesday");
            ThursdayInfo.setText(dataProcessing.convertOpeningToString(jsonParse.getTimes(restaurant, 3)));
            ThursdayInfo.setPromptText("Thursday");
            FridayInfo.setText(dataProcessing.convertOpeningToString(jsonParse.getTimes(restaurant, 4)));
            FridayInfo.setPromptText("Friday");
            SaturdayInfo.setText(dataProcessing.convertOpeningToString(jsonParse.getTimes(restaurant, 5)));
            SaturdayInfo.setPromptText("Saturday");
            SundayInfo.setText(dataProcessing.convertOpeningToString(jsonParse.getTimes(restaurant, 6)));
            SundayInfo.setPromptText("Sunday");

            Stage stage = new Stage();
            stage.setTitle("Information for " + restaurant.getName());
            Scene scene = new Scene(vBox, 300, 567);
            stage.setScene(scene);
            stage.show();
            button.setOnAction(s -> {
                addRestaurantInfo(event, restaurantInfoName, xpositionInfo, ypositionInfo
                        , adresseInfo, restaurantTypeInfo, priceClassChoiceBoxInfo
                        , tablesInfo, websiteInfo, ratingInfo, MondayInfo, TuesdayInfo
                        , WednesdayInfo, ThursdayInfo, FridayInfo
                        , SaturdayInfo, SundayInfo, restaurant.getRestaurantID());
                stage.close();
                try {
                    showAllRestaurant(event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            button1.setOnAction(s -> {
                List<Restaurant> restaurants = jsonParse.parseRestaurant();
                int index = 0;
                boolean found = false;
                while (!found) {
                    if (index > restaurants.size() - 1) found = true;
                    if (restaurants.get(index).getRestaurantID().equals(restaurant.getRestaurantID())) found = true;
                    else index++;
                }
                alertInfo(restaurants.get(index).getName()+"was removed successfully");
                restaurants.remove(index);
                try {
                    jsonParse.clearJson();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                for (Restaurant r : restaurants) {
                    jsonParse.writeJson(r);
                }
                stage.close();
                try {
                    showAllRestaurant(event);
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        };

    }

    public void addRestaurantDashboard(ActionEvent event) {
        alertInfo(restaurantName.getText()+" was added successfully!");
        addRestaurant(event, restaurantName, xposition, yposition
                , adresse, restaurantType, priceClassChoiceBox
                , tables, website,rating, Monday, Tuesday
                , Wednesday, Thursday, Friday
                , Saturday, Sunday, null);
        try {
            showAllRestaurant(event);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void addRestaurantInfo(ActionEvent event, TextField restaurantName, TextField xposition, TextField yposition
            , TextField adresse, ChoiceBox<RestaurantType> restaurantType, ChoiceBox<PriceCategory> priceClassChoiceBox
            , TextField tables, TextField website,TextField rating, TextField Monday, TextField Tuesday
            , TextField Wednesday, TextField Thursday, TextField Friday
            , TextField Saturday, TextField Sunday, UUID id) {
        alertInfo(restaurantName.getText()+" was updated successfully!");
        addRestaurant(event, restaurantName, xposition, yposition
                , adresse, restaurantType, priceClassChoiceBox
                , tables, website, rating, Monday, Tuesday
                , Wednesday, Thursday, Friday
                , Saturday, Sunday, id);

    }

    public void addRestaurant(ActionEvent event, TextField restaurantName, TextField xposition, TextField yposition
            , TextField adresse, ChoiceBox<RestaurantType> restaurantType, ChoiceBox<PriceCategory> priceClassChoiceBox
            , TextField tables, TextField website, TextField rating, TextField Monday, TextField Tuesday
            , TextField Wednesday, TextField Thursday, TextField Friday
            , TextField Saturday, TextField Sunday, UUID id) {
        try {
            DataProcessing dataProcessing = new DataProcessing();
            String restaurantNameT = restaurantName.getText();
            if (restaurantNameT.isBlank()) alert("RestaurantName is blank");
            float ypositionT = Float.NaN;
            float xpositionT = Float.NaN;
            if(!xposition.getText().isBlank()) xpositionT = Float.parseFloat(xposition.getText());
            if(!xposition.getText().isBlank()) ypositionT = Float.parseFloat(yposition.getText());
            String adresseT = adresse.getText();
            if (adresseT.isBlank()) alert("Adress is blank");
            String ratingT = rating.getText();
            RestaurantType restaurantTypeT = restaurantType.getValue();
            if (restaurantTypeT == null) alert("RestaurantType is blank");
            PriceCategory priceCategoryT = priceClassChoiceBox.getValue();
            if (priceCategoryT == null) alert("PriceCategory is blank");
            String tablesT = tables.getText();
            Set<Table> tableSet = dataProcessing.getTablesFromString(tablesT);
            if (tableSet == null) alert("Tables are incorrect or missing");
            Coord coord = null;
            if(!Float.isNaN(ypositionT))coord = new Coord(xpositionT, ypositionT);
            Restaurant restaurant = new Restaurant(restaurantNameT,coord, adresseT, restaurantTypeT, priceCategoryT, tableSet, null);
            String websiteT = website.getText();
            if (!websiteT.isBlank()) restaurant.setWebsite(websiteT);
            String mondayT = Monday.getText();
            String TuesdayT = Tuesday.getText();
            String WednesdayT = Wednesday.getText();
            String ThursdayT = Thursday.getText();
            String FridayT = Friday.getText();
            String SaturdayT = Saturday.getText();
            String SundayT = Sunday.getText();
            dataProcessing.addAllTimeSlotstoRestaurant(mondayT, TuesdayT, WednesdayT, ThursdayT
                    , FridayT, SaturdayT, SundayT, restaurant);

            if(!ratingT.isBlank()) restaurant.setAverageRating(Float.parseFloat(ratingT));
            //REST-Api funktioniert noch nicht ganz
            // restaurantController.addRestaurant(restaurant);
            if (id != null) restaurant.setRestaurantID(id);

            JSONParse jsonParse = new JSONParse();
            jsonParse.writeJson(restaurant);

        } catch (NumberFormatException n) {
            n.printStackTrace();
            alert("Please follow the given Format");
        } catch (NullPointerException n) {
            n.printStackTrace();
        }
    }
    /*public void setRestaurant(List<Restaurant> restaurants) {
        Platform.runLater(() -> {
            var children = flowPane.getChildren();
            children.setAll(notes.stream().map(this::createNoteView).toList());
        });
    }*/

    public void alert(String alertMessage) throws NullPointerException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("An Error occured!");
        alert.setContentText(alertMessage);
        alert.showAndWait();
        throw new NullPointerException();
    }

    public void alertInfo(String alertMessage) throws NullPointerException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText("Success!");
        alert.setContentText(alertMessage);
        alert.showAndWait();
    }

    public void openToAddAdmin(ActionEvent event) throws IOException {
        URL myFXML = getClass().getClassLoader().getResource("fxml/AddUser.fxml");
        openNewSmallWindow(myFXML, "Admin management", event);
    }

    public void switchToResultView(ActionEvent event) throws IOException {
        URL myFXML = getClass().getClassLoader().getResource("fxml/ResultView.fxml");
        switchFXML(myFXML, event);
    }

    public void switchFXML(URL myFXML, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(myFXML);
        Parent root = (Parent) loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void openNewSmallWindow(URL myFXML, String title, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(myFXML);
        Parent root = (Parent) loader.load();
        Stage stageOld = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageOld.close();
        Stage stage = new Stage();
        stage.setTitle(title);
        Scene scene = new Scene(root, 400, 200);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        priceClassChoiceBox.getItems().addAll(PriceCategory.values());
        restaurantType.getItems().addAll(RestaurantType.values());
        try {
            showAllRestaurant(new ActionEvent());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
