package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Contacts {
    public void btnHomeClick(ActionEvent actionEvent) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene scene2 = new Scene(root2, 800, 600);
        scene2.getStylesheets().add("GUI/Light.css");
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene2);
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

    public void btnMarketplaceClick(ActionEvent actionEvent) throws IOException {
        Parent root5 = FXMLLoader.load(getClass().getResource("Marketplace.fxml"));
        Scene scene5 = new Scene(root5, 800, 600);
        scene5.getStylesheets().add("GUI/Light.css");
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene5);
        window.show();
    }
}
