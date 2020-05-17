package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Livestock {
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

    public void btnMarketplaceClick(ActionEvent actionEvent) throws IOException {
        Parent root5 = FXMLLoader.load(getClass().getResource("Marketplace.fxml"));
        Scene scene5 = new Scene(root5, 800, 600);
        scene5.getStylesheets().add("GUI/Light.css");
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene5);
        window.show();
    }
}
