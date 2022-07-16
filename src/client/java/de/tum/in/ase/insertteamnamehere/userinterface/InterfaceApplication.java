package de.tum.in.ase.insertteamnamehere.userinterface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class InterfaceApplication extends Application {
        private Stage stage;
        private Scene scene;
        private Parent root;
        @Override
        public void start(Stage primaryStage) throws Exception {
            URL myFXML = getClass().getClassLoader().getResource("fxml/ResultView.fxml");
            FXMLLoader loader = new FXMLLoader(myFXML);
            root = (Parent) loader.load();
            primaryStage.setTitle("Reservation System");
            primaryStage.setScene(new Scene(root, 880.0, 673.0));
            primaryStage.show();
        }

    }


