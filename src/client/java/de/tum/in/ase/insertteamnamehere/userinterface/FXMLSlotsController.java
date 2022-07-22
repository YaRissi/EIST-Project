package de.tum.in.ase.insertteamnamehere.userinterface;

import de.tum.in.ase.insertteamnamehere.model.*;
import de.tum.in.ase.insertteamnamehere.service.ReservationService;
import de.tum.in.ase.insertteamnamehere.user.User;
import de.tum.in.ase.insertteamnamehere.util.Credentials;
import de.tum.in.ase.insertteamnamehere.util.JSONParse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class FXMLSlotsController implements Initializable {

    @FXML
    private Text start;
    @FXML
    private Text end;

    @FXML
    private TextField startInput;

    @FXML
    private TextField endInput;
    @FXML
    private Button makeAReservation = new Button("Make a Reservation");

    @FXML
    private ListView<TimeSlot> timeSlotListView;

    private final ReservationService reservationService = new ReservationService();

    private final RestaurantService restaurantService = new RestaurantService(new Credentials().readUser());

    private static List<Reservation> reservations = new ArrayList<>();

    private Table openedTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        JSONParse jsonParse = new JSONParse();
        restaurantService.setRestaurants(jsonParse.parseRestaurant());
        ActionEvent actionEvent = FXMLReservationController.getShowAvailableSlotsPressed();
        handleMakeReservationEvent();
        initializeAvailableSlots(actionEvent);
    }

    private void initializeAvailableSlots(ActionEvent actionEvent) {
        Button pressedButton = (Button) actionEvent.getSource();
        String[] idDate = pressedButton.getId().split(";");
        timeSlotListView.getItems().clear();
        Table table = null;
        for(int i = 0; i < restaurantService.getRestaurants().size(); i++) {
            Restaurant current = restaurantService.getRestaurants().get(i);
            for(int j = 0; j < current.getTables().size(); j++) {
                Iterator<Table> iterator = current.getTables().iterator();
                Table currentTable = iterator.next();
                if(String.valueOf(currentTable.getTableID()).equals(idDate[0])) {
                    table = currentTable;
                    openedTable = table;
                    break;
                }
            }
        }
        if(table == null) {
            return;
        }
        String dateAsString = idDate[1];
        String[] monthDayYear = dateAsString.split("/");
        LocalDate date = LocalDate.of(Integer.parseInt(monthDayYear[2]), Integer.parseInt(monthDayYear[1]), Integer.parseInt(monthDayYear[0]));
        timeSlotListView.getItems().clear();
        List<TimeSlot> slots = table.getAvailableTimeSlots(date);
        timeSlotListView.getItems().addAll(slots);
        timeSlotListView.setVisible(true);
    }

    private void handleMakeReservationEvent() {
        makeAReservation.setOnAction(event -> {
            String startTime = startInput.getText();
            String endTime = endInput.getText();
            TimeSlot timeSlot = new TimeSlot(LocalTime.of(Integer.parseInt(startTime.split(":")[0]), Integer.parseInt(startTime.split(":")[1])),
                    LocalTime.of(Integer.parseInt(endTime.split(":")[0]), Integer.parseInt(endTime.split(":")[1])));
            Reservation reservation = new Reservation(new Credentials().readUser(), timeSlot, openedTable, openedTable.getMaxNumberOfPeople(), UUID.randomUUID(), FXMLReservationController.getDateChosen());
            reservationService.saveReservation(reservation);
            reservations.add(reservation);
        });
    }

    public static List<Reservation> getReservations(){
        return reservations;
    }

    public static void deleteReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    public static void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public void setOpenedTable(Table openedTable) {
        this.openedTable = openedTable;
    }

    public Table getOpenedTable() {
        return openedTable;
    }
}
