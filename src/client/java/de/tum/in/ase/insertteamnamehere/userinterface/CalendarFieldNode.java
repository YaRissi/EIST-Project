package de.tum.in.ase.insertteamnamehere.userinterface;

import de.tum.in.ase.insertteamnamehere.model.Reservation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;

//Code from com.github.SirGoose3432:javafx-calendar
public class CalendarFieldNode extends AnchorPane {
    Reservation reservation;

    // Date associated with this pane
    private LocalDate date;

    public CalendarFieldNode(Node... children) {
        super(children);

        // Add action handler for mouse clicked
        this.setOnMouseClicked(e -> {
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            VBox dialogBox = new VBox(20);
            if(reservation != null) {
                Button cancelButton = new Button("Cancel reservation");
                dialogBox.getChildren().add(new Text(reservation.getDate() + "\n" + reservation.getTimeslot().getOpen().getHour() + " Uhr -" + reservation.getTimeslot().getClosed().getHour()+ " Uhr"
                        + "\n" + reservation.getTable().getRestaurant().getName()));
                dialogBox.getChildren().add(cancelButton);
                cancelButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        FXMLReservationController.deleteReservation(reservation);
                        dialog.close();
                    }
                });
                Scene dialogScene = new Scene(dialogBox,300,100);
                dialog.setScene(dialogScene);
                dialog.show();
            }
            else{
                dialogBox.getChildren().add(new Text("No reservations on this date!"));
            }
        });
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setReservation(Reservation reservation){
        this.reservation = reservation;
    }

}
