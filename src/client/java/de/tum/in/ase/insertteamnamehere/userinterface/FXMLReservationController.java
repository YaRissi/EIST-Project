package de.tum.in.ase.insertteamnamehere.userinterface;

import de.tum.in.ase.insertteamnamehere.model.*;
import de.tum.in.ase.insertteamnamehere.service.ReservationService;
import de.tum.in.ase.insertteamnamehere.user.User;
import javafx.event.ActionEvent;
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
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class FXMLReservationController implements Initializable {

    private final ReservationService reservationService = new ReservationService();

    private final RestaurantService restaurantService = new RestaurantService(new User("Bob", null, null));

    @FXML
    private TextField start;
    @FXML
    private TextField end;
    @FXML
    private Button makeAReservation = new Button("Make a Reservation");

    @FXML
    private VBox tablesBox;

    @FXML
    private ListView<TimeSlot> timeSlotListView;

    @FXML
    private DatePicker datePicker = new DatePicker();


   private void initializeTables(Restaurant restaurant) {
        datePicker.setOnAction(event -> {
            tablesBox.getChildren().clear();

            Set<Table> tables = restaurant.getTables();
            if (tables == null) {
                return;
            }

            String dateAsString = datePicker.editorProperty().getValue().getText();
            String[] monthDayYear = dateAsString.split("/");
            LocalDate date = LocalDate.of(Integer.getInteger(monthDayYear[2]), Integer.getInteger(monthDayYear[0]), Integer.getInteger(monthDayYear[1]));


            Iterator<Table> iterator = tables.iterator();
            for(int i = 0; i < tables.size(); i++) {
                Table current = iterator.next();
                Text id = new Text(String.valueOf(current.getTableID()));
                Text maxPeople = new Text(String.valueOf(current.getMaxNumberOfPeople()));

                Button goToSlots = new Button("See Available Slots");
                goToSlots.setOnAction(e -> {
                    Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    Scene scene;
                    try {
                        scene = new Scene(new FXMLLoader(getClass().getClassLoader().getResource("fxml/SlotsView.fxml")).load());
                    } catch (IOException ioException) {
                        throw new RuntimeException(ioException);
                    }
                    stage.setScene(scene);
                    stage.show();
                    initializeAvailableSlots(date, current);
                });


                if(current.getAvailableTimeSlots(date).size() == 0) {
                    goToSlots.setVisible(false);
                }

                VBox tableBox = new VBox();
                tableBox.getChildren().addAll(id, maxPeople, goToSlots);
                tablesBox.getChildren().add(tableBox);
            }
        });
    }

    private void initializeAvailableSlots(LocalDate date, Table table) {
        timeSlotListView.getItems().clear();
        List<TimeSlot> slots = table.getAvailableTimeSlots(date);
        timeSlotListView.getItems().addAll(slots);
    }

    private void handleMakeReservationEvent(User user, Restaurant restaurant, Table table, LocalDate date, ActionEvent actionEvent) {
        makeAReservation.setOnAction(event -> {
            String startTime = start.getText();
            String endTime = end.getText();
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(Date.valueOf(date));
            TimeSlot timeSlot = new TimeSlot(LocalTime.of(Integer.getInteger(startTime.split(":")[0]), Integer.getInteger(startTime.split(":")[0])),
                    LocalTime.of(Integer.getInteger(endTime.split(":")[0]), Integer.getInteger(endTime.split(":")[1])));
            Reservation reservation = new Reservation(user, timeSlot, table, table.getMaxNumberOfPeople(), UUID.randomUUID(), gregorianCalendar);
            reservationService.saveReservation(reservation);
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       for (Restaurant restaurant : restaurantService.getRestaurants()) {
           initializeTables(restaurant);
       }
    }

}
