package Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Eduplatform {
    public Stage stage;
    public Eduplatform() throws SQLException{
        Button Reading = new Button("Reading");
        Button Writing = new Button("Writing");
        Button Math = new Button("Math");


        Reading.getStyleClass().addAll("HomeBtn", "LightGreen");
        Image APImg = new Image(getClass().getResourceAsStream("../Images/AddProduct.png"));
        ImageView APIV = new ImageView(APImg);
        APIV.setFitWidth(50);
        APIV.setFitHeight(50);
        Reading.setGraphic(APIV);
        Reading.setTooltip(new Tooltip("Add Products"));
        AddProducts ap = new AddProducts();
        Reading.setOnAction(event -> {
            new DisplayReading();
        });

        Writing.getStyleClass().addAll("HomeBtn", "LightGreen");
        Image AUImg = new Image(getClass().getResourceAsStream("../Images/AddUser.png"));
        ImageView AUIV = new ImageView(AUImg);
        AUIV.setFitWidth(50);
        AUIV.setFitHeight(50);
        Writing.setGraphic(AUIV);
        Writing.setTooltip(new Tooltip("Edit User"));
        Writing.setOnAction(event -> {
            new DisplayWritingAssignment();
        });

        Math.getStyleClass().addAll("HomeBtn", "LightGreen");
        Image MrImg = new Image(getClass().getResourceAsStream("../Images/AddProduct.png"));
        ImageView MrIV = new ImageView(MrImg);
        MrIV.setFitWidth(50);
        MrIV.setFitHeight(50);
        Math.setGraphic(MrIV);
        Math.setTooltip(new Tooltip("Market"));
        Market mr = new Market();
        Math.setOnAction(e -> {
            try {
                new Mathematics();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        });
        HBox RowOne = new HBox();
        HBox RowTwo = new HBox();
        HBox RowThree = new HBox();
        RowOne.getChildren().addAll(Reading);
        RowTwo.getChildren().addAll(Writing);
        RowThree.getChildren().addAll(Math);

        RowOne.setAlignment(Pos. CENTER);
        RowTwo.setAlignment(Pos. CENTER);
        RowThree.setAlignment(Pos. CENTER);

        VBox Center = new VBox();
        Center.getStyleClass().addAll("HomeVBox");
        Center.getChildren().addAll(RowOne, RowTwo, RowThree);
        Center.setMaxWidth(800 * 0.95);
        Center.setMaxHeight(600 * 0.75);
        Center.setSpacing(20);

        Menu m = new Menu();
        Login.getLayout().setTop(m.Menu());
        Login.getLayout().setCenter(Center);
//

    }
}
