package de.tum.in.ase.insertteamnamehere.userinterface;

import de.tum.in.ase.insertteamnamehere.model.Restaurant;
import de.tum.in.ase.insertteamnamehere.model.Table;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class RestaurantObject {

    public static void display(Restaurant restaurant) throws IOException {
        Stage stage = new Stage();
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(5,5,5,5));
        Region region = new Region();
        region.setPrefWidth(50);

        HBox topContainer = new HBox();
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> {
            stage.close();
        });

        topContainer.getChildren().addAll(region, closeButton);
        HBox.setHgrow(region, Priority.ALWAYS);
        borderPane.setTop(topContainer);

        VBox content = new VBox();
        Text restaurantName = new Text(restaurant.getName());
        restaurantName.setStyle("-fx-font: 24 arial;");
        VBox.setMargin(restaurantName, new Insets(5, 10, 5, 10));

        HBox pictures = new HBox();

        /* FileInputStream fileInputStream =
                new FileInputStream("src/client/java/de/tum/in/ase/insertteamnamehere/userinterface/pictures/restaurant-clipart-7-transparent.png");
        ImageView imageView = new ImageView(new Image(fileInputStream));
        imageView.setFitHeight(75);
        imageView.setFitWidth(75);
        pictures.getChildren().add(imageView); */

        Text rating = new Text("Rating: " + restaurant.getAverageRating());
        Text address = new Text("Address: " + restaurant.getAddress());
        Text priceClass = new Text("Price " + restaurant.getPriceCategory().toString());
        Text openingTimes = new Text("Opening Times: ");
        Text description = new Text("Description:\nDies hier ist ein tolles Restaurant mit einem Rating von "
                + restaurant.getAverageRating());

        // ListView<VBox> tables = new ListView<>();
        Button showTables = new Button("Show tables");
        showTables.setOnAction(e -> {
            /* tables.getItems().clear();
            /* tables.getItems().clear();
            for(Table table : restaurant.getTables()) {
                tables.getItems().add(createTableObject(table));
            }
            } */
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

        content.getChildren().addAll(restaurantName, pictures, rating, address,
                priceClass, description, openingTimes, showTables, toReservationButton, toWebsiteButton,
                reviews, writeReview, postButton);

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
