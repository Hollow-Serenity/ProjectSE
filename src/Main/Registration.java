package Main;

import java.awt.*;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Registration {

	public static Stage Window;

	public Boolean checkUName(Database db, String UName) {
		try {
			String res = null;
			db.prestatement = db.Connect.prepareStatement("SELECT userName FROM users WHERE userName = ?");
			db.prestatement.setString(1, UName);
			db.resultSet = db.prestatement.executeQuery();
			while(db.resultSet.next()) {
				res = db.resultSet.getString(1);
			}
			return res == null;
		}
		catch (SQLException e) {
			return true;
		}
	}

	public Registration() throws Exception {

		VBox Center = new VBox();
		Text Status = new Text();
		Status.setText("");
		Status.setFill(Color.RED);
		TextField First = new TextField();
		TextField Last = new TextField();
		TextField UName = new TextField();
		TextField Password = new TextField();
		TextField PasswordCheck = new TextField();
		Database db = new Database();

        if(!Login.isLogin) {
			Button Register = new Button("Register");
			Register.setAlignment(Pos.BASELINE_CENTER);
			Register.setOnAction(e -> {
				if(Password.getText().equals(PasswordCheck.getText())
						&& Password.getText().length() > 6
						&& Password.getText().length() < 21
						&& !UName.getText().equals("")
						&& !First.getText().equals("")
						&& !Last.getText().equals("")
						&& checkUName(db, UName.getText())) {
					try {
						db.prestatement = db.Connect.prepareStatement("INSERT INTO users VALUES(?,?,?,?)");
						db.prestatement.setString(1, UName.getText());
						db.prestatement.setString(2, First.getText());
						db.prestatement.setString(3, Last.getText());
						db.prestatement.setString(4, Password.getText());
						db.prestatement.executeUpdate();
						Login D = new Login();
						D.login();
					}
					catch (SQLException e1) {
						Status.setText("Error while adding user");
					}
				}
				else {
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
			});
			Center.getChildren().addAll(First, Last, UName, Password, PasswordCheck, Status, Register);
        }
        else {
			db.prestatement = db.Connect.prepareStatement("SELECT * FROM users WHERE userName = ?");
			db.prestatement.setString(1, Login.StoreUName);
			db.resultSet = db.prestatement.executeQuery();
			while (db.resultSet.next()) {
				UName.setText(db.resultSet.getString(1));
	        	First.setText(db.resultSet.getString(2));
	        	Last.setText(db.resultSet.getString(3));
	        	Password.setText(db.resultSet.getString(4));
			}
			Button UpdateBtn = new Button("Update account");
			UpdateBtn.setOnAction(e -> {
				if(Password.getText().equals(PasswordCheck.getText())
						&& Password.getText().length() > 6
						&& Password.getText().length() < 21
						&& !UName.getText().equals("")
						&& !First.getText().equals("")
						&& !Last.getText().equals("")
						&& checkUName(db, UName.getText())) {
					try {
						db.prestatement = db.Connect.prepareStatement(""
								+ "UPDATE users SET userName=?,"
								+ "firstName=?, lastName=?,"
								+ "password=? WHERE userName = ?");
						db.prestatement.setString(1, UName.getText());
						db.prestatement.setString(2, First.getText());
						db.prestatement.setString(3, Last.getText());
						db.prestatement.setString(4, Password.getText());
						db.prestatement.setString(5, Login.StoreUName);
						db.prestatement.executeUpdate();
						Login.StoreUName = UName.getText();
						Home H = new Home();
						H.Homes();
					} catch (SQLException e1) {
						System.out.println(e1.getMessage());
						Status.setText("Error while updating data in Users!");
					}
				}
				else {
					if(!Password.getText().equals(PasswordCheck.getText())) {
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
			});
			Center.getChildren().addAll(First, Last, UName, Password, PasswordCheck, Status, UpdateBtn);
        }
        
		First.setPromptText("First Name");
		Last.setPromptText("Last Name");
		UName.setPromptText("Username");
		Password.setPromptText("Password");
		PasswordCheck.setPromptText("Repeat Password");
		
		Center.getStyleClass().add("hbox");
		Center.setMaxWidth(400);
		Center.setMaxHeight(400);
		Center.setSpacing(10);
		Menu m = new Menu();
		Login.Layout.setTop(m.Menu());
		Login.Layout.setCenter(Center);
	}
}
