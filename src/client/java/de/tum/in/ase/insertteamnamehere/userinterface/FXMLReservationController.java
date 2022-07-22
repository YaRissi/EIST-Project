package de.tum.in.ase.insertteamnamehere.userinterface;

import de.tum.in.ase.insertteamnamehere.model.*;
import de.tum.in.ase.insertteamnamehere.service.ReservationService;
import de.tum.in.ase.insertteamnamehere.user.User;
import de.tum.in.ase.insertteamnamehere.util.Credentials;
import de.tum.in.ase.insertteamnamehere.util.JSONParse;
import de.tum.in.ase.insertteamnamehere.util.SortingOptions;
import javafx.event.ActionEvent;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class FXMLReservationController implements Initializable {

    private final ReservationService reservationService = new ReservationService();

    private final RestaurantService restaurantService = new RestaurantService(new Credentials().readUser());

    @FXML
    private VBox tablesBox;

    @FXML
    private ListView<TimeSlot> timeSlotListView;

    @FXML
    private DatePicker datePicker = new DatePicker();

    private static ActionEvent showAvailableSlotsPressed;

    private static List<Reservation> reservations = new ArrayList<>();


    public void initializeTables(ActionEvent actionEvent) {

        Button pressedButton = (Button) actionEvent.getSource();
        Restaurant restaurant;
        Optional<Restaurant> optionalRestaurant = restaurantService.getRestaurants().stream().filter(r -> r.getName().equals(pressedButton.getId())).findAny();
        if(optionalRestaurant.isPresent()) {
            restaurant = optionalRestaurant.get();
        } else {
            return;
        }
        datePicker.setOnAction(event -> {
            tablesBox.getChildren().clear();

            Set<Table> tables = restaurant.getTables();

            String dateAsString = datePicker.editorProperty().getValue().getText();
            String[] monthDayYear = dateAsString.split("/");
            LocalDate date = LocalDate.of(Integer.parseInt(monthDayYear[2]), Integer.parseInt(monthDayYear[1]), Integer.parseInt(monthDayYear[0]));


            Iterator<Table> iterator = tables.iterator();
            for(int i = 0; i < tables.size(); i++) {
                Table current = iterator.next();
                VBox table = new VBox();
                Text tableID = new Text("Table: " + current.getTableID());
                Text maxPeople = new Text("Max. Person: " + current.getMaxNumberOfPeople());
                Button goToSlots = new Button("See Available Slots");
                goToSlots.setId(String.valueOf(current.getTableID() + ";" + dateAsString));
                goToSlots.setOnAction(e -> {
                    showAvailableSlotsPressed = e;
                    Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    Scene scene;
                    try {
                        scene = new Scene(new FXMLLoader(getClass().getClassLoader().getResource("fxml/SlotsView.fxml")).load());
                    } catch (IOException ioException) {
                        throw new RuntimeException(ioException);
                    }
                    stage.setScene(scene);
                    stage.show();
                });


                if(current.getAvailableTimeSlots(date).size() == 0) {
                    goToSlots.setVisible(false);
                }
                table.getChildren().addAll(tableID, maxPeople, goToSlots);
                tablesBox.getChildren().addAll(table);
            }
        });


    }

    public static ActionEvent getShowAvailableSlotsPressed() {
        return showAvailableSlotsPressed;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        JSONParse jsonParse = new JSONParse();
        restaurantService.setRestaurants(jsonParse.parseRestaurant());
        ActionEvent actionEvent = RestaurantObject.getShowTablesPressed();
        initializeTables(actionEvent);
    }

    public static List<Reservation> getReservations(){
        return reservations;
    }

    public static void deleteReservation(Reservation reservation){
        reservations.remove(reservation);
    }

    public static void addReservation(Reservation reservation){
        reservations.add(reservation);
    }
}
