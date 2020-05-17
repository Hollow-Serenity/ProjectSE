package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Marketplace {

    public void btnHomeClick(ActionEvent actionEvent) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene scene2 = new Scene(root2, 800, 600);
        scene2.getStylesheets().add("GUI/Light.css");
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
    }

    public void btnContactsClick(ActionEvent actionEvent) throws IOException {
        Parent root3 = FXMLLoader.load(getClass().getResource("Contacts.fxml"));
        Scene scene3 = new Scene(root3, 800, 600);
        scene3.getStylesheets().add("GUI/Light.css");
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene3);
        window.show();
    }

    public void btnLivestockClick(ActionEvent actionEvent) throws IOException {
        Parent root4 = FXMLLoader.load(getClass().getResource("Livestock.fxml"));
        Scene scene4 = new Scene(root4, 800, 600);
        scene4.getStylesheets().add("GUI/Light.css");
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene4);
        window.show();
    }

    public void btnSignOffClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("GUI/Light.css");
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
