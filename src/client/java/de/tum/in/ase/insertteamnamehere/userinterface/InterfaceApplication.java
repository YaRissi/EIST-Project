package de.tum.in.ase.insertteamnamehere.userinterface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

    public class InterfaceApplication extends Application {
        @Override
        public void start(Stage primaryStage) throws Exception {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ResultView.fxml")));
            primaryStage.setTitle("Reservation System");
            primaryStage.setScene(new Scene(root, 880.0, 673.0));
            primaryStage.show();
        }


    }


