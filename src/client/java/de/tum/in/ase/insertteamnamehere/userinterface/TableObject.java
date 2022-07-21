package de.tum.in.ase.insertteamnamehere.userinterface;

import de.tum.in.ase.insertteamnamehere.model.Restaurant;
import de.tum.in.ase.insertteamnamehere.model.Table;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;

public class TableObject {

    public static void display(Restaurant restaurant) {
        Stage stage = new Stage();
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(600, 400);

        VBox tablesBox = new VBox();
        tablesBox.setLayoutX(104.0);
        tablesBox.setLayoutX(39.0);
        tablesBox.setPrefSize(496, 361.0);
        AnchorPane.setBottomAnchor(tablesBox, 0.0);
        AnchorPane.setLeftAnchor(tablesBox, 104.0);
        AnchorPane.setRightAnchor(tablesBox, 0.0);
        AnchorPane.setTopAnchor(tablesBox, 39.0);

        Text textSeeTables = new Text("See tables below:");
        textSeeTables.setStrokeType(StrokeType.OUTSIDE);
        textSeeTables.setStrokeWidth(0.0);
        tablesBox.setLayoutX(307.0);
        tablesBox.setLayoutX(27.0);


        VBox sideBox = new VBox();
        sideBox.setLayoutX(489.0);
        sideBox.setLayoutX(7.0);
        sideBox.setPrefSize(100, 200);
        AnchorPane.setBottomAnchor(sideBox, 0.0);
        AnchorPane.setLeftAnchor(sideBox, 0.0);
        AnchorPane.setTopAnchor(sideBox, 0.0);

        Text textPickDate = new Text("Please pick a date below to see available tables.");
        textSeeTables.setStrokeType(StrokeType.OUTSIDE);
        textSeeTables.setStrokeWidth(0.0);
        textPickDate.setWrappingWidth(99.76110076904297);
        VBox.setMargin(textPickDate, new Insets(5.0, 0, 10.0, 2.0));

        DatePicker datePicker = new DatePicker();
        datePicker.setOnAction(event -> {
            tablesBox.getChildren().clear();

            Set<Table> tables = restaurant.getTables();
            if (tables == null) {
                return;
            }

            String dateAsString = datePicker.editorProperty().getValue().getText();
            String[] monthDayYear = dateAsString.split("/");
            // LocalDate date = LocalDate.of(Integer.getInteger(monthDayYear[2]), Integer.getInteger(monthDayYear[0]), Integer.getInteger(monthDayYear[1]));

            Iterator<Table> iterator = tables.iterator();
            for(int i = 0; i < tables.size(); i++) {
                Table current = iterator.next();
                Text id = new Text(String.valueOf(current.getTableID()));
                Text maxPeople = new Text(String.valueOf(current.getMaxNumberOfPeople()));

                Button goToSlots = new Button("See Available Slots");
                /*goToSlots.setOnAction(e -> {
                    Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    Scene scene;
                    try {
                        scene = new Scene(new FXMLLoader(getClass().getClassLoader().getResource("fxml/SlotsView.fxml")).load());
                    } catch (IOException ioException) {
                        throw new RuntimeException(ioException);
                    }
                    stage.setScene(scene);
                    stage.show();
                    initializeAvailableSlots(date, current);
                });


                if(current.getAvailableTimeSlots(date).size() == 0) {
                    goToSlots.setVisible(false);
                } */

                VBox tableBox = new VBox();
                tableBox.getChildren().addAll(id, maxPeople, goToSlots);
                tablesBox.getChildren().add(tableBox);
            }
        });
        VBox.setMargin(datePicker, new Insets(0, 0, 0, 2.0));

        sideBox.getChildren().addAll(textPickDate, datePicker);

        anchorPane.getChildren().addAll(tablesBox, sideBox);

        Scene scene = new Scene(anchorPane, 600, 400);

        stage.setTitle(restaurant.getName());
        stage.setScene(scene);
        stage.show();
    }
}
