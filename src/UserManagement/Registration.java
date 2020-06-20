package UserManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Main.Database;
import Main.Home;
import Main.Menu;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Registration {

	private VBox Center;
	private final HBox Buttons = new HBox(30);
	private static BorderPane Layout;

	private static final Connection Connect = Database.getConnection();
	private static PreparedStatement prestatement = Database.getPrestatement();
	private static ResultSet resultSet = Database.getResultSet();

	private static final Text Status = new Text();

	private static final TextField First = new TextField();
	private static final TextField Last = new TextField();
	private static final TextField UName = new TextField();
	private static final TextField Password = new TextField();
	private static final TextField PasswordCheck = new TextField();

	private static final ChoiceBox<String> Doctor = new ChoiceBox<>();

	private static final Button RegisterBtn = new Button("Register");
	private static final Button UpdateBtn = new Button("Update account");
	private static final Button DeleteBtn = new Button ("Delete Account");

	public Boolean checkUName(String username) {
		if (!UName.getText().equals(Menu.getUName())) {
			try {
				String res = null;
				prestatement = Connect.prepareStatement("SELECT userName FROM users WHERE userName = ?");
				prestatement.setString(1, username);
				resultSet = prestatement.executeQuery();
				while (resultSet.next()) {
					res = resultSet.getString(1);
				}
				return res == null;
			} catch (SQLException e) {
				return true;
			}
		}
		return true;
	}

	public String isDoctorToString(String doctor) {
		if (doctor.equals("Doctor")) {
			return "T";
		}
		else {
			return "F";
		}
	}

	public void setStyles(){
		Status.setFill(Color.RED);

		Doctor.getItems().addAll("Standard user","Doctor");
		Doctor.setValue("Standard user");

		First.setPromptText("First Name");
		Last.setPromptText("Last Name");
		UName.setPromptText("Username");
		Password.setPromptText("Password");
		PasswordCheck.setPromptText("Repeat Password");
	}

	public void setVBox() {
		Center = new VBox(10);
		Center.getStyleClass().add("hbox");
		Center.setMaxWidth(400);
		Center.setMaxHeight(400);
		Layout.setTop(Menu.getMenu(Layout));
		Layout.setCenter(Center);
	}

	public Boolean checkPassword() {
		return Password.getText().equals(PasswordCheck.getText())
				&& Password.getText().length() > 6
				&& Password.getText().length() < 21
				&& !UName.getText().equals("")
				&& !First.getText().equals("")
				&& !Last.getText().equals("")
				&& checkUName(UName.getText());
	}

	public void checkPasswordFault() {
		if (!Password.getText().equals(PasswordCheck.getText())) {
			Status.setText("Your password doesn't match");
		}
		else {
			if (!(Password.getText().length() > 6) || !(Password.getText().length() < 21)) {
				Status.setText("PW must be between 7 and 20 characters");
			}
			else {
				if (!checkUName(UName.getText())) {
					Status.setText("This username already exists");
				}
				else {
					Status.setText("Please fill in all fields");
				}
			}
		}
	}

	public void databaseInsert() throws SQLException {
		prestatement = Connect.prepareStatement("INSERT INTO users VALUES(?,?,?,?,?)");
		prestatement.setString(1, UName.getText());
		prestatement.setString(2, First.getText());
		prestatement.setString(3, Last.getText());
		prestatement.setString(4, Password.getText());
		prestatement.setString(5, isDoctorToString(Doctor.getValue()));
		prestatement.executeUpdate();
	}

	private void registerBtnAction() {
		if(checkPassword()) {
			try {
				databaseInsert();
				Menu.setIsDoctor(Doctor.getValue().equals("Doctor"));
				Login.login();
			}
			catch (SQLException e1) {
				Status.setText("Error while adding user");
			}
		}
		else {
			checkPasswordFault();
		}
	}

	public void startRegistration() {
		RegisterBtn.setAlignment(Pos.BASELINE_CENTER);
		RegisterBtn.setOnAction(e -> registerBtnAction());
		Center.getChildren().addAll(First, Last, UName, Password, PasswordCheck, Doctor, Status, RegisterBtn);
	}

	public void showAccountDetails() throws SQLException {
		prestatement = Connect.prepareStatement("SELECT * FROM users WHERE userName = ?");
		prestatement.setString(1, Menu.getUName());
		resultSet = prestatement.executeQuery();
		while (resultSet.next()) {
			UName.setText(resultSet.getString(1));
			First.setText(resultSet.getString(2));
			Last.setText(resultSet.getString(3));
			Password.setText(resultSet.getString(4));
			if(resultSet.getString(5).equals("T")) {
				Doctor.setValue("Doctor");
			}
			else {
				Doctor.setValue("Standard user");
			}
		}
	}

	public void updateAccountDetails() throws SQLException{
		prestatement = Connect.prepareStatement(""
				+ "UPDATE users SET userName=?,"
				+ "firstName=?, lastName=?,"
				+ "`password`=?, isDoctor =? WHERE userName = ?");
		prestatement.setString(1, UName.getText());
		prestatement.setString(2, First.getText());
		prestatement.setString(3, Last.getText());
		prestatement.setString(4, Password.getText());
		prestatement.setString(5, isDoctorToString(Doctor.getValue()));
		prestatement.setString(6, Menu.getUName());
		prestatement.executeUpdate();
		Menu.setUName(UName.getText());
	}

	public void deleteAccount() {
		try {
			prestatement = Connect.prepareStatement("DELETE FROM users WHERE userName = ?");
			prestatement.setString(1, Menu.getUName());
			prestatement.execute();
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			Status.setText("Error while updating data in Users!");
		}
	}

	private void updateBtnAction() {
		if(checkPassword()) {
			Home H = new Home();
			try {
				updateAccountDetails();
				H.Homes(Layout);
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
		else {
			checkPasswordFault();
		}
	}

	private void deleteBtnAction() {
		if(checkPassword()) {
			deleteAccount();
			Login.login();
		}
		else {
			checkPasswordFault();
		}
	}

	public void startEditAccount() throws SQLException {
		showAccountDetails();

		UpdateBtn.setOnAction(e -> updateBtnAction());
		DeleteBtn.setOnAction(event -> deleteBtnAction());

		Buttons.getChildren().addAll(UpdateBtn, DeleteBtn);
		Center.getChildren().addAll(First, Last, UName, Password, PasswordCheck, Doctor, Status, Buttons);
	}

	public Registration(BorderPane layout) throws Exception {
		Layout = layout;
		setStyles();
		setVBox();

		if(!Menu.getIsLogin()) {
			startRegistration();
		}
		else {
			startEditAccount();
		}
	}

	//for test purposes
	public Registration(String first, String last, String uname, String pw, String pwc) {
		First.setText(first);
		Last.setText(last);
		UName.setText(uname);
		Password.setText(pw);
		Doctor.setValue("Standard user");
	}

	public void updateTextFields() {
		First.setText("FirstUpdate");
		Last.setText("LastUpdate");
		UName.setText("UNameUpdate");
		Password.setText("PWUpdate");
		Doctor.setValue("Doctor");
	}

	public void resetTextFields() {
		First.setText("");
		Last.setText("");
		UName.setText("");
		Password.setText("");
		Doctor.setValue("Standard user");
	}

	public String checkCurrentUName() {
		return UName.getText();
	}

	public void setPW(String PW) {
		Password.setText(PW);
		PasswordCheck.setText(PW);
	}

	public String getStatus() {
		return Status.getText();
	}
}
