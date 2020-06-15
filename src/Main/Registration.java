package Main;

import java.sql.SQLException;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Registration {

	private static VBox Center;

	private static Database db = new Database();

	private static Text Status = new Text();

	private static TextField First = new TextField();
	private static TextField Last = new TextField();
	private static TextField UName = new TextField();
	private static TextField Password = new TextField();
	private static TextField PasswordCheck = new TextField();

	private static ChoiceBox<String> Doctor = new ChoiceBox<>();

	private static Button Register = new Button("Register");
	private static Button UpdateBtn = new Button("Update account");

	public Boolean checkUName(Database db, String username) {
		if (!UName.getText().equals(Login.getUName())) {
			try {
				String res = null;
				db.prestatement = db.Connect.prepareStatement("SELECT userName FROM users WHERE userName = ?");
				db.prestatement.setString(1, username);
				db.resultSet = db.prestatement.executeQuery();
				while (db.resultSet.next()) {
					res = db.resultSet.getString(1);
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

	public static void setVBox() {
		Center = new VBox(10);
		Center.getStyleClass().add("hbox");
		Center.setMaxWidth(400);
		Center.setMaxHeight(400);
		Menu m = new Menu();
		Login.getLayout().setTop(m.Menu());
		Login.getLayout().setCenter(Center);
	}

	public Boolean checkPassword() {
		return Password.getText().equals(PasswordCheck.getText())
				&& Password.getText().length() > 6
				&& Password.getText().length() < 21
				&& !UName.getText().equals("")
				&& !First.getText().equals("")
				&& !Last.getText().equals("")
				&& checkUName(db, UName.getText());
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
				if (!checkUName(db, UName.getText())) {
					Status.setText("This username already exists");
				}
				else {
					Status.setText("Please fill in all fields");
				}
			}
		}
	}

	public void databaseInsert() throws SQLException {
		db.prestatement = db.Connect.prepareStatement("INSERT INTO users VALUES(?,?,?,?,?)");
		db.prestatement.setString(1, UName.getText());
		db.prestatement.setString(2, First.getText());
		db.prestatement.setString(3, Last.getText());
		db.prestatement.setString(4, Password.getText());
		db.prestatement.setString(5, isDoctorToString(Doctor.getValue()));
		db.prestatement.executeUpdate();
	}

	public void startRegistration() {
		Register.setAlignment(Pos.BASELINE_CENTER);
		Register.setOnAction(e -> {
			if(checkPassword()) {
				try {
					databaseInsert();
					Login.setIsDoctor(Doctor.getValue().equals("Doctor"));
					Login D = new Login();
					D.login();
				}
				catch (SQLException e1) {
					Status.setText("Error while adding user");
				}
			}
			else {
				checkPasswordFault();
			}
		});
		Center.getChildren().addAll(First, Last, UName, Password, PasswordCheck, Doctor, Status, Register);
	}

	public void showAccountDetails() throws SQLException {
		db.prestatement = db.Connect.prepareStatement("SELECT * FROM users WHERE userName = ?");
		db.prestatement.setString(1, Login.getUName());
		db.resultSet = db.prestatement.executeQuery();
		while (db.resultSet.next()) {
			UName.setText(db.resultSet.getString(1));
			First.setText(db.resultSet.getString(2));
			Last.setText(db.resultSet.getString(3));
			Password.setText(db.resultSet.getString(4));
			if(db.resultSet.getString(5).equals("T")) {
				Doctor.setValue("Doctor");
			}
			else {
				Doctor.setValue("Standard user");
			}

		}
	}

	public void updateAccountDetails() throws SQLException{
		db.prestatement = db.Connect.prepareStatement(""
				+ "UPDATE users SET userName=?,"
				+ "firstName=?, lastName=?,"
				+ "`password`=?, isDoctor =? WHERE userName = ?");
		db.prestatement.setString(1, UName.getText());
		db.prestatement.setString(2, First.getText());
		db.prestatement.setString(3, Last.getText());
		db.prestatement.setString(4, Password.getText());
		db.prestatement.setString(5, isDoctorToString(Doctor.getValue()));
		db.prestatement.setString(6, Login.getUName());
		db.prestatement.executeUpdate();
		Login.setUName(UName.getText());
	}

	public void deleteAccount() {
		try {
			db.prestatement = db.Connect.prepareStatement("DELETE FROM users WHERE userName = ?");
			db.prestatement.setString(1, Login.getUName());
			db.prestatement.execute();
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			Status.setText("Error while updating data in Users!");
		}
	}

	public void startEditAccount() throws SQLException {
		showAccountDetails();
		UpdateBtn.setAlignment(Pos.BASELINE_CENTER);
		UpdateBtn.setOnAction(e -> {
			if(checkPassword()) {
				Home H = new Home();
				try {
					updateAccountDetails();
					H.Homes();
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
			}
			else {
				checkPasswordFault();
			}
		});
		Center.getChildren().addAll(First, Last, UName, Password, PasswordCheck, Doctor, Status, UpdateBtn);
	}

	public Registration() throws Exception {
		setStyles();
		setVBox();

		if(!Login.getIsLogin()) {
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
