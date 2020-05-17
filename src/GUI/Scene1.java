package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;

public class Scene1 {
    @FXML
    TextField txtUserName;
    @FXML
    PasswordField txtPassWord;
    @FXML
    Button btnSignIn;

    public void btnSignInClick(ActionEvent actionEvent) throws IOException{
        Parent root2 = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene scene2 = new Scene(root2, 800, 600);
        scene2.getStylesheets().add("GUI/Light.css");
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
    }
}
