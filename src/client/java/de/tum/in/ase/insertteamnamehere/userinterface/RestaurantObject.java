package de.tum.in.ase.insertteamnamehere.userinterface;

import de.tum.in.ase.insertteamnamehere.model.Restaurant;
import de.tum.in.ase.insertteamnamehere.model.Table;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class RestaurantObject {

    public static void display(Restaurant restaurant) throws IOException {
        Stage stage = new Stage();
        BorderPane borderPane = new BorderPane();

        Button closeButton = new Button("Close");
        borderPane.setTop(closeButton);

        VBox content = new VBox();
        Text restaurantName = new Text(restaurant.getName());

        HBox pictures = new HBox();
        ImageView imageView = new ImageView();
        pictures.getChildren().add(imageView);

        Text rating = new Text("Rating: " + restaurant.getAverageRating());
        Text address = new Text("Address: " + restaurant.getAddress());
        Text priceClass = new Text("Price " + restaurant.getPriceCategory().toString());
        Text openingTimes = new Text("Opening Times: ");

        ListView<VBox> tables = new ListView<>();
        Button showTables = new Button("Show tables");
        showTables.setOnAction(e -> {
            tables.getItems().clear();
            for (Table table : restaurant.getTables()) {
                tables.getItems().add(createTableObject(table));
            }
        });

        Button toReservationButton = new Button("To Reservation");
        toReservationButton.setOnAction(e -> {

        });

        Button toWebsiteButton = new Button("To Website");
        toWebsiteButton.setOnAction(e -> {

        });

        Text reviews = new Text("Reviews");
        TextField writeReview = new TextField();
        Button postButton = new Button("Post");

        content.getChildren().add(restaurantName);
        content.getChildren().add(pictures);
        content.getChildren().add(rating);
        content.getChildren().add(address);
        content.getChildren().add(priceClass);
        content.getChildren().add(openingTimes);
        content.getChildren().add(showTables);
        content.getChildren().add(tables);
        content.getChildren().add(toReservationButton);
        content.getChildren().add(toWebsiteButton);
        content.getChildren().add(reviews);
        content.getChildren().add(writeReview);
        content.getChildren().add(postButton);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(content);
        borderPane.setCenter(scrollPane);

        Scene scene = new Scene(borderPane, 576.0, 617.0);


        stage.setTitle(restaurant.getName());
        stage.setScene(scene);
        stage.show();
    }

    public static void createCommentObject() {

    }

    public static VBox createTableObject(Table table) {
        VBox tableObject = new VBox();
        Text tableID = new Text("Table " + table.getTableID());
        Text maxPersons = new Text("Max. Person: " + table.getMaxNumberOfPeople());
        tableObject.getChildren().addAll(tableID, maxPersons);
        return tableObject;
    }

}
