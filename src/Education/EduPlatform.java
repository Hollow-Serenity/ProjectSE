package Education;


import Main.Menu;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EduPlatform {
    private static BorderPane Layout;
    private final HBox RowOne = new HBox();
    private final HBox RowTwo = new HBox();
    private final HBox RowThree = new HBox();
    private final VBox Center = new VBox(20);

    private static final Button Reading = new Button("Reading");
    private static final Button Writing = new Button("Writing");
    private static final Button Math = new Button("Math");

    private void setLayout() {
        RowOne.getChildren().addAll(Reading);
        RowOne.setAlignment(Pos. CENTER);
        RowTwo.getChildren().addAll(Writing);
        RowTwo.setAlignment(Pos. CENTER);
        RowThree.getChildren().addAll(Math);
        RowThree.setAlignment(Pos. CENTER);

        Center.getStyleClass().addAll("HomeVBox");
        Center.getChildren().addAll(RowOne, RowTwo, RowThree);
        Center.setMaxWidth(800 * 0.95);
        Center.setMaxHeight(600 * 0.75);

        Layout.setTop(Menu.getMenu(Layout));
        Layout.setCenter(Center);
    }

    private void setButtonStyle(Button button, String image, String tooltip) {
        button.getStyleClass().addAll("HomeBtn", "LightGreen");
        Image APImg = new Image(getClass().getResourceAsStream(image));
        ImageView APIV = new ImageView(APImg);
        APIV.setFitWidth(50);
        APIV.setFitHeight(50);
        button.setGraphic(APIV);
        button.setTooltip(new Tooltip(tooltip));
    }

    public EduPlatform(BorderPane layout) {
        Layout = layout;
        setLayout();

        setButtonStyle(Reading, "../Images/AddProduct.png", "reading assignment");
        Reading.setOnAction(event -> new DisplayReading(Layout));

        setButtonStyle(Writing, "../Images/AddUser.png", "Writing assignment");
        Writing.setOnAction(event -> new DisplayWritingAssignment(Layout));

        setButtonStyle(Math, "../Images/AddProduct.png", "Math assignment");
        Math.setOnAction(e -> new Mathematics(Layout));
    }
}
