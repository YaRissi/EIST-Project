package de.tum.in.ase.insertteamnamehere.userinterface;

import com.github.cliftonlabs.json_simple.JsonException;
import de.tum.in.ase.insertteamnamehere.user.User;
import de.tum.in.ase.insertteamnamehere.util.Coord;
import de.tum.in.ase.insertteamnamehere.util.Credentials;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

public class FXMLLoginController {
    @FXML
    public TextField password;
    @FXML
    public TextField username;
    @FXML
    public TextField usernameADD;
    @FXML
    public TextField passwordADD;

    @FXML
    public TextField firstname;
    @FXML
    public TextField lastname;
    @FXML
    public TextField email;


    public void openResultView(ActionEvent event) throws IOException {
        String firstnameText = firstname.getText();
        String lastnameText = lastname.getText();
        String emailText = email.getText();

        if (firstnameText.isBlank() || lastnameText.isBlank()) alert("Please enter you first and last name");

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (emailText.isBlank() || !pat.matcher(emailText).matches()) alert("Please enter a valid email");

        User user = new User(firstnameText + " " + lastnameText, null, new Coord((float) 48.2656027, (float) 11.6709969));

        user.setEmail(emailText);

        new Credentials().writeUser(user);

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/ResultView.fxml"));
        Parent root = (Parent) loader.load();
        Stage stageOld = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageOld.close();
        Stage stage = new Stage();
        stage.setTitle("Reservation System");
        Scene scene = new Scene(root, 880.0, 673.0);
        stage.setScene(scene);
        stage.show();

    }

    public void login(ActionEvent event) throws IOException, JsonException, NoSuchAlgorithmException {
        String usernameText = username.getText();
        String passwordText = password.getText();
        Credentials credentials = new Credentials();
        if (credentials.checkLogin(usernameText, passwordText)) {
            switchToFromSmallToAdminView(event);
        } else {
            alert("Wrong username or password");
        }

    }

    public void addAdmin(ActionEvent actionEvent) throws NoSuchAlgorithmException, IOException {
        String username = usernameADD.getText();
        String password = passwordADD.getText();

        Credentials credentials = new Credentials();

        credentials.addLogin(username, password);
        switchToFromSmallToAdminView(actionEvent);
    }

    public void switchToFromSmallToResultView(ActionEvent event) throws IOException {
        URL myFXML = getClass().getClassLoader().getResource("fxml/ResultView.fxml");
        openNewBigWindow(myFXML, "Reservation System", event);
    }

    public void switchToFromSmallToAdminView(ActionEvent event) throws IOException {
        URL myFXML = getClass().getClassLoader().getResource("fxml/AdminView.fxml");
        openNewBigWindow(myFXML, "Reservation System", event);
    }

    public void openNewBigWindow(URL myFXML, String title, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(myFXML);
        Parent root = (Parent) loader.load();
        Stage stageOld = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageOld.close();
        Stage stage = new Stage();
        stage.setTitle(title);
        Scene scene = new Scene(root, 880.0, 673.0);
        stage.setScene(scene);
        stage.show();
    }


    public void alert(String alertMessage) throws NullPointerException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("An Error occured!");
        alert.setContentText(alertMessage);
        alert.showAndWait();
        throw new NullPointerException();
    }
}
