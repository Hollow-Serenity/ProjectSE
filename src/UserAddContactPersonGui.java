import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.SQLException;

public class UserAddContactPersonGui extends Application {
    Label addedContactLabel = new Label();

    @Override
    public void start(Stage stage) throws Exception {
        Label firstNameLabel = new Label("First name");
        TextField firstNameTextField = new TextField();
        Label lastNameLabel = new Label("Last name");
        TextField lastNameTextField = new TextField();
        Label phoneNumberLabel = new Label("Phone number");
        TextField phoneNumberTextField = new TextField();
        Button addContactButton = new Button("Add contact");

        addContactButton.setOnAction(actionEvent -> {
            try {
                User.addPerson(new Person(firstNameTextField.getText(), lastNameTextField.getText(), phoneNumberTextField.getText()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            addedContactLabel.setText("Successfully added contact");
        });

        HBox hBoxFirstName = new HBox(20);
        hBoxFirstName.getChildren().addAll(firstNameLabel, firstNameTextField);
        hBoxFirstName.setAlignment(Pos.CENTER);
        HBox hBoxLastName = new HBox(20);
        hBoxLastName.getChildren().addAll(lastNameLabel, lastNameTextField);
        hBoxLastName.setAlignment(Pos.CENTER);
        HBox hBoxPhoneNumber = new HBox(20);
        hBoxPhoneNumber.getChildren().addAll(phoneNumberLabel, phoneNumberTextField);
        hBoxPhoneNumber.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(hBoxFirstName, hBoxLastName, hBoxPhoneNumber, addContactButton, addedContactLabel);
        vBox.setPadding(new Insets(20, 20, 20, 20));
        vBox.setAlignment(Pos.CENTER);
        vBox.setMinSize(300, 200);

        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.setMinWidth(350);
        stage.setMinHeight(250);
        stage.centerOnScreen();
        stage.show();
    }
}
