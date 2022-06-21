package de.tum.in.ase.insertteamnamehere.view;

import de.tum.in.ase.insertteamnamehere.controller.ClientApplication;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ReservationView extends Scene {
    public ReservationView(ClientApplication application) {
        super(new VBox(),640,500);
        var DetailedViewButton = new Button("Detailed View");
        DetailedViewButton.setOnAction(q -> application.showDetailedView());
        var MapViewBotton = new Button("Map View");
        MapViewBotton.setOnAction(q -> application.showMapView());
        var HomeViewBotton = new Button("Home View");
        HomeViewBotton.setOnAction(q -> application.showHomeScene());

        var Vbox = new VBox(18,DetailedViewButton,MapViewBotton,HomeViewBotton);
        Vbox.setAlignment(Pos.CENTER);
        setRoot(Vbox);

    }
}
