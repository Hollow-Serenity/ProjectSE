package Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.JOptionPane;

import static java.lang.String.valueOf;

public class ViewAppointment {

    Database db = new Database();

    @SuppressWarnings("unchecked")
    public void ViewAppointment() {
        ObservableList<Appointment> DataList = FXCollections.observableArrayList();
        TableView<Appointment> AppointmentTable = new TableView<Appointment>(DataList);

        TableColumn<Appointment, Integer> appointment_id = new TableColumn<Appointment, Integer>("Sr.#");
        TableColumn<Appointment, String> doctorName = new TableColumn<Appointment, String>("Doctor Name");
        TableColumn<Appointment, String> specilization = new TableColumn<Appointment, String>("Specilization");
        TableColumn<Appointment, String> date = new TableColumn<Appointment, String>("Date");
        TableColumn<Appointment, Time> time = new TableColumn<Appointment, Time>("Time");

        appointment_id.setCellValueFactory(new PropertyValueFactory<>("appCount"));
        doctorName.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        specilization.setCellValueFactory(new PropertyValueFactory<>("specilizationName"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));

        try {
            db.prestatement = db.Connect.prepareStatement("SELECT appointment.*, users.firstName, users.lastName FROM appointment LEFT JOIN users ON users.userName = appointment.doctorName WHERE patientId = ? AND DATE >= ?");
            db.prestatement.setString(1, Login.getUName());
            DateFormat DATE_FORMATDB = new SimpleDateFormat("yyyy-MM-dd");
            Date now = new Date();
            db.prestatement.setDate(2, java.sql.Date.valueOf(DATE_FORMATDB.format(now)));
            db.resultSet = db.prestatement.executeQuery();
            int count = 1;
            while (db.resultSet.next()) {
            	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
            	Appointment app = new Appointment();
            	app.setAppCount(count);
            	app.setAppointmentId(db.resultSet.getInt(1));
            	String firstName = db.resultSet.getString(7);
            	String lastName = db.resultSet.getString(8);
            	
            	String dName = firstName + " " + lastName;
            	app.setDoctorName(dName);
            	app.setDoctorId(db.resultSet.getString(2));
            	app.setSpecilizationName(db.resultSet.getString(3));
            	
            	Date dd = db.resultSet.getDate(4);
            	String appDate = formatter.format(dd);
            	app.setDate(appDate);
            	app.setTime(db.resultSet.getTime(5));
            	app.setPatientId(db.resultSet.getString(6));
            	DataList.add(app);
            	count++;
                
            }
        } catch (Exception e1) {
            System.out.println("Error while fetching data from Appointment!");
            e1.printStackTrace();
        }

        AppointmentTable.getColumns().addAll(appointment_id, doctorName, specilization, date, time);

        VBox TableVB = new VBox();
        TableVB.getChildren().add(AppointmentTable);
        TableVB.setMinWidth(320);

        HBox ButtonB = new HBox();
        AddAppointment addApp = new AddAppointment();
        Button New = new Button("Make New Appointment");
        New.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	addApp.AddAppointment(null, DataList, -1);
            }
        });
        
        Button update = new Button("Update Appointment");
        update.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Object object =  AppointmentTable.getSelectionModel().selectedItemProperty().get();
            	int index = AppointmentTable.getSelectionModel().selectedIndexProperty().get();
            	if(index > -1) {
            		addApp.AddAppointment("update", DataList, index);
            	}else {
            		Alert a = new Alert(AlertType.NONE,  
                            "Please Select Row From Table",ButtonType.OK);
        			a.show();
            	}
            }
        });
        
        Button deleteButton = new Button("Delete Appointment");
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Object object =  AppointmentTable.getSelectionModel().selectedItemProperty().get();
            	int index = AppointmentTable.getSelectionModel().selectedIndexProperty().get();
            	if(index > -1) {
	            	Appointment app = DataList.get(index);
	            	int selectedAppId = app.getAppointmentId();
	            	deleteAppointment(selectedAppId);
            	}else {
            		Alert a = new Alert(AlertType.NONE,  
                            "Please Select Row From Table",ButtonType.OK);
        			a.show();
            	}
            }
        });



        ButtonB.getChildren().addAll(New, update, deleteButton);
        ButtonB.setSpacing(10);

        VBox Center = new VBox();
        Center.getStyleClass().add("hbox");
        Center.getChildren().addAll(TableVB, ButtonB);
        Center.setMaxHeight(800);
        Center.setMaxWidth(1000);
        Center.setSpacing(20);

        Login.getLayout().setCenter(Center);
    }
    
    private void deleteAppointment(int selectedAppId) {
    	try {
    		Database db = new Database();
    		String query = " DELETE FROM appointment WHERE appointmentId = ? ";
    		db.prestatement = db.Connect.prepareStatement(query);
    		db.prestatement.setInt(1, selectedAppId);
    		
    		
    		db.prestatement.executeUpdate();
    		JOptionPane.showMessageDialog(null, "Record Deleted Succesfully");
    		ViewAppointment();
    	}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Database Error in Saving Record");
			e.printStackTrace();
		}
    }

}
