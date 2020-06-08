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
		Login d = new Login();
		if (Login.getIsLogin()) {
			javafx.scene.control.Menu Manage = new javafx.scene.control.Menu("_Manage");
			
			AddProducts ap = new AddProducts();
			MenuItem AddProduct = new MenuItem("_Add Products");
			AddProduct.setOnAction(e -> {
				ap.AddProduct();
			});
			MenuItem AddUser = new MenuItem("_Edit Profile");
			AddUser.setOnAction(e -> {
				try {
					Registration logon = new Registration();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			});
			MenuItem Home = new MenuItem("_Home");
			Home.setOnAction(e -> {
				try {
					Main.Home h = new Home();
					h.Homes();
				} catch (SQLException e1) {
					System.out.println("SQL Error");
				}
			});
			MenuItem Specializations = new MenuItem("_Specialization");
			Specializations.setOnAction(e -> {
				try {
					new Specialization();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			});
			MenuItem Conditions = new MenuItem("_Conditions");
			Conditions.setOnAction(e -> {
				try {
					new Condition();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			});
			if (!Login.getIsDoctor()) {
				Specializations.setVisible(false);
				Conditions.setVisible(false);
			}
			MenuItem Logout = new MenuItem("_Logout");
			Logout.setOnAction(e -> {
				Login.setUName(null);
				Login.setIsLogin(false);
				Login.setIsDoctor(false);
				d.login();
			});
			Action.getItems().addAll(Home, Specializations, Conditions, Logout, Exit);
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
