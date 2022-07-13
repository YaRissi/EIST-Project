package de.tum.in.ase.insertteamnamehere.userinterface;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {

    public static void display() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Confirm Reservation");

        Text email = new Text("Please enter your email address");
        TextField enterEmail = new TextField();
    }
}
