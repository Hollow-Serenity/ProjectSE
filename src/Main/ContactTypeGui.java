package Main;

import Main.UserAddContactCompanyGui;
import Main.UserAddContactPersonGui;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ContactTypeGui {
    public ContactTypeGui() throws Exception {
        VBox vBox = new VBox(20);
        vBox.setMaxWidth(400);
        vBox.setMaxHeight(400);

        HBox hBox = new HBox(40);
        //hBox.setAlignment(Pos.CENTER);

        Label chooseLabel = new Label("What kind of contact would you like to add");

        Button personButton = new Button("Person");
        Button companyButton = new Button("Company");

        hBox.getChildren().addAll(personButton, companyButton);
        vBox.getChildren().addAll(chooseLabel, hBox);

        Menu m = new Menu();
        Login.Layout.setTop(m.Menu());
        Login.Layout.setCenter(vBox);



        /*
        personButton.setOnAction(actionEvent -> {
            UserAddContactPersonGui userAddContactPersonGui = new UserAddContactPersonGui();
            try {
                userAddContactPersonGui.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        companyButton.setOnAction(actionEvent -> {
            UserAddContactCompanyGui userAddContactCompanyGui = new UserAddContactCompanyGui();
            try {
                userAddContactCompanyGui.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
         */

    }
}
