package de.tum.in.ase.insertteamnamehere.userinterface;

import de.tum.in.ase.insertteamnamehere.model.Reservation;
import de.tum.in.ase.insertteamnamehere.model.Restaurant;
import de.tum.in.ase.insertteamnamehere.model.Table;
import de.tum.in.ase.insertteamnamehere.model.TimeSlot;
import de.tum.in.ase.insertteamnamehere.service.ReservationService;
import de.tum.in.ase.insertteamnamehere.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

public class FXMLReservationController {

    private final ReservationService reservationService = new ReservationService();

    @FXML
    private Button goToSlotsView;

    @FXML
    private TextField start;
    @FXML
    private TextField end;
    @FXML
    private Button makeAReservation;

    @FXML
    private TableView<Table> tablesView;

    @FXML
    private TableColumn<Table, String> tableID;

    @FXML
    private TableColumn<Table, Integer> tableMaxPeople;

    @FXML
    private TableColumn<Table, Button> chooseSlotButtonColumn;

    @FXML
    private TableView<Object> timeSlotsTableView;

    @FXML
    private TableColumn<Object, TimeSlot> timeSlotsColumn;

    @FXML
    private DatePicker datePicker;


    private void handleGoToSlotsView(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(new FXMLLoader(getClass().getClassLoader().getResource("fxml/SlotsView.fxml")).load());
        stage.setScene(scene);
        stage.show();
    }

    private void initializeTables(Restaurant restaurant) {
        String dateAsString = datePicker.editorProperty().getValue().getText();
        String[] monthDayYear = dateAsString.split("/");
        LocalDate date = LocalDate.of(Integer.getInteger(monthDayYear[2]), Integer.getInteger(monthDayYear[0]), Integer.getInteger(monthDayYear[1]));
        tablesView.getItems().addAll(restaurant.getTables());

        chooseSlotButtonColumn.setCellFactory(param -> {
            TableCell<Table, Button> buttonTableCell = new TableCell<>();
            buttonTableCell.setItem(new Button("See Available Slots"));
            return buttonTableCell;
        });

        for(int i = 0; i < tablesView.getItems().size(); i++) {
            Table current = tablesView.getItems().get(i);
            chooseSlotButtonColumn.getCellFactory().call(chooseSlotButtonColumn).setItem(new Button("See Available Slots"));
            if(current.getAvailableTimeSlots(date).size() == 0) {
                chooseSlotButtonColumn.getCellObservableValue(current).getValue().setVisible(false);
            }
        }
    }

    private void initializeAvailableSlots(LocalDate date, Table table) {
        List<TimeSlot> slots = table.getAvailableTimeSlots(date);
        timeSlotsTableView.getItems().addAll(slots);
    }

    private void handleMakeReservationEvent(User user, Restaurant restaurant, Table table, LocalDate date, ActionEvent actionEvent) {
        String startTime = start.getText();
        String endTime = end.getText();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(Date.valueOf(date));
        TimeSlot timeSlot = new TimeSlot(LocalTime.of(Integer.getInteger(startTime.split(":")[0]), Integer.getInteger(startTime.split(":")[0])),
                LocalTime.of(Integer.getInteger(endTime.split(":")[0]), Integer.getInteger(endTime.split(":")[1])));
        Reservation reservation = new Reservation(user, timeSlot, table, table.getMaxNumberOfPeople(), UUID.randomUUID(), gregorianCalendar);
        reservationService.saveReservation(reservation);
    }
}
