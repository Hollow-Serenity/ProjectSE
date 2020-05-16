
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.util.Stack;

public class Main extends Application {

    Stage window;
    Scene sceneHome, sceneSell, sceneSellConfirm, sceneBuy, bidConfirm;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        Label label1 = new Label("Marketplace");
        Button buttonSell = new Button("Sell");
        Button buttonBuy = new Button("Buy");
        buttonSell.setOnAction(e -> window.setScene(sceneSell));
        buttonBuy.setOnAction(e -> window.setScene(sceneBuy));
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, buttonSell, buttonBuy);
        sceneHome = new Scene(layout1, 800, 600);

        //Sellpage

        Label labelSell = new Label("Sell");
        Label Select = new Label("Select your Lifestock");
        TextField name = new TextField ();
        String x = name.getText();
        name.setPromptText("Enter name of lifestock.");
        TextField quant = new TextField();
        String y = quant.getText();
        quant.setPromptText("Quantity");
        TextField price = new TextField();
        String z =  price.getText();
        price.setPromptText("Price");
        String xyz = x+y+z;

        Button ConfirmSell = new Button("Confirm");
        Button buttonBack1 = new Button("Back to main menu");
        buttonBack1.setOnAction(e -> window.setScene(sceneHome));
        ConfirmSell.setOnAction(e -> {
            System.out.println(xyz);



            window.setScene(sceneSellConfirm);

        } );

        VBox layout2 = new VBox(20);
        layout2.getChildren().addAll(labelSell, Select, name, quant, price, ConfirmSell, buttonBack1);
        sceneSell = new Scene(layout2, 800, 600);

        //Confirm Sold


        Label label = new Label(xyz);
        Label Confirm = new Label ("Listing confirmed.");
        Button buttonBack2 = new Button("Back to main menu");
        buttonBack2.setOnAction(e -> window.setScene(sceneHome));
        VBox layoutConfirm = new VBox(20);
        layoutConfirm.getChildren().addAll(label, Confirm, buttonBack2);
        sceneSellConfirm = new Scene(layoutConfirm, 800, 600);




        //BuyPage
        Label labelBuy = new Label("Buy");
        Label LabelSelect = new Label("Select the lifestock u want to buy");
        TextField Name = new TextField ();
        Name.setPromptText("Enter name of lifestock.");
        TextField Quant = new TextField();
        Quant.setPromptText("Quantity");
        TextField Price = new TextField();
        Price.setPromptText("Your bid");
        Button ConfirmBid = new Button("Confirm Bid");
        Button buttonBack3 = new Button("Back to main menu");
        buttonBack3.setOnAction(e -> window.setScene(sceneHome));
        ConfirmBid.setOnAction(e -> window.setScene(bidConfirm));
        VBox layout3 = new VBox(20);
        layout3.getChildren().addAll(labelBuy, LabelSelect, Name, Quant, Price, ConfirmBid, buttonBack3);
        sceneBuy = new Scene(layout3, 800, 600);



        //bid Confirm
        Label ConBid= new Label("Bid Confirmed.");
        Button buttonBack4 = new Button("Back to main menu");
        buttonBack4.setOnAction(e -> window.setScene(sceneHome));
        VBox layout4 = new VBox(20);
        layout4.getChildren().addAll(ConBid, buttonBack4);
        bidConfirm = new Scene(layout4, 800, 600);




        window.setScene(sceneHome);
        window.setTitle("Titel");
        window.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
