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

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.JOptionPane;

import static java.lang.String.valueOf;

public class ViewAppointment {

    private static Connection Connect = Database.getConnection();
    private static PreparedStatement prestatement = Database.getPrestatement();
    private static ResultSet resultSet = Database.getResultSet();

    @SuppressWarnings("unchecked")
    public void ViewAppointment() {
        ObservableList<Appointment> DataList = FXCollections.observableArrayList();
        TableView<Appointment> AppointmentTable = new TableView<>(DataList);

        TableColumn<Appointment, Integer> appointment_id = new TableColumn<>("Sr.#");
        TableColumn<Appointment, String> doctorName = new TableColumn<>("Doctor Name");
        TableColumn<Appointment, String> specilization = new TableColumn<>("Specilization");
        TableColumn<Appointment, String> date = new TableColumn<>("Date");
        TableColumn<Appointment, Time> time = new TableColumn<>("Time");
        TableColumn<Appointment, Boolean> passed = new TableColumn<>("Has Passed");

        appointment_id.setCellValueFactory(new PropertyValueFactory<>("appCount"));
        doctorName.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        specilization.setCellValueFactory(new PropertyValueFactory<>("specilizationName"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        passed.setCellValueFactory(new PropertyValueFactory<>("passed"));

        try {
            prestatement = Connect.prepareStatement("SELECT appointment.*, users.firstName, users.lastName FROM appointment LEFT JOIN users ON users.userName = appointment.doctorName WHERE patientId = ? AND DATE >= ?");
            prestatement.setString(1, Login.getUName());
            SimpleDateFormat DATE_FORMATDB = new SimpleDateFormat("yyyy-MM-dd");
            Date now = new Date();
            prestatement.setDate(2, java.sql.Date.valueOf(DATE_FORMATDB.format(now)));
            resultSet = prestatement.executeQuery();
            int count = 1;
            while (resultSet.next()) {
            	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
            	Appointment app = new Appointment();
            	app.setAppCount(count);
            	app.setAppointmentId(resultSet.getInt(1));
            	String firstName = resultSet.getString(7);
            	String lastName = resultSet.getString(8);
            	
            	String dName = firstName + " " + lastName;
            	app.setDoctorName(dName);
            	app.setDoctorId(resultSet.getString(2));
            	app.setSpecilizationName(resultSet.getString(3));
            	
            	Date dd = resultSet.getDate(4);
            	String appDate = formatter.format(dd);
            	app.setDate(appDate);
            	Time temp = resultSet.getTime(5);
            	app.setTime(temp);
            	app.setPatientId(resultSet.getString(6));
            	app.setDateTime(appDate, temp);
            	app.setPassed();
            	DataList.add(app);

            	count++;
                
            }
        } catch (Exception e1) {
            System.out.println("Error while fetching data from Appointment!");
            e1.printStackTrace();
        }

        AppointmentTable.getColumns().addAll(appointment_id, doctorName, specilization, date, time, passed);

        VBox TableVB = new VBox();
        TableVB.getChildren().add(AppointmentTable);
        TableVB.setMinWidth(320);

        HBox ButtonB = new HBox();
        AddAppointment addApp = new AddAppointment();
        Button New = new Button("Make New Appointment");
        New.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    addApp.AddAppointment(null, DataList, -1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        Button update = new Button("Update Appointment");
        update.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Object object =  AppointmentTable.getSelectionModel().selectedItemProperty().get();
            	int index = AppointmentTable.getSelectionModel().selectedIndexProperty().get();
            	if(index > -1) {
                    try {
                        addApp.AddAppointment("update", DataList, index);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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
    		String query = " DELETE FROM appointment WHERE appointmentId = ? ";
    		prestatement = Connect.prepareStatement(query);
    		prestatement.setInt(1, selectedAppId);
    		
    		
    		prestatement.executeUpdate();
    		JOptionPane.showMessageDialog(null, "Record Deleted Succesfully");
    		ViewAppointment();
    	}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Database Error in Saving Record");
			e.printStackTrace();
		}
    }

}
