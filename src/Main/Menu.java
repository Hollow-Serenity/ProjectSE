package Main;

import java.sql.SQLException;

import LiveStock.AddProducts;
import Medical.Specialization;
import UserManagement.Login;
import UserManagement.Registration;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

public class Menu {
	private static Menu singleton = null;

	private static final VBox PageTop = new VBox();

	private static final MenuBar myMenu = new MenuBar();
	private static final MenuBar loginMenu = new MenuBar();

	private static final javafx.scene.control.Menu Menu = new javafx.scene.control.Menu("_Menu");
	private static final javafx.scene.control.Menu MenuLogin = new javafx.scene.control.Menu("_Menu");
	private static final javafx.scene.control.Menu Manage = new javafx.scene.control.Menu("_Manage");

	private static final MenuItem Exit = new MenuItem("_Exit");
	private static final MenuItem Exit1 = new MenuItem("_Exit");
	private static final MenuItem AddProduct = new MenuItem("_Add Products");
	private static final MenuItem AddUser = new MenuItem("_Edit Profile");
	private static final MenuItem Home = new MenuItem("_Home");
	private static final MenuItem Specializations = new MenuItem("_Specialization");
	private static final MenuItem Logout = new MenuItem("_Logout");

	private static Boolean isLogin = false;
	private static Boolean isDoctor = false;
	private static String StoreUName;

	private Menu() {
		setLayout();
		setButtonActions();
	}

	public static VBox getMenu() {
		if (singleton == null) {
			singleton = new Menu();
		}
		setMenu();
		return PageTop;
	}

	private static void setButtonActions() {
		Exit.setOnAction(e -> System.exit(0));
		Exit1.setOnAction(e -> System.exit(0));
		setAddProductButtonAction();
		setAddUserButtonAction();
		setHomeButtonAction();
		setSpecializationsButtonAction();
		setLogoutButtonAction();
	}

	private static void setAddProductButtonAction() {
		AddProduct.setOnAction(e -> {
			AddProducts ap = new AddProducts();
			ap.AddProduct();
		});
	}

	private static void setAddUserButtonAction() {
		AddUser.setOnAction(e -> {
			try {
				Registration logon = new Registration();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
	}

	private static void setHomeButtonAction() {
		Home.setOnAction(e -> {
			try {
				Home h = new Home();
				h.Homes();
			} catch (SQLException e1) {
				System.out.println("SQL Error");
			}
		});
	}

	private static void setSpecializationsButtonAction() {
		Specializations.setOnAction(e -> {
			try {
				new Specialization();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
	}

	private static void setLogoutButtonAction() {
		Logout.setOnAction(e -> {
			StoreUName = null;
			isLogin = false;
			isDoctor = false;
			Login.login();
		});
	}

	private static void setLayout() {
		Menu.getItems().addAll(Home, Specializations, Logout, Exit);
		Manage.getItems().addAll(AddProduct, AddUser);
		myMenu.getMenus().addAll(Menu, Manage);
		MenuLogin.getItems().addAll(Exit1);
		loginMenu.getMenus().add(MenuLogin);
	}

	private static void setMenu() {
		if(isLogin) {
			PageTop.getChildren().clear();
			PageTop.getChildren().add(myMenu);
		}
		else {
			PageTop.getChildren().clear();
			PageTop.getChildren().add(loginMenu);
		}
		if (isDoctor) {
			Specializations.setVisible(true);
		}
		else {
			Specializations.setVisible(false);
		}
	}

	public static String getUName() {
		return StoreUName;
	}
	public static Boolean getIsLogin() {
		return isLogin;
	}
	public static Boolean getIsDoctor() {
		return isDoctor;
	}

	public static void setUName(String UName) {
		StoreUName = UName;
	}
	public static void setIsLogin(Boolean login) {
		isLogin = login;
	}
	public static void setIsDoctor(Boolean doctor) {
		isDoctor = doctor;
	}
}
