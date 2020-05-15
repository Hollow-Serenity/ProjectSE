package Main;

import java.sql.SQLException;

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

	public static Boolean isLogin = false;
	public static Stage Window;
	public static String StoreUName;
	public static Scene Scn;
	public static BorderPane Layout;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Window = primaryStage;
		login();
		Window.setTitle("Inventory System");
	}
	public void login() {
		Text Status = new Text();
		Status.setText("");

		TextField UName = new TextField();
		UName.setPromptText("Username");
		UName.getStyleClass().add("Username");

		PasswordField Password = new PasswordField();
		Password.setPromptText("Password");
		Password.getStyleClass().add("Password");

		Button LoginBtn = new Button("Login");
		LoginBtn.getStyleClass().addAll("LoginBtn", "WhiteTextColor");

		Button Register = new Button("Sign up");
		Register.getStyleClass().addAll("LogonBtn");

		VBox CompanyInformation = new VBox();
		Label CName = new Label("Welcome to zeroXess");
		CName.getStyleClass().add("CName");
		CName.getStyleClass().add("WhiteTextColor");

		Label LoginLbl = new Label("Login");
		LoginLbl.getStyleClass().addAll("LoginHeading");

		Line Hr = new Line(0, 0, 100, 0);
		Hr.setStrokeWidth(5);
		Hr.setStroke(Color.WHITE);
		Hr.getStyleClass().add("Hr");

		Label CDesc = new Label("Lorem Ipsum is simply dummy text\n " + "of the printing and typesetting industry.\n"
				+ "Lorem Ipsum has been the industry's\n" + "standard dummy text ever since the 1500s,\n"
				+ "printer took a galley of type and to\n" + "make a type specimen book.");

		VBox LoginBox = new VBox();
		LoginBox.getStyleClass().add("WhiteVbox");
		LoginBox.getChildren().addAll(LoginLbl, Status, UName, Password, LoginBtn, Register);
		LoginBox.setSpacing(20);

		HBox CenterBox = new HBox();
		CenterBox.getStyleClass().add("CenterHbox");
		CenterBox.getChildren().addAll(CompanyInformation, LoginBox);

		Layout = new BorderPane();
		Menu m = new Menu();
		Layout.setTop(m.Menu());
		Layout.setCenter(CenterBox);

		CDesc.getStyleClass().add("WhiteTextColor");

		CompanyInformation.getChildren().addAll(CName, Hr, CDesc);
		CompanyInformation.getStyleClass().add("BlueVbox");
		CompanyInformation.setSpacing(20);

		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		Scn = new Scene(Layout, screenBounds.getWidth(), screenBounds.getHeight());
		Scn.getStylesheets().add(getClass().getResource("../css/application.css").toExternalForm());
		Window.setScene(Scn);
		Window.setMaximized(true);
		Window.show();

		Password.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
			if (e.getCode() == KeyCode.ENTER) {
				LoginBtn.fire();
			}
		});

		UName.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
			if (e.getCode() == KeyCode.ENTER) {
				LoginBtn.fire();
			}
		});

		LoginBtn.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
			if (e.getCode() == KeyCode.ENTER) {
				LoginBtn.fire();
			}
		});

		LoginBtn.setOnAction(e -> {
			try {
				Database db = new Database();
				db.prestatement = db.Connect.prepareStatement("select * from users where userName = ?");
				db.prestatement.setString(1, UName.getText());
				db.resultSet = db.prestatement.executeQuery();
				while (db.resultSet.next()) {
					if (Password.getText().equals(db.resultSet.getString("password"))) {
						StoreUName = UName.getText();
						isLogin = true;
						Home h = new Home();
						h.Homes();
						break;
					}
				}
				Status.setFill(Color.RED);
				Status.setText("Your username or password is incorrect");
			}
			catch (SQLException e1) {
				System.out.println("Error while fetching data");
			}
		});

		Register.setOnAction(e -> {
			try {
				new Registration();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
	}
}