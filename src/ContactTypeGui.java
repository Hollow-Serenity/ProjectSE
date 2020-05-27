import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ContactTypeGui extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);

        HBox hBox = new HBox(40);
        hBox.setAlignment(Pos.CENTER);

        Label chooseLabel = new Label("What kind of contact would you like to add");

        Button personButton = new Button("Person");
        personButton.setOnAction(actionEvent -> {
            UserAddContactPersonGui userAddContactPersonGui = new UserAddContactPersonGui();
            try {
                userAddContactPersonGui.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Button companyButton = new Button("Company");
        companyButton.setOnAction(actionEvent -> {
            UserAddContactCompanyGui userAddContactCompanyGui = new UserAddContactCompanyGui();
            try {
                userAddContactCompanyGui.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        hBox.getChildren().addAll(personButton, companyButton);
        vBox.getChildren().addAll(chooseLabel, hBox);

        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.setMinWidth(800);
        stage.setMinHeight(800);
        stage.centerOnScreen();
        stage.show();
    }
}
