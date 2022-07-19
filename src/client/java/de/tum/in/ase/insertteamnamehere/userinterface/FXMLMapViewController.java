package de.tum.in.ase.insertteamnamehere.userinterface;

import com.dlsc.gmapsfx.GoogleMapView;
import com.dlsc.gmapsfx.MapComponentInitializedListener;
import com.dlsc.gmapsfx.javascript.object.*;
import de.tum.in.ase.insertteamnamehere.model.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLMapViewController implements Initializable, MapComponentInitializedListener {

    @FXML
    private Button backToResultView;

    private List<Restaurant> searchResult;

    @FXML
    public GoogleMapView MapView = new GoogleMapView();

    public FXMLMapViewController() {
    }

    public void switchToResultView(ActionEvent event) throws IOException {
        URL myFXML = getClass().getClassLoader().getResource("fxml/ResultView.fxml");
        FXMLLoader loader = new FXMLLoader(myFXML);
        Parent root = (Parent) loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void mapInitialized() {
        LatLong joeSmithLocation = new LatLong(48.13534994382753, 11.579664625269427);


        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(48.13534994382753, 11.579664625269427))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);

        GoogleMap map = MapView.createMap(mapOptions);

        //Add markers to the map
        for (Restaurant r : searchResult) {
            LatLong position = new LatLong(r.getLocation().getLat(), r.getLocation().getLon());

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(position);

            Marker newMarker = new Marker(markerOptions);
            map.addMarker(newMarker);

            InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
            infoWindowOptions.content(r.getName() + "\n" + r.getAddress() + "\n" + r.getWebsite() + "\n" + r.getAverageRating());
            InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);

            fredWilkeInfoWindow.open(map, newMarker);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MapView.addMapInitializedListener(this);
    }
}
