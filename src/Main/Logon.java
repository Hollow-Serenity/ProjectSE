package Main;

import java.sql.SQLException;

import com.mysql.cj.protocol.Resultset;

import javafx.event.ActionEvent;
import javafx.event.EventHandler; 
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Logon {

	public static Stage Window;

	public Logon() throws Exception {

		VBox Center = new VBox();
		TextField First = new TextField();
		TextField Last = new TextField();
		TextField UName = new TextField();
		TextField Password = new TextField();
		TextField PasswordCheck = new TextField();
		Database db = new Database();
		
		Alert a = new Alert(AlertType.INFORMATION); 	
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
                a.show(); 
            } 
        }; 

        if(Driver.StoreUName == null) {
			Button Register = new Button("Add User");
			Register.setOnAction(e -> {
				try {
					db.prestatement = db.Connect.prepareStatement("INSERT INTO users VALUES(?,?,?,?)");
					db.prestatement.setString(1, UName.getText());
					db.prestatement.setString(2, First.getText());
					db.prestatement.setString(3, Last.getText());
					db.prestatement.setString(4, Password.getText());
					db.prestatement.executeUpdate();
			        event.handle(new ActionEvent());
					Driver D = new Driver();
					D.login();
	            	a.setContentText("User has been added");
				} catch (SQLException e1) {
	            	a.setContentText("Error while adding user!");
				}
				event.handle(new ActionEvent());
			});
			Center.getChildren().addAll(First, Last, UName, Password, PasswordCheck, Register);
        }
        else {
			db.prestatement = db.Connect.prepareStatement("SELECT * FROM users WHERE userName = ?");
			db.prestatement.setString(1, Driver.StoreUName);
			db.resultSet =  db.prestatement.executeQuery();
			while (db.resultSet.next()) {
				UName.setDisable(true);
				UName.setText(db.resultSet.getString(1));
	        	First.setText(db.resultSet.getString(2));
	        	Last.setText(db.resultSet.getString(3));
	        	Password.setText(db.resultSet.getString(4));
			}
			Button UpdateBtn = new Button("Edit User");
			UpdateBtn.setOnAction(e -> {
				try {
					db.prestatement = db.Connect.prepareStatement(""
							+ "UPDATE users SET userName=?,"
							+ "firstName=?, lastName=?,"
							+ "password=? WHERE userName = ?");
					db.prestatement.setString(1, UName.getText());
					db.prestatement.setString(2, First.getText());
					db.prestatement.setString(3, Last.getText());
					db.prestatement.setString(4, Password.getText());
					db.prestatement.setString(5, Driver.StoreUName);
					db.prestatement.executeUpdate();
					Driver.StoreUName = UName.getText();
	            	a.setContentText("User has been updated");
	    			Home H = new Home();
	    			H.Homes();
				} catch (SQLException e1) {
					System.out.println(e1.getMessage());
	            	a.setContentText("Error while updating data in Users!");
				}
				event.handle(new ActionEvent());
			});
			Center.getChildren().addAll(First, Last, UName, Password, PasswordCheck, UpdateBtn);
        }
        
		First.setPromptText("First Name");
		Last.setPromptText("Last Name");
		UName.setPromptText("Username");
		Password.setPromptText("Password");
		PasswordCheck.setPromptText("Repeat password");
		
		Center.getStyleClass().add("hbox");
		Center.setMaxWidth(400);
		Center.setMaxHeight(400);
		Center.setSpacing(10);
		Menu m = new Menu();
		Driver.Layout.setTop(m.Menu());
		Driver.Layout.setCenter(Center);
	}
}
