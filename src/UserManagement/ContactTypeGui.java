package UserManagement;

import Main.Menu;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ContactTypeGui {
    private static BorderPane Layout;
    public ContactTypeGui(BorderPane layout) throws Exception {
        this.Layout = layout;
        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);

        HBox hBox = new HBox(40);
        hBox.setMaxHeight(400);
        hBox.setMaxWidth(400);
        hBox.setAlignment(Pos.CENTER);

        Label chooseLabel = new Label("What kind of contact would you like to add");

        Button personButton = new Button("Person");
        Button companyButton = new Button("Company");
        hBox.getChildren().addAll(personButton, companyButton);
        vBox.getChildren().addAll(chooseLabel, hBox);

        Layout.setTop(Menu.getMenu(Layout));
        Layout.setCenter(vBox);

        personButton.setOnAction(actionEvent -> {
            try {
                new UserAddContactPersonGui(Layout);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        companyButton.setOnAction(actionEvent -> {
            try {
                new UserAddContactCompanyGui(Layout);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}