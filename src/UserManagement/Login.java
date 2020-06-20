package UserManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Main.Database;
import Main.Home;
import Main.Menu;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Login extends Application {

	private static final Database db = Database.getDatabase();

	private static final Connection Connect = Database.getConnection();
	private static ResultSet resultSet = Database.getResultSet();

	private static Stage Window;
	private static final BorderPane Layout = new BorderPane();

	private static final Label CName = new Label("Welcome to zeroXess");
	private static final Label LoginLbl = new Label("Login");

	private static final Text Status = new Text();
	private static final TextField UName = new TextField();
	private static final PasswordField Password = new PasswordField();

	private static final Button LoginBtn = new Button("Login");
	private static final Button Register = new Button("Sign up");

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.initStyle(javafx.stage.StageStyle.UNDECORATED);
		Window = primaryStage;
		initiate();
		login();
		Window.setTitle("ZeroXess");
	}

	public static void setStyles() {
		UName.setPromptText("Username");
		UName.getStyleClass().add("Username");

		Password.setPromptText("Password");
		Password.getStyleClass().add("Password");

		LoginBtn.getStyleClass().addAll("LoginBtn", "WhiteTextColor");

		Register.getStyleClass().addAll("LogonBtn");

		CName.getStyleClass().add("CName");
		CName.getStyleClass().add("WhiteTextColor");

		LoginLbl.getStyleClass().addAll("LoginHeading");
	}

	private static VBox getCompanyInfoBox() {
		VBox CIBox = new VBox( 10);

		Line Hr = new Line(0, 0, 100, 0);
		Hr.setStrokeWidth(5);
		Hr.setStroke(Color.WHITE);
		Hr.getStyleClass().add("Hr");

		Label CDesc = new Label("Lorem Ipsum is simply dummy text\n " + "of the printing and typesetting industry.\n"
				+ "Lorem Ipsum has been the industry's\n" + "standard dummy text ever since the 1500s,\n"
				+ "printer took a galley of type and to\n" + "make a type specimen book.");
		CDesc.getStyleClass().add("WhiteTextColor");

		CIBox.getChildren().addAll(CName, Hr, CDesc);
		CIBox.getStyleClass().add("BlueVbox");


		return CIBox;
	}

	private static VBox getLoginBox() {
		VBox LoginBox = new VBox();

		LoginBox.getStyleClass().add("WhiteVbox");
		LoginBox.getChildren().addAll(LoginLbl, Status, UName, Password, LoginBtn, Register);
		LoginBox.setSpacing(20);


		return LoginBox;
	}

	private static HBox getCenterBox(VBox ci, VBox login) {
		HBox CenterBox = new HBox();
		CenterBox.getStyleClass().add("CenterHbox");
		CenterBox.getChildren().addAll(ci, login);
		CenterBox.setSpacing(0);
		return CenterBox;
	}

	private static void initiate() {
		Scene scn = new Scene(Layout, 800, 600);
		scn.getStylesheets().add(Login.class.getResource("../css/application.css").toExternalForm());
		Window.setScene(scn);
		Window.setMaximized(false);
		Window.show();
	}

	private static void keyEvent(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			LoginBtn.fire();
		}
	}

	private static void doctorCheck() throws SQLException {
		if (resultSet.getString("isDoctor").equals("T")) {
			Menu.setIsDoctor(true);
		}
	}

	private static void saveInfo() throws SQLException {
		Menu.setUName(UName.getText());
		Menu.setIsLogin(true);
		doctorCheck();
	}

	private static Boolean loginCheck() throws SQLException {
		return  Password.getText().equals(resultSet.getString("password"));
	}

	private static void loginAttempt() {
		try {
			PreparedStatement prestatement = Connect.prepareStatement("select * from users where userName = ?");
			prestatement.setString(1, UName.getText());
			resultSet = prestatement.executeQuery();
			while (resultSet.next()) {
				if(loginCheck()) {
					saveInfo();
					Password.setText("");
					UName.setText("");
					Status.setText("");
					Home h = new Home();
					h.Homes(Layout);
					break;
				}
				else {
					Status.setFill(Color.RED);
					Status.setText("Your username or password is incorrect");
				}
			}
		}
		catch (SQLException e1) {
			Status.setText("Error while fetching data");
		}
	}

	private static void startRegistration() {
		try {
			new Registration(Layout);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void login() {
		setStyles();
		VBox CompanyInformation = getCompanyInfoBox();
		VBox LoginBox = getLoginBox();
		HBox CenterBox = getCenterBox(CompanyInformation, LoginBox);

		Layout.setTop(Menu.getMenu(Layout));
		Layout.setCenter(CenterBox);

		Password.addEventHandler(KeyEvent.KEY_PRESSED, e -> keyEvent(e));
		UName.addEventHandler(KeyEvent.KEY_PRESSED, e -> keyEvent(e));
		LoginBtn.addEventHandler(KeyEvent.KEY_PRESSED, e -> keyEvent(e));

		LoginBtn.setOnAction(e -> loginAttempt());
		Register.setOnAction(e -> startRegistration());
	}
}
