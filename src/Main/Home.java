package Main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

import java.sql.SQLException;

public class Home {

    @SuppressWarnings("static-access")
    public void Homes() throws SQLException {
        Button InventoryBtn = new Button("Add Inventory");
        Button EditUser = new Button("Edit Profile");
        Button LogoutBtn = new Button("Logout");
        Button MarketBtn = new Button("Market");
        Button UserAddBtn = new Button("Add User");
        Button appointmentdBtn = new Button("Appointment");

        InventoryBtn.getStyleClass().addAll("HomeBtn", "LightGreen");
        Image APImg = new Image(getClass().getResourceAsStream("../Images/AddProduct.png"));
        ImageView APIV = new ImageView(APImg);
        APIV.setFitWidth(50);
        APIV.setFitHeight(50);
        InventoryBtn.setGraphic(APIV);
        InventoryBtn.setTooltip(new Tooltip("Add Products"));
        AddProducts ap = new AddProducts();
        InventoryBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ap.AddProduct();
            }
        });
        EditUser.getStyleClass().addAll("HomeBtn", "LightGreen");
        Image AUImg = new Image(getClass().getResourceAsStream("../Images/AddUser.png"));
        ImageView AUIV = new ImageView(AUImg);
        AUIV.setFitWidth(50);
        AUIV.setFitHeight(50);

        EditUser.setGraphic(AUIV);
        EditUser.setTooltip(new Tooltip("Edit User"));
        EditUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new Registration();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        LogoutBtn.getStyleClass().addAll("HomeBtn", "LightGreen");
        Image LgImg = new Image(getClass().getResourceAsStream("../Images/Logout.png"));
        ImageView LgIV = new ImageView(LgImg);
        LgIV.setFitWidth(50);
        LgIV.setFitHeight(50);
        LogoutBtn.setGraphic(LgIV);
        LogoutBtn.setTooltip(new Tooltip("Logout"));
        LogoutBtn.setOnAction(e -> {
            Login d = new Login();
            Login.StoreUName = null;
            d.isLogin = false;
            d.login();
        });

        //Market Button
        MarketBtn.getStyleClass().addAll("HomeBtn", "LightGreen");
        Image MrImg = new Image(getClass().getResourceAsStream("../Images/AddProduct.png"));
        ImageView MrIV = new ImageView(MrImg);
        MrIV.setFitWidth(50);
        MrIV.setFitHeight(50);
        MarketBtn.setGraphic(MrIV);
        MarketBtn.setTooltip(new Tooltip("Market"));
        Market mr = new Market();
        MarketBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mr.Market();
            }
        });

        UserAddBtn.getStyleClass().addAll("HomeBtn", "LightGreen");
        Image UAImg = new Image(getClass().getResourceAsStream("../Images/AddUser.png"));
        ImageView UAIV = new ImageView(UAImg);
        UAIV.setFitWidth(50);
        UAIV.setFitHeight(50);
        UserAddBtn.setGraphic(UAIV);
        UserAddBtn.setTooltip(new Tooltip("Add User"));
        UserAddContactGui userAdd = new UserAddContactGui();
        UserAddBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                userAdd.userAdd();
            }
        });
        
        appointmentdBtn.getStyleClass().addAll("HomeBtn", "LightGreen");
        Image appImg = new Image(getClass().getResourceAsStream("../Images/AddUser.png"));
        ImageView appIV = new ImageView(appImg);
        appIV.setFitWidth(50);
        appIV.setFitHeight(50);
        appointmentdBtn.setGraphic(appIV);
        appointmentdBtn.setTooltip(new Tooltip("Make Appointment"));
//        AddAppointment addApp = new AddAppointment();
        ViewAppointment viewApp = new ViewAppointment();
        appointmentdBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	viewApp.ViewAppointment();
            }
        });

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        HBox RowOne = new HBox();
        RowOne.setSpacing(20);
        if(Login.isDoctor) {
        	RowOne.getChildren().addAll(InventoryBtn, MarketBtn, EditUser, UserAddBtn, LogoutBtn);
        }else {
        	RowOne.getChildren().addAll(InventoryBtn, MarketBtn, EditUser, UserAddBtn, appointmentdBtn, LogoutBtn);
        }
        
        RowOne.setAlignment(Pos.CENTER);


        VBox Center = new VBox();
        //Center.setAlignment(Pos.CENTER);
        Center.getChildren().addAll(RowOne);
        Center.getStyleClass().addAll("HomeVBox");
        Center.setMaxWidth(screenBounds.getWidth() * 0.95);
        Center.setMaxHeight(screenBounds.getHeight() * 0.75);
        Center.setSpacing(20);

        Menu m = new Menu();
        Login.Layout.setTop(m.Menu());
        Login.Layout.setCenter(Center);


    }
}
