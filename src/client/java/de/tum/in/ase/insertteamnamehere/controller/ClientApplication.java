package de.tum.in.ase.insertteamnamehere.controller;

import de.tum.in.ase.insertteamnamehere.view.DetailedView;
import de.tum.in.ase.insertteamnamehere.view.HomeScene;
import de.tum.in.ase.insertteamnamehere.view.MapView;
import de.tum.in.ase.insertteamnamehere.view.ReservationVIew;
import javafx.application.Application;
import javafx.stage.Stage;

public class ClientApplication extends Application {
    private final RestaurantController restaurantController = new RestaurantController();
    private final  ReservationController reservationController = new ReservationController();
    private final TableController tableController = new TableController();
    private final  UserController userController = new UserController();
    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        primaryStage.setScene(new HomeScene(this));
        primaryStage.setTitle("Willkommen beim Restaurant- Reservierungssystem");
        primaryStage.show();
    }

    public void showHomeScene(){
        stage.setScene(new HomeScene(this));


    }
    public void showDetailedView(){
        stage.setScene(new DetailedView());

    }
    public void showMapView(){
        stage.setScene(new MapView(null));

    }
    public void showReservationView(){
        stage.setScene(new ReservationView(null));

    }
}
