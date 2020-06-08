package Main;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Market {

    Stage window;
    Scene sceneHome, sceneSell, sceneSellConfirm, sceneBuy, bidConfirm;

    public void Market() {
        Menu m = new Menu();
        BorderPane layout = Login.getLayout();
        layout.setTop(m.Menu());

        window = new Stage();
        Label label1 = new Label("Marketplace");
        Button buttonSell = new Button("Sell");
        Button buttonBuy = new Button("Buy");
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, buttonSell, buttonBuy);
        layout.setCenter(layout1);

        Label labelSell = new Label("Sell");
        Label Select = new Label("Select your Lifestock");
        TextField name = new TextField();
        name.setPromptText("Enter name of lifestock.");
        TextField quant = new TextField();
        quant.setPromptText("Quantity");
        TextField price = new TextField();
        price.setPromptText("Price");

        Button ConfirmSell = new Button("Confirm");
        Button buttonBack1 = new Button("Back to main menu");
        buttonBack1.setOnAction(e -> layout.setCenter(layout1));

        VBox layout2 = new VBox(20);
        layout2.getChildren().addAll(labelSell, Select, name, quant, price, ConfirmSell, buttonBack1);
        buttonSell.setOnAction(e -> layout.setCenter(layout2));

        //Confirm Sold
        Label label = new Label("");
        Label Confirm = new Label("Listing confirmed.");
        Button buttonBack2 = new Button("Back to main menu");
        buttonBack2.setOnAction(e -> layout.setCenter(layout1));
        VBox layoutConfirm = new VBox(20);
        layoutConfirm.getChildren().addAll(label, Confirm, buttonBack2);

        ConfirmSell.setOnAction(e -> {
            String x = name.getText();
            String y = quant.getText();
            String z = price.getText();
            String xyz = x + " " + y + " " + z;
            System.out.println("Sell Confirmed: " + xyz);
            label.setText(xyz);
            layout.setCenter(layoutConfirm);
        });

        //BuyPage
        Label labelBuy = new Label("Buy");
        Label LabelSelect = new Label("Select the lifestock u want to buy");
        TextField Name = new TextField();
        Name.setPromptText("Enter name of lifestock.");
        TextField Quant = new TextField();
        Quant.setPromptText("Quantity");
        TextField Price = new TextField();
        Price.setPromptText("Your bid");
        Button ConfirmBid = new Button("Confirm Bid");
        Button buttonBack3 = new Button("Back to main menu");
        buttonBack3.setOnAction(e -> layout.setCenter(layout1));

        VBox layout3 = new VBox(20);
        layout3.getChildren().addAll(labelBuy, LabelSelect, Name, Quant, Price, ConfirmBid, buttonBack3);
        buttonBuy.setOnAction(e -> layout.setCenter(layout3));

        //bid Confirm
        Label ConBid = new Label("Bid Confirmed.");
        Button buttonBack4 = new Button("Back to main menu");
        buttonBack4.setOnAction(e -> layout.setCenter(layout1));
        VBox layout4 = new VBox(20);
        layout4.getChildren().addAll(ConBid, buttonBack4);

        ConfirmBid.setOnAction((ActionEvent e) -> {
            layout.setCenter(layout4);
        });

    }

}
