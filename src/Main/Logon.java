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
		TextField Email = new TextField();
		TextField Phone = new TextField();
		TextField Password = new TextField();
		Database db = new Database();
		
		Alert a = new Alert(AlertType.INFORMATION); 	
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
                a.show(); 
            } 
        }; 
        if(Driver.EmailStore == null) {
			Button Logon = new Button("Add User");
			Logon.setOnAction(e -> {
				try {
					db.prestatement = db.Connect.prepareStatement("INSERT INTO Users VALUES(?,?,?,?,?)");
					db.prestatement.setString(1, Email.getText());
					db.prestatement.setString(2, First.getText());
					db.prestatement.setString(3, Last.getText());
					db.prestatement.setString(4, Phone.getText());
					db.prestatement.setString(5, Password.getText());
					db.prestatement.executeUpdate();
			        event.handle(new ActionEvent());
					Driver D = new Driver();
					D.Login();
	            	a.setContentText("User has been added");
				} catch (SQLException e1) {
	            	a.setContentText("Error while inserting data in Users!");
				}
				event.handle(new ActionEvent());
			});
			Center.getChildren().addAll(First, Last, Email, Phone, Password, Logon);
        }
        else {
			db.prestatement = db.Connect.prepareStatement("SELECT * FROM Users WHERE Email = ?");
			db.prestatement.setString(1, Driver.EmailStore);
			db.resultSet =  db.prestatement.executeQuery();
			while (db.resultSet.next()) {
				Email.setDisable(true);
				Email.setText(db.resultSet.getString(1));
	        	First.setText(db.resultSet.getString(2));
	        	Last.setText(db.resultSet.getString(3));
	        	Phone.setText(db.resultSet.getString(4));
	        	Password.setText(db.resultSet.getString(5));
			}
			Button UpdateBtn = new Button("Edit User");
			UpdateBtn.setOnAction(e -> {
				try {
					db.prestatement = db.Connect.prepareStatement(""
							+ "UPDATE Users SET FirstName=?,"
							+ "LastName=?, Phone=?,"
							+ "Password=? WHERE Email = ?");
					db.prestatement.setString(1, First.getText());
					db.prestatement.setString(2, Last.getText());
					db.prestatement.setString(3, Phone.getText());
					db.prestatement.setString(4, Password.getText());
					db.prestatement.setString(5, Email.getText());
					db.prestatement.executeUpdate();
	            	a.setContentText("User has been updated");
	    			Home H = new Home();
	    			H.Homes();
				} catch (SQLException e1) {
					System.out.println(e1.getMessage());
	            	a.setContentText("Error while updating data in Users!");
				}
				event.handle(new ActionEvent());
			});
			Center.getChildren().addAll(First, Last, Email, Phone, Password, UpdateBtn);
        }
        
		First.setPromptText("First Name");
		Last.setPromptText("Last Name");
		Email.setPromptText("Email Address");
		Phone.setPromptText("Phone Number");
		Password.setPromptText("Password");
		
		Center.getStyleClass().add("hbox");
		Center.setMaxWidth(400);
		Center.setMaxHeight(400);
		Center.setSpacing(10);
		Menu m = new Menu();
		Driver.Layout.setTop(m.Menu());
		Driver.Layout.setCenter(Center);
	}
}
