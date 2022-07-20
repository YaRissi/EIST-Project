package de.tum.in.ase.insertteamnamehere.userinterface;

import de.tum.in.ase.insertteamnamehere.model.Restaurant;
import de.tum.in.ase.insertteamnamehere.model.Review;
import de.tum.in.ase.insertteamnamehere.model.Table;
import de.tum.in.ase.insertteamnamehere.util.JSONParse;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class RestaurantObject {

    public static void display(Restaurant restaurant) throws IOException {
        Stage stage = new Stage();
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(5, 5, 5, 5));
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
        restaurantName.setFont(new Font(24));
        VBox.setMargin(restaurantName, new Insets(5, 10, 5, 10));

        HBox pictures = new HBox();

        FileInputStream fileInputStream =
                new FileInputStream("src/client/resources/pictures/restaurant-clipart-7-transparent.png");
        ImageView imageView = new ImageView(new Image(fileInputStream));
        imageView.setFitHeight(75);
        imageView.setFitWidth(75);
        pictures.getChildren().add(imageView);

        Text rating = new Text("Rating: " + restaurant.getAverageRating());
        VBox.setMargin(rating, new Insets(10, 10, 5, 10));
        Text address = new Text("Address: " + restaurant.getAddress());
        VBox.setMargin(address, new Insets(0, 10, 5, 10));
        Text priceClass = new Text("Price: " + restaurant.getPriceCategory().toString());
        VBox.setMargin(priceClass, new Insets(0, 10, 5, 10));
        Text openingTimes = new Text("Opening Times: ");
        VBox.setMargin(openingTimes, new Insets(0, 10, 5, 10));


        // TODO Implement showTableButton (delete comments if no need)
        /* Button showTablesButton = new Button("Show tables");
        showTablesButton.setOnAction(e -> {

        });
        HBox.setMargin(showTablesButton, new Insets(0, 0, 0, 10));

        HBox buttonBox = new HBox();

        Button toReservationButton = new Button("To Reservation");
        toReservationButton.setOnAction(e -> {

        });
        HBox.setMargin(toReservationButton, new Insets(0, 10, 0, 5)); */

        Button toWebsiteButton = new Button("To Website");
        toWebsiteButton.setOnAction(e -> {
            URL url;
            try {
                String restaurantURL = restaurant.getWebsite();
                url = new URL(restaurantURL);
                URLConnection connection = url.openConnection();

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));

                String inputLine;
                while ((inputLine = reader.readLine()) != null) {
                    System.out.println(inputLine);
                }
                reader.close();

                System.out.println("Done");

            } catch (IOException exception) {
                exception.printStackTrace();
            } catch (NullPointerException exception) {
                System.out.println("The URL was null...");
            }
        });
        VBox.setMargin(toWebsiteButton, new Insets(5, 0, 10, 10));

        // buttonBox.getChildren().addAll(showTablesButton, toReservationButton);

        Text postYourReview = new Text("Post your own review");
        VBox.setMargin(postYourReview, new Insets(0, 0, 5, 10));
        TextField writeName = new TextField();
        writeName.setPromptText("Type your name");
        writeName.setMaxWidth(100);
        VBox.setMargin(writeName, new Insets(0, 0, 5, 10));
        TextField writeReview = new TextField();
        writeReview.setPromptText("Write your review");
        writeName.setMaxWidth(250);
        VBox.setMargin(writeReview, new Insets(0, 30, 5, 10));

        Text reviews = new Text("Reviews (" + restaurant.getReviews().size() + ")");
        VBox.setMargin(reviews, new Insets(0, 0,10,0));
        reviews.setFont(new Font(17));

        VBox reviewSection = new VBox();
        reviewSection.getChildren().add(reviews);
        for (Review review : restaurant.getReviews()) {
            reviewSection.getChildren().add(createReviewObject(review));
        }
        reviewSection.setPadding(new Insets(10, 10, 10, 10));

        Label warning = new Label("One of you inputs is empty!");
        VBox.setMargin(warning, new Insets(0, 0, 5, 10));
        warning.setTextFill(Color.RED);

        Button postButton = new Button("Post");
        VBox.setMargin(postButton, new Insets(0, 30, 5, 10));
        postButton.setOnAction(e -> {
            Review review = new Review(writeName.getText(), writeReview.getText());
            try {
                reviewSection.getChildren().add(1, createReviewObject(review));
                restaurant.addReview(review);
                new JSONParse().writeJson(restaurant);
                reviews.setText("Reviews (" + restaurant.getReviews().size() + ")");
                content.getChildren().remove(warning);
                writeName.clear();
                writeReview.clear();
            } catch (IllegalArgumentException exception) {
                if (!content.getChildren().contains(warning)) {
                    content.getChildren().add(12, warning);
                }
            }
        });

        content.getChildren().addAll(restaurantName, pictures, rating, address,
                priceClass, openingTimes, toWebsiteButton,
                postYourReview, writeName, writeReview, postButton, reviewSection);

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

    public static VBox createReviewObject(Review review) {
        if (review.getName().isEmpty() || review.getContent().isEmpty()) {
            throw new IllegalArgumentException();
        }
        VBox reviewBox = new VBox();
        Text name = new Text(review.getName() + " posted:");
        name.setFont(new Font(15));
        Text content = new Text("\"" + review.getContent() + "\"" + "\n");
        reviewBox.getChildren().addAll(name, content);
        return reviewBox;
    }

    public static VBox createTableObject(Table table) {
        VBox tableObject = new VBox();
        Text tableID = new Text("Table " + table.getTableID());
        Text maxPersons = new Text("Max. Person: " + table.getMaxNumberOfPeople());
        tableObject.getChildren().addAll(tableID, maxPersons);
        return tableObject;
    }

}
