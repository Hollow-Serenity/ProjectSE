package Main;

import java.sql.SQLException;

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


public class Home {
	@SuppressWarnings("static-access")
	public void Homes() throws SQLException {		
		Button InventoryBtn = new Button("Add Inventory");	
		Button EditUser = new Button("Edit Profile"); 		
		Button LogoutBtn = new Button("Logout"); 

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
		EditUser.setTooltip(new Tooltip("Add User"));
		EditUser.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					new Logon();
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
		LogoutBtn.setOnAction(e->{
			Driver d = new Driver();
			Driver.StoreUName = null;
			d.isLogin = false;
			d.login();
		});
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		
		HBox RowOne = new HBox();
		RowOne.setSpacing(20);
		RowOne.getChildren().addAll(InventoryBtn, EditUser, LogoutBtn);
		
		VBox Center = new VBox();
		Center.setAlignment(Pos.CENTER);
		Center.getChildren().addAll(RowOne);
		Center.getStyleClass().addAll("HomeH" + "box");
		Center.setMaxWidth(screenBounds.getWidth()*0.75);
		Center.setMaxHeight(screenBounds.getHeight()*0.75);
		Center.setSpacing(20);
		Menu m = new Menu();
		Driver.Layout.setTop(m.Menu());
		Driver.Layout.setCenter(Center);
	}
}
