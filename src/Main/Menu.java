package Main;

import java.sql.SQLException;

import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Menu {

	public static Stage Window;

	public VBox Menu() {
		MenuBar myMenu = new MenuBar();
		javafx.scene.control.Menu Action = new javafx.scene.control.Menu("_Action");
		MenuItem Exit = new MenuItem("_Exit");
		Exit.setOnAction(e -> System.exit(0));
		VBox PageTop = new VBox();
		Driver d = new Driver();
		if (Driver.isLogin) {
			javafx.scene.control.Menu Manage = new javafx.scene.control.Menu("_Manage");
			
			AddProducts ap = new AddProducts();
			MenuItem AddProduct = new MenuItem("_Add Products");
			AddProduct.setOnAction(e -> {
				ap.AddProduct();
			});
			MenuItem AddUser = new MenuItem("_Edit Profile");
			AddUser.setOnAction(e -> {
				try {
					Logon logon = new Logon();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			});
			MenuItem Home = new MenuItem("_Home");
			Home.setOnAction(e -> {
				try {
					Home h = new Home();
					h.Homes();
				} catch (SQLException e1) {
					System.out.println("SQL Error");
				}
			});
			MenuItem Logout = new MenuItem("_Logout");
			Logout.setOnAction(e -> {
				Driver.StoreUName = null;
				d.isLogin = false;
				d.login();
			});
			Action.getItems().addAll(Home, Logout, Exit);
			Manage.getItems().addAll(AddProduct, AddUser);
			myMenu.getMenus().addAll(Action, Manage);
			PageTop.getChildren().add(myMenu);
		} else {
			MenuItem Login = new MenuItem("_Login");
			Login.setOnAction(e -> d.login());
			Action.getItems().addAll(Login, Exit);
			myMenu.getMenus().add(Action);
			PageTop.getChildren().add(myMenu);
		}
		return PageTop;
	}

}
