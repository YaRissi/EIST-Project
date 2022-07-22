package de.tum.in.ase.insertteamnamehere.userinterface;

import com.sothawo.mapjfx.Coordinate;
import com.sothawo.mapjfx.MapView;
import com.sothawo.mapjfx.Marker;
import de.tum.in.ase.insertteamnamehere.model.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FXMLMapViewController {

    @FXML
    public MapView mapView = new MapView();

    private List<Restaurant> searchResult = new ArrayList<>();
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
        for(Restaurant r: searchResult){
            Marker newMarker = Marker.createProvided(Marker.Provided.RED);
            newMarker.setPosition(new Coordinate((double)r.getLocation().getLat(),(double)r.getLocation().getLon()));
            newMarker.setVisible(true);
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
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
