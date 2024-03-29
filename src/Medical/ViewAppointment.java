package Medical;

import LiveStock.AddProducts;
import Main.Database;
import Main.Menu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewAppointment {

    private static final Connection Connect = Database.getConnection();
    private static PreparedStatement prestatement = Database.getPrestatement();
    private static ResultSet resultSet = Database.getResultSet();

    private static BorderPane Layout;

    private final ObservableList<Appointment> DataList = FXCollections.observableArrayList();
    private final TableView<Appointment> AppointmentTable = new TableView<>(DataList);

    private final VBox TableVB = new VBox();
    private final VBox Center = AddProducts.createCenter(20, 800, 1000);
    private final HBox ButtonB = new HBox();

    private final Button New = new Button("Make New Appointment");
    private final Button update = new Button("Update Appointment");
    private final Button deleteButton = new Button("Delete Appointment");

    private void setTable() {
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

        AppointmentTable.getColumns().addAll(appointment_id, doctorName, specilization, date, time, passed);
    }

    private void setPrestatement(SimpleDateFormat formatter) throws SQLException {
        prestatement = Connect.prepareStatement("SELECT appointment.*, users.firstName, users.lastName FROM appointment LEFT JOIN users ON users.userName = appointment.doctorName WHERE patientId = ? AND DATE >= ?");
        prestatement.setString(1, Menu.getUName());
        Date now = new Date();
        prestatement.setDate(2, java.sql.Date.valueOf(formatter.format(now)));
        resultSet = prestatement.executeQuery();
    }

    private void setValues(Appointment app, SimpleDateFormat formatter) throws ParseException, SQLException {
        app.setAppointmentId(resultSet.getInt(1));
        app.setDoctorId(resultSet.getString(2));
        app.setSpecilizationName(resultSet.getString(3));
        app.setDoctorName(resultSet.getString(7) + " " + resultSet.getString(8));
        app.setDate(formatter.format(resultSet.getDate(4)));
        app.setTime(resultSet.getTime(5));
        app.setPatientId(resultSet.getString(6));
        app.setDateTime(formatter.format(resultSet.getDate(4)), resultSet.getTime(5));
        app.setPassed();
        DataList.add(app);
    }

    private void fillTable() {
        try {
            DataList.clear();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            setPrestatement(formatter);
            int count = 1;
            while (resultSet.next()) {
                Appointment app = new Appointment();
                setValues(app, formatter);
                app.setAppCount(count);
                count++;
            }
        } catch (Exception e1) {
            System.out.println("Error while fetching data from Appointment!");
        }
    }

    private void addAppointmentAction(String updateCheck, int selectedIndex) {
        new AddAppointment(updateCheck, DataList, selectedIndex, Layout);
    }

    private void updateAppointmentAction() {
        int index = AppointmentTable.getSelectionModel().selectedIndexProperty().get();
        if(index > -1) {
            try {
                addAppointmentAction("update", index);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert a = new Alert(AlertType.NONE,
                    "Please Select Row From Table",ButtonType.OK);
            a.show();
        }
    }

    private void deleteAppointmentAction() {
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

    private void deleteAppointment(int selectedAppId) {
        try {
            String query = " DELETE FROM appointment WHERE appointmentId = ? ";
            prestatement = Connect.prepareStatement(query);
            prestatement.setInt(1, selectedAppId);
            prestatement.executeUpdate();
            fillTable();
        }catch(Exception e) {
            System.out.println("Database Error in Deleting Record");
            e.printStackTrace();
        }
    }

    private void setLayout() {
        TableVB.getChildren().add(AppointmentTable);
        TableVB.setMinWidth(320);

        ButtonB.getChildren().addAll(New, update, deleteButton);
        ButtonB.setSpacing(10);

        Center.getChildren().addAll(TableVB, ButtonB);
        Layout.setCenter(Center);
    }

    public ViewAppointment(BorderPane layout) {
        Layout = layout;
        setTable();
        fillTable();
        setLayout();

        New.setOnAction(event -> addAppointmentAction(null, -1));
        update.setOnAction(event -> updateAppointmentAction());
        deleteButton.setOnAction(event -> deleteAppointmentAction());
    }

}
