package de.tum.in.ase.insertteamnamehere.userinterface;

import de.tum.in.ase.insertteamnamehere.model.Restaurant;
import de.tum.in.ase.insertteamnamehere.model.Review;
import de.tum.in.ase.insertteamnamehere.model.Table;
import de.tum.in.ase.insertteamnamehere.util.JSONParse;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.net.*;

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


        Button showTablesButton = new Button("Show tables");
        showTablesButton.setOnAction(e -> {
            Stage stage1 = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Scene scene = null;
            try {
                scene = new Scene(new FXMLLoader(RestaurantObject.class.getClassLoader().getResource("fxml/TablesView.fxml")).load());
            } catch (IOException ioException) {
                throw new RuntimeException(ioException);
            }
            stage1.setScene(scene);
            stage1.show();
        });
        HBox.setMargin(showTablesButton, new Insets(0, 0, 0, 10));

        HBox buttonBox = new HBox();

        Button toWebsiteButton = new Button("To Website");
        // restaurant.setWebsite("https://www.youtube.com/");
        toWebsiteButton.setOnAction(e -> {
            try {
                Desktop desk = Desktop.getDesktop();
                desk.browse(new URI("https://www.youtube.com/"));

            } catch (IOException | URISyntaxException exception) {
                exception.printStackTrace();
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
        Spinner<Integer> reviewRating = new Spinner<>();
        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5);
        spinnerValueFactory.setValue(5);
        reviewRating.setValueFactory(spinnerValueFactory);
        reviewRating.setMaxWidth(50);
        VBox.setMargin(reviewRating, new Insets(0, 0, 5, 10));

        Text reviews = new Text("Reviews (" + restaurant.getReviews().size() + ")");
        VBox.setMargin(reviews, new Insets(0, 0, 10, 0));
        reviews.setFont(new Font(17));

        VBox reviewSection = new VBox();
        reviewSection.getChildren().add(reviews);
        for (Review review : restaurant.getReviews()) {
            reviewSection.getChildren().add(createReviewObject(review, review.getRating(),restaurant));
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
                reviewSection.getChildren().add(1, createReviewObject(review, reviewRating.getValue(),restaurant));
                review.setRating(reviewRating.getValue());
                restaurant.addReview(review);
                new JSONParse().writeJson(restaurant);
                reviews.setText("Reviews (" + restaurant.getReviews().size() + ")");
                content.getChildren().remove(warning);
                writeName.clear();
                writeReview.clear();
            } catch (IllegalArgumentException | FileNotFoundException exception) {
                if (!content.getChildren().contains(warning)) {
                    content.getChildren().add(12, warning);
                }
            }
        });

        content.getChildren().addAll(restaurantName, pictures, rating, address,
                priceClass, openingTimes, toWebsiteButton, showTablesButton,
                postYourReview, writeName, writeReview, reviewRating, postButton, reviewSection);

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

    public static VBox createReviewObject(Review review, int reviewRating,Restaurant restaurant) throws FileNotFoundException {
        if (review.getName().isEmpty() || review.getContent().isEmpty()) {
            throw new IllegalArgumentException();
        }
        VBox reviewBox = new VBox();
        Text name = new Text(review.getName() + " posted:");
        name.setFont(new Font(15));
        VBox.setMargin(name, new Insets(0, 0, 5, 0));
        Text content = new Text("\"" + review.getContent() + "\"");
        content.setFont(new Font(14));
        Label rating = new Label("This user rated this restaurant with " + reviewRating + " stars.");
        VBox.setMargin(rating, new Insets(7, 0, 0, 0));
        rating.setTextFill(Color.GREY);
        HBox hBox = new HBox();
        Button likeButton = new Button("Like");
        Label numberOfLikes = new Label(review.getNumberOfLikes() + " user liked this comment!");
        numberOfLikes.setTextFill(Color.GREY);
        likeButton.setOnAction(e -> {
            review.setNumberOfLikes(review.getNumberOfLikes()+1);
            restaurant.updateReview(review);
            new JSONParse().writeJson(restaurant);
            numberOfLikes.setText(review.getNumberOfLikes() + " user liked this comment!");
        });
        HBox.setMargin(likeButton, new Insets(0, 0, 0,5));
        hBox.setPadding(new Insets(5, 0, 15, 0));
        hBox.getChildren().addAll(numberOfLikes, likeButton);
        reviewBox.getChildren().addAll(name, content, rating, hBox);
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
