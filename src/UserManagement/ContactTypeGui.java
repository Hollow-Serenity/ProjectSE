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
    private final VBox vBox = new VBox(20);
    private final HBox hBox = new HBox(40);

    private static final Label chooseLabel = new Label("What kind of contact would you like to add");

    private static final Button personButton = new Button("Person");
    private static final Button companyButton = new Button("Company");

    private void setLayout() {
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(chooseLabel, hBox);

        hBox.setMaxHeight(400);
        hBox.setMaxWidth(400);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(personButton, companyButton);

        Layout.setTop(Menu.getMenu(Layout));
        Layout.setCenter(vBox);
    }

    public ContactTypeGui(BorderPane layout) throws Exception {
        Layout = layout;
        setLayout();

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
