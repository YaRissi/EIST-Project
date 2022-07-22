package de.tum.in.ase.insertteamnamehere.userinterface;

import com.sothawo.mapjfx.Coordinate;
import com.sothawo.mapjfx.MapLabel;
import com.sothawo.mapjfx.MapView;
import com.sothawo.mapjfx.Marker;
import com.sothawo.mapjfx.event.MarkerEvent;
import de.tum.in.ase.insertteamnamehere.model.Reservation;
import de.tum.in.ase.insertteamnamehere.model.Restaurant;
import de.tum.in.ase.insertteamnamehere.model.RestaurantType;
import de.tum.in.ase.insertteamnamehere.model.TimeSlot;
import de.tum.in.ase.insertteamnamehere.user.User;
import de.tum.in.ase.insertteamnamehere.util.Coord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FXMLMapViewController {

    @FXML
    public MapView mapView = new MapView();

    private List<Marker> markers = new ArrayList<>();

    public void initMapAndControls(){
        mapView.initializedProperty().addListener((observable, oldvalue, newvalue) -> {
            if(newvalue){
                afterMapInitialized();
            }
        });
        mapView.initialize();
    }

    public void afterMapInitialized() {
        for(Restaurant r: FXMLInterfaceController.getSearchResult()){
            Marker newMarker = Marker.createProvided(Marker.Provided.RED);
            newMarker.setPosition(new Coordinate((double)r.getLocation().getLat(),(double)r.getLocation().getLon()));
            newMarker.setVisible(true);
            MapLabel newLabel = new MapLabel(r.getName());
            newMarker.attachLabel(newLabel);
            markers.add(newMarker);
            mapView.addMarker(newMarker);
        }
        mapView.setCenter(new Coordinate(48.13534994382753, 11.579664625269427));
        for(Marker m : markers){
            mapView.addMarker(m);
        }
    }

    public void switchToResultView(ActionEvent event) throws IOException {
        URL myFXML = getClass().getClassLoader().getResource("fxml/ResultView.fxml");
        FXMLLoader loader = new FXMLLoader(myFXML);
        Parent root = (Parent) loader.load();
        FXMLInterfaceController interfaceController = loader.getController();
        interfaceController.fillResultView(FXMLInterfaceController.getSearchResult());
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToCalendarView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/CalendarView.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Reservation calendar");
        stage.setScene(new Scene(root));
        FXMLCalendarViewController controller = loader.getController();
        controller.calendarPane.getChildren().add(new CalendarViewTemplate(YearMonth.now()).getView());
        stage.show();
    }

    public List<Marker> getMarkers() {
        return markers;
    }

    public void setMarkers(List<Marker> markers) {
        this.markers = markers;
    }

}
