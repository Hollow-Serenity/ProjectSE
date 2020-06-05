package Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
            db.prestatement = db.Connect.prepareStatement("SELECT appointment.*, users.firstName, users.lastName FROM appointment LEFT JOIN users ON users.userName = appointment.doctorName WHERE patientId = ?");
            db.prestatement.setString(1, Login.StoreUName);
            db.resultSet = db.prestatement.executeQuery();
            int count = 1;
            while (db.resultSet.next()) {
            	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
            	Appointment app = new Appointment();
            	app.setAppCount(count);
            	
            	String firstName = db.resultSet.getString(7);
            	String lastName = db.resultSet.getString(8);
            	
            	String dName = firstName + " " + lastName;
            	app.setDoctorName(dName);
            	app.setSpecilizationName(db.resultSet.getString(3));
            	
            	Date dd = db.resultSet.getDate(4);
            	String appDate = formatter.format(dd);
            	app.setDate(appDate);
            	app.setTime(db.resultSet.getTime(5));
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
            	addApp.AddAppointment();
            }
        });



        ButtonB.getChildren().addAll(New);
        ButtonB.setSpacing(10);

        VBox Center = new VBox();
        Center.getStyleClass().add("hbox");
        Center.getChildren().addAll(TableVB, ButtonB);
        Center.setMaxHeight(800);
        Center.setMaxWidth(1000);
        Center.setSpacing(20);

        Login.Layout.setCenter(Center);
    } 

}
