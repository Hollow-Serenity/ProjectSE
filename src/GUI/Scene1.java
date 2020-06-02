package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Scene1 {
    @FXML
    TextField txtUserName;
    @FXML
    PasswordField txtPassWord;

    public void btnSignInClick(ActionEvent actionEvent) throws IOException{
        if(txtUserName.getText().equals("a")&&txtPassWord.getText().equals("a")) {
            Parent root2 = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene scene2 = new Scene(root2, 800, 600);
            scene2.getStylesheets().add("GUI/Light.css");
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene2);
            window.show();
        }
        else if(txtUserName.getText().isEmpty() || txtPassWord.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Please enter your login information");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Incorrect login information");
            alert.showAndWait();
        }
    }

    public void btnSignUpClick(MouseEvent mouseEvent) throws IOException {
        Parent root6 = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Scene scene6 = new Scene(root6, 300, 400);
        scene6.getStylesheets().add("GUI/Light.css");
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setScene(scene6);
        window.showAndWait();
    }
}
