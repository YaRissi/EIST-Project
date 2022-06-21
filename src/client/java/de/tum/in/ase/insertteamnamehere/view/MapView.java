package de.tum.in.ase.insertteamnamehere.view;

import de.tum.in.ase.insertteamnamehere.controller.ClientApplication;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class MapView extends Scene {
    public MapView(ClientApplication application) {
        super(new VBox(),640,500);

        BackgroundImage Map = new BackgroundImage(
                new Image("C:\\Users\\pepek\\IdeaProjects\\eist22t02-insertteamnamehere92\\src\\client\\java\\de\\tum\\in\\ase\\insertteamnamehere\\view\\MünchenMap.jpg",
                        1831,783,false,true),
                BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
        var DetailedViewButton = new Button("Detailed View");
        DetailedViewButton.setOnAction(q -> application.showDetailedView());
        var ReservationViewBotton = new Button("Map View");
        ReservationViewBotton.setOnAction(q -> application.showMapView());
        var HomeViewBotton = new Button("Home View");
        HomeViewBotton.setOnAction(q -> application.showHomeScene());
        //var randomBotton = new Button("Was passiert jetztz");


        var Vbox = new VBox(18,DetailedViewButton,ReservationViewBotton,HomeViewBotton);
        Vbox.setAlignment(Pos.CENTER);
        Vbox.setBackground(new Background(Map));
        setRoot(Vbox);
    }
}
