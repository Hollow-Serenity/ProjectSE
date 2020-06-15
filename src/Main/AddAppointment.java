package Main;

import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AddAppointment {
	Stage DialogStage = new Stage();
	ComboBox cbSelectDoctor = new ComboBox();
	ComboBox cbSpecilization = new ComboBox();
	DatePicker txtDate = new DatePicker();
	Spinner<Integer> spinnerHH = new Spinner<Integer>(0, 23, 0);
	Spinner<Integer> spinnerMM = new Spinner<Integer>(0, 59, 0);
	int selectedAppId = -1;
	String updateCheck = "";
	@SuppressWarnings("unchecked")
    public void AddAppointment(String updateCheck, ObservableList<Appointment> DataList, int selectedIndex) {
		
		this.updateCheck = updateCheck;
		
		resetValues();
		DialogStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		      public void handle(WindowEvent we) {
		          resetValues();
		      }
		  }); 



		VBox mainVB = new VBox();
		HBox hBox1 = new HBox();
		
		cbSelectDoctor.setPrefWidth(150);
		Label lblSelectDoctor = new Label("Select Doctor:");
		lblSelectDoctor.setPadding(new Insets(0, 32, 0, 0));
		
//		cbSelectDoctor.setOnAction(f->{
//			cbSpecilization.getItems().clear();
//			getSpecilization();
//		});
		hBox1.getChildren().addAll(lblSelectDoctor, cbSelectDoctor);
		
		HBox hBox2 = new HBox();
		cbSpecilization.setPrefWidth(150);
		Label lblSpecilization = new Label("Select Specilization:");
		getSpecilization();
		cbSpecilization.setOnAction(f->{
			cbSelectDoctor.getItems().clear();
			getDoctors();
		});
		hBox2.getChildren().addAll(lblSpecilization, cbSpecilization);
		
		HBox hBox3 = new HBox();
		
		txtDate.setPrefWidth(150);
		txtDate.setEditable(false);
		Label lblDate = new Label("Select Date:");
		lblDate.setPadding(new Insets(0, 44, 0, 0));
		hBox3.getChildren().addAll(lblDate, txtDate);
		
		
		HBox hBox4 = new HBox();
		
		spinnerHH.setPrefWidth(60);
		Label lblTime = new Label("Time:");
		lblTime.setPadding(new Insets(0, 80, 0, 0));
		Label lblHH = new Label(" HH ");
		lblHH.setPadding(new Insets(5, 0, 0, 0));
		
		
		spinnerMM.setPrefWidth(60);
		Label lblMM= new Label(" MM ");
		lblMM.setPadding(new Insets(5, 0, 0, 0));
		hBox4.getChildren().addAll(lblTime, spinnerHH ,lblHH, spinnerMM, lblMM);
		
//		HBox hBox6 = new HBox();
//		Spinner<Integer> spinner = new Spinner<Integer>(0, 23, 0);
//		hBox6.getChildren().addAll(spinner);
		
		VBox vbox = new VBox();
		vbox.setSpacing(20);
		vbox.setAlignment(Pos.CENTER);
		
		HBox hBoxSaveButt = new HBox();
		hBoxSaveButt.setAlignment(Pos.CENTER);
		hBoxSaveButt.setPadding(new Insets(0, 0, 0, 10));
		
		Button btnSave = new Button();
		
		if(updateCheck != null && !updateCheck.equals("")) {
			Appointment app = DataList.get(selectedIndex);
			selectedAppId = app.getAppointmentId();
			cbSpecilization.getSelectionModel().select(app.getSpecilizationName());
			cbSelectDoctor.getItems().clear();
			getDoctors();
			cbSelectDoctor.getSelectionModel().select(app.getDoctorId());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			txtDate.setValue(LocalDate.parse(app.getDate(), formatter));
			Time time = app.getTime();
			DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
			String sTime = timeFormat.format(time);
			String[] sTimeArr = sTime.split(":");
			spinnerHH.getValueFactory().setValue(Integer.parseInt(sTimeArr[0]));
			spinnerMM.getValueFactory().setValue(Integer.parseInt(sTimeArr[1]));
			
			btnSave.setText("Update");
		}else {
			btnSave.setText("Save");
		}
		
		
		btnSave.setPrefWidth(100);
		btnSave.setPrefHeight(70);
		btnSave.setPadding(new Insets(0, 10, 0, 0));
		btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                	if(updateCheck != null && !updateCheck.equals("")) {
                		updateRecord();
                	}else {
                		saveRecord();
                		
                	}
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
		hBoxSaveButt.getChildren().addAll(btnSave);
		
		HBox hBoxCheckReserveButt = new HBox();
//		hBoxCheckReserveButt.setAlignment(Pos.CENTER);
		hBoxCheckReserveButt.setPadding(new Insets(0, 0, 0, 110));
		
		Button btnCheckReserveSlots = new Button("View Reserved Slots");
		btnCheckReserveSlots.setPrefWidth(150);
		btnCheckReserveSlots.setPrefHeight(30);
		btnCheckReserveSlots.setPadding(new Insets(0, 0, 0, 0));
		btnCheckReserveSlots.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                	if(validateFieldsForCheckReservred()) {
                		showReservedSlots();
                	}
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
		
		hBoxCheckReserveButt.getChildren().addAll(btnCheckReserveSlots);
		
		vbox.getChildren().addAll(hBoxCheckReserveButt, hBoxSaveButt);
		
		mainVB.getChildren().addAll(hBox2, hBox1, hBox3, hBox4, vbox);
		mainVB.getStyleClass().add("hbox");
		
//		mainVB.setMaxHeight(800);
//		mainVB.setMaxWidth(1000);
//		mainVB.setSpacing(20);
//		mainVB.setMaxWidth(400);
//		mainVB.setMaxHeight(250);
		mainVB.setSpacing(10);
		
		Scene DialogScn = new Scene(mainVB, 450, 300);
		DialogScn.getStylesheets().add(getClass().getResource("../css/application.css").toExternalForm());
        DialogStage.setScene(DialogScn);
        DialogStage.setResizable(false);
        DialogStage.setTitle("Add New Appointment");
        DialogStage.show();
//        Login.Layout.setCenter(mainVB);
	}
	
	private void getDoctors() {
		try {
			Database db = new Database();
//			db.prestatement = db.Connect.prepareStatement("SELECT userName FROM users WHERE isDoctor = ?");
			
			db.prestatement = db.Connect.prepareStatement("SELECT userID FROM user_specialization WHERE specializationID = ?");
			String specilization = (String)cbSpecilization.getSelectionModel().getSelectedItem();
			db.prestatement.setString(1, specilization);
			db.resultSet = db.prestatement.executeQuery();
			
			while (db.resultSet.next()) {
				cbSelectDoctor.getItems().add(db.resultSet.getString(1));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getSpecilization() {
		try {
			Database db = new Database();
//			db.prestatement = db.Connect.prepareStatement("SELECT specializationID FROM user_specialization WHERE userID = ?");
			db.prestatement = db.Connect.prepareStatement("SELECT name FROM specialization");
//			String doctor = (String)cbSelectDoctor.getSelectionModel().getSelectedItem();
//			db.prestatement.setString(1, doctor);
			db.resultSet = db.prestatement.executeQuery();
			
			while (db.resultSet.next()) {
				cbSpecilization.getItems().add(db.resultSet.getString(1));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void saveRecord() {
		try {
			if(validateFields()) {
				System.out.println("Save Record::::::::");
				
				String hh = spinnerHH.getValue().toString();
				String mm = spinnerMM.getValue().toString();
				String time = hh+":"+mm+":"+"00";
				Database db = new Database();
				db.prestatement = db.Connect.prepareStatement("INSERT INTO appointment(doctorName, specializationId, date, time, patientId) VALUES(?,?,?,?,?)");
				db.prestatement.setString(1, cbSelectDoctor.getSelectionModel().getSelectedItem().toString());
				db.prestatement.setString(2, cbSpecilization.getSelectionModel().getSelectedItem().toString());
				db.prestatement.setDate(3, java.sql.Date.valueOf(txtDate.getValue()));
				db.prestatement.setTime(4, java.sql.Time.valueOf(time));

				
				db.prestatement.setString(5, Login.getUName());
				db.prestatement.executeUpdate();
				
				JOptionPane.showMessageDialog(null, "Record Saved Succesfully");
				resetValues();
				DialogStage.hide();
				ViewAppointment vApp = new ViewAppointment();
				vApp.ViewAppointment();
				
				
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Database Error in Saving Record");
			e.printStackTrace();
		}
	}
	
	private void updateRecord() {
		try {
			if(validateFields()) {
				System.out.println("Update Record::::::::");
				
				String hh = spinnerHH.getValue().toString();
				String mm = spinnerMM.getValue().toString();
				String time = hh+":"+mm+":"+"00";
				Database db = new Database();
				String query = " UPDATE appointment SET doctorName = ?, specializationId = ?, DATE = ?, TIME = ?, patientId = ? WHERE appointmentId = ? ";
				db.prestatement = db.Connect.prepareStatement(query);
				db.prestatement.setString(1, cbSelectDoctor.getSelectionModel().getSelectedItem().toString());
				db.prestatement.setString(2, cbSpecilization.getSelectionModel().getSelectedItem().toString());
				db.prestatement.setDate(3, java.sql.Date.valueOf(txtDate.getValue()));
				db.prestatement.setTime(4, java.sql.Time.valueOf(time));				
				db.prestatement.setString(5, Login.getUName());
				
				db.prestatement.setInt(6, selectedAppId);
				
				db.prestatement.executeUpdate();
				
				JOptionPane.showMessageDialog(null, "Record Updated Succesfully");
				resetValues();
				DialogStage.hide();
				ViewAppointment vApp = new ViewAppointment();
				vApp.ViewAppointment();
				
				
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Database Error in Updating Record");
			e.printStackTrace();
		}
	}
	
	private boolean validateFields() {
		if(cbSelectDoctor.getSelectionModel().getSelectedItem() == null) {
			
			Alert a = new Alert(AlertType.NONE,  
                    "Please Select Doctor",ButtonType.OK);
			a.show();
			cbSelectDoctor.requestFocus();
			 
			
			return false;
		}
		
		if(cbSpecilization.getSelectionModel().getSelectedItem() == null) {
			Alert a = new Alert(AlertType.NONE,  
                    "Please Select Specilization",ButtonType.OK);
			a.show();
			cbSpecilization.requestFocus();
			return false;
		}
		
		if(txtDate.getValue() == null) {
			Alert a = new Alert(AlertType.NONE,  
                    "Please Select Date",ButtonType.OK);
			a.show();
			txtDate.requestFocus();
			return false;
		}
		if(updateCheck != null && !updateCheck.equals("")) {
			if(!getDoctorAvailabilityForUpdate()) {
				Alert a = new Alert(AlertType.INFORMATION,  
	                    "Message",ButtonType.OK);
				a.setContentText("Doctor is not available at selected Date/Time slot\nPlease choose different Date/Time slot");
				a.show();
				return false;
			}
		}else {
			if(!getDoctorAvailability()) {
				Alert a = new Alert(AlertType.INFORMATION,  
	                    "Message",ButtonType.OK);
				a.setContentText("Doctor is not available at selected Date/Time slot\nPlease choose different Date/Time slot");
				a.show();
				return false;
			}
		}
		
		return true;
	}
	
	private boolean getDoctorAvailability() {
		try {
			Database db = new Database();
			String hh = spinnerHH.getValue().toString();
			String mm = spinnerMM.getValue().toString();
			String time = hh+":"+mm+":"+"00";
//			String query = "SELECT * FROM appointment WHERE doctorName = ? AND DATE = ? AND (appointment.TIME < ? OR appointment.TIME + INTERVAL 45 MINUTE > ?)";
			String query1  = "SELECT * FROM appointment WHERE doctorName = ? AND DATE = ? AND CASE " + 
					"    WHEN appointment.TIME <= ? THEN  (appointment.TIME <= ? AND appointment.TIME + INTERVAL 45 MINUTE >= ?) " + 
					"    WHEN appointment.TIME >= ? THEN  (appointment.TIME >= ? AND appointment.TIME - INTERVAL 45 MINUTE <= ?) " + 
					"    END";
			db.prestatement = db.Connect.prepareStatement(query1);
			db.prestatement.setString(1, cbSelectDoctor.getSelectionModel().getSelectedItem().toString());
			db.prestatement.setDate(2, java.sql.Date.valueOf(txtDate.getValue()));
			db.prestatement.setTime(3, java.sql.Time.valueOf(time));
			db.prestatement.setTime(4, java.sql.Time.valueOf(time));
			db.prestatement.setTime(5, java.sql.Time.valueOf(time));
			db.prestatement.setTime(6, java.sql.Time.valueOf(time));
			db.prestatement.setTime(7, java.sql.Time.valueOf(time));
			db.prestatement.setTime(8, java.sql.Time.valueOf(time));
			
			db.resultSet = db.prestatement.executeQuery();
			
			while (db.resultSet.next()) {
				return false;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	private boolean getDoctorAvailabilityForUpdate() {
		try {
			Database db = new Database();
			String hh = spinnerHH.getValue().toString();
			String mm = spinnerMM.getValue().toString();
			String time = hh+":"+mm+":"+"00";
//			String query = "SELECT * FROM appointment WHERE doctorName = ? AND DATE = ? AND (appointment.TIME < ? OR appointment.TIME + INTERVAL 45 MINUTE > ?)";
			String query1 = "SELECT * FROM appointment WHERE doctorName = ? AND DATE = ? AND appointmentId != ? AND CASE " + 
						"    WHEN appointment.TIME <= ? THEN  (appointment.TIME <= ? AND appointment.TIME + INTERVAL 45 MINUTE >= ?) " + 
						"    WHEN appointment.TIME >= ? THEN  (appointment.TIME >= ? AND appointment.TIME - INTERVAL 45 MINUTE <= ?) " + 
						"    END";
			db.prestatement = db.Connect.prepareStatement(query1);
			db.prestatement.setString(1, cbSelectDoctor.getSelectionModel().getSelectedItem().toString());
			db.prestatement.setDate(2, java.sql.Date.valueOf(txtDate.getValue()));
			db.prestatement.setInt(3, selectedAppId);
			db.prestatement.setTime(4, java.sql.Time.valueOf(time));
			db.prestatement.setTime(5, java.sql.Time.valueOf(time));
			db.prestatement.setTime(6, java.sql.Time.valueOf(time));
			db.prestatement.setTime(7, java.sql.Time.valueOf(time));
			db.prestatement.setTime(8, java.sql.Time.valueOf(time));
			db.prestatement.setTime(9, java.sql.Time.valueOf(time));
			
			db.resultSet = db.prestatement.executeQuery();
			
			while (db.resultSet.next()) {
				return false;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	private void resetValues() {
		cbSelectDoctor.getItems().clear();
		cbSpecilization.getItems().clear();
		txtDate.setValue(null);
		spinnerHH.getValueFactory().setValue(0);
		spinnerMM.getValueFactory().setValue(1);
	}
	
	
	
	public void showReservedSlots() {
		Stage stage = new Stage();
        ObservableList<Appointment> DataList = FXCollections.observableArrayList();
        TableView<Appointment> AppointmentTable = new TableView<Appointment>(DataList);

        TableColumn<Appointment, Integer> srNo = new TableColumn<Appointment, Integer>("Sr.#");
        TableColumn<Appointment, String> date = new TableColumn<Appointment, String>("Date");
        TableColumn<Appointment, Time> sTime = new TableColumn<Appointment, Time>("Start Time");
        TableColumn<Appointment, Time> eTime = new TableColumn<Appointment, Time>("End Time");
        
        srNo.setCellValueFactory(new PropertyValueFactory<>("appCount"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        sTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        eTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        
        
        
        try {
        	Database db = new Database();
            db.prestatement = db.Connect.prepareStatement("SELECT appointment.date, appointment.TIME, appointment.TIME + INTERVAL 45 MINUTE AS endTime FROM appointment WHERE doctorName = ? AND DATE = ?");
            db.prestatement.setString(1, cbSelectDoctor.getSelectionModel().getSelectedItem().toString());
            db.prestatement.setDate(2, java.sql.Date.valueOf(txtDate.getValue()));
            db.resultSet = db.prestatement.executeQuery();
            int count = 1;
            while (db.resultSet.next()) {
            	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
            	Appointment app = new Appointment();
            	app.setAppCount(count);
            	
            	Date dd = db.resultSet.getDate(1);
            	String appDate = formatter.format(dd);
            	app.setDate(appDate);
            	app.setTime(db.resultSet.getTime(2));            	
            	app.setEndTime(db.resultSet.getTime(3));
            	
            	DataList.add(app);
            	count++;
            }
        } catch (Exception e1) {
            System.out.println("Error while fetching data from Appointment!");
            e1.printStackTrace();
        }
        
        AppointmentTable.getColumns().addAll(srNo, date, sTime, eTime);
        
        VBox TableVB = new VBox();
        TableVB.getChildren().add(AppointmentTable);
        TableVB.setMinWidth(250);

        HBox ButtonBox = new HBox();


        VBox Center = new VBox();
        Center.getStyleClass().add("hbox");
        Center.getChildren().addAll(TableVB);
        Center.setMaxHeight(500);
        Center.setMaxWidth(500);
        Center.setSpacing(20);

        stage.setResizable(false);
        Scene DialogScn = new Scene(Center, 500, 500);
        DialogScn.getStylesheets().add(getClass().getResource("../css/application.css").toExternalForm());
        stage.setScene(DialogScn);

        stage.setTitle("View Reserved Slots");
        stage.show();
    }
	
	private boolean validateFieldsForCheckReservred() {
		if(cbSelectDoctor.getSelectionModel().getSelectedItem() == null) {
			
			Alert a = new Alert(AlertType.NONE,  
                    "Please Select Doctor",ButtonType.OK);
			a.show();
			cbSelectDoctor.requestFocus();
			 
			
			return false;
		}
		
		if(txtDate.getValue() == null) {
			Alert a = new Alert(AlertType.NONE,  
                    "Please Select Date",ButtonType.OK);
			a.show();
			txtDate.requestFocus();
			return false;
		}
		
		return true;
	}
	
	
}
