package Medical;

import LiveStock.AddProducts;
import Main.Database;
import Main.Menu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddAppointment {
    private static final Connection Connect = Database.getConnection();
    private static PreparedStatement prestatement = Database.getPrestatement();
    private static ResultSet resultSet = Database.getResultSet();

    private static BorderPane Layout;

    private final ObservableList<Appointment> DataList = FXCollections.observableArrayList();
    private final TableView<Appointment> AppointmentTable = new TableView<>(DataList);

    private final VBox mainVB = new VBox(10);
    private final VBox Center = new VBox(20);
    private final VBox CenterRes = AddProducts.createCenter(20, 500, 500);
    private final VBox TableVB = new VBox();
    private final HBox hBox1 = new HBox();
    private final HBox hBox2 = new HBox();
    private final HBox hBox3 = new HBox();
    private final HBox hBox4 = new HBox();
    private final HBox hBoxSaveButt = new HBox();
    private final HBox hBoxCheckReserveButt = new HBox();

    private final Label lblSelectDoctor = new Label("Select Doctor:");
    private final Label lblSpecilization = new Label("Select Specialization:");
    private final Label lblDate = new Label("Select Date:");
    private final Label lblTime = new Label("Time:");
    private final Label lblHH = new Label(" HH ");
    private final Label lblMM = new Label(" MM ");
    private static javafx.scene.text.Text status = new Text();

    private final ComboBox cbSelectDoctor = new ComboBox();
    private final ChoiceBox<String> cbSpecialization = Specialization.addSpecializationChoiceBox();
    private final DatePicker txtDate = new DatePicker();
    private final Button btnSave = new Button();
    private final Button btnCheckReserveSlots = new Button("View Reserved Slots");
    private final Spinner<Integer> spinnerHH = new Spinner<>(0, 23, 0);
    private final Spinner<Integer> spinnerMM = new Spinner<>(0, 59, 0);

    private int selectedAppId = -1;
    private String updateCheck = "";

    private void setBoxes() {
        hBox1.getChildren().addAll(lblSelectDoctor, cbSelectDoctor);
        hBox2.getChildren().addAll(lblSpecilization, cbSpecialization);
        hBox3.getChildren().addAll(lblDate, txtDate);
        hBox4.getChildren().addAll(lblTime, spinnerHH, lblHH, spinnerMM, lblMM);
        hBoxSaveButt.getChildren().addAll(btnSave);
        hBoxSaveButt.setAlignment(Pos.CENTER);
        hBoxCheckReserveButt.setPadding(new Insets(0, 0, 0, 250));
        hBoxCheckReserveButt.getChildren().addAll(btnCheckReserveSlots);

        Center.setAlignment(Pos.CENTER);
        Center.getChildren().addAll(status, hBoxCheckReserveButt, hBoxSaveButt);
        mainVB.getChildren().addAll(hBox2, hBox1, hBox3, hBox4, Center);
        mainVB.getStyleClass().add("hbox");
    }

    private void setAlignment() {
        lblSpecilization.setPadding(new Insets(0, 20, 0, 190));
        lblSelectDoctor.setPadding(new Insets(0, 69, 0, 190));
        lblDate.setPadding(new Insets(0, 83, 0, 190));
        lblTime.setPadding(new Insets(0, 129, 0, 190));
        lblHH.setPadding(new Insets(5, 0, 0, 0));
        lblMM.setPadding(new Insets(5, 0, 0, 0));
    }

    private void setSizes() {
        cbSelectDoctor.setPrefWidth(150);
        cbSpecialization.setPrefWidth(150);
        txtDate.setPrefWidth(150);
        spinnerHH.setPrefWidth(60);
        spinnerMM.setPrefWidth(60);
        btnSave.setPrefWidth(100);
        btnSave.setPrefHeight(70);
        btnCheckReserveSlots.setPrefWidth(200);
        btnCheckReserveSlots.setPrefHeight(30);
    }

    private void setOther() {
        cbSpecialization.setOnAction(f -> {
            cbSelectDoctor.getItems().clear();
            getDoctors();
        });
        txtDate.setEditable(false);
        status.setFill(Color.RED);
        cbSelectDoctor.getItems().clear();
        getDoctors();

        Layout.setTop(Menu.getMenu(Layout));
        Layout.setCenter(mainVB);
    }

    private void setFullLayout() {
        setBoxes();
        setAlignment();
        setSizes();
        setOther();
    }

    private void setUpdateInfo(Appointment app) {
        selectedAppId = app.getAppointmentId();
        cbSpecialization.getSelectionModel().select(app.getSpecilizationName());
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
    }

    private void saveAction() {
        try {
            if (updateCheck != null && !updateCheck.equals("")) {
                updateRecord();
            } else {
                saveRecord();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkReserveSlot() {
        try {
            if (validateFieldsForCheckReserved()) {
                showReservedSlots();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AddAppointment(String updateCheck, ObservableList<Appointment> DataList, int selectedIndex, BorderPane layout) {
        this.Layout = layout;
        this.updateCheck = updateCheck;
        setFullLayout();

        if (updateCheck != null && !updateCheck.equals("")) {
            Appointment app = DataList.get(selectedIndex);
            setUpdateInfo(app);
        } else {
            btnSave.setText("Save");
        }

        btnSave.setOnAction(event -> saveAction());
        btnCheckReserveSlots.setOnAction(event -> checkReserveSlot());
    }

    private void getDoctors() {
        try {
            prestatement = Connect.prepareStatement("SELECT userID FROM user_specialization WHERE specializationID = ?");
            String specialization = cbSpecialization.getValue();
            prestatement.setString(1, specialization);
            resultSet = prestatement.executeQuery();

            while (resultSet.next()) {
                cbSelectDoctor.getItems().add(resultSet.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertRecord(String time) throws SQLException {
        prestatement.setString(1, cbSelectDoctor.getSelectionModel().getSelectedItem().toString());
        prestatement.setString(2, cbSpecialization.getSelectionModel().getSelectedItem());
        prestatement.setDate(3, java.sql.Date.valueOf(txtDate.getValue()));
        prestatement.setTime(4, java.sql.Time.valueOf(time));
        prestatement.setString(5, Menu.getUName());
        prestatement.executeUpdate();
    }

    private void openViewApp() {
        ViewAppointment vApp = new ViewAppointment();
        vApp.ViewAppointment(Layout);
    }

    private String getTime() {
        String hh = spinnerHH.getValue().toString();
        String mm = spinnerMM.getValue().toString();
        return hh + ":" + mm + ":" + "00";
    }

    private void saveRecord() {
        try {
            if (validateFields()) {
                String time = getTime();
                prestatement = Connect.prepareStatement("INSERT INTO appointment(doctorName, specializationId, date, time, patientId) VALUES(?,?,?,?,?)");
                insertRecord(time);
                resetValues();
                openViewApp();
            }
        } catch (Exception e) {
            status.setText("Database Error in Saving Record");
            e.printStackTrace();
        }
    }

    private void updateRecord() {
        try {
            if (validateFields()) {
                String time = getTime();
                prestatement = Connect.prepareStatement(" UPDATE appointment SET doctorName = ?, specializationId = ?, DATE = ?, TIME = ?, patientId = ? WHERE appointmentId = ? ");
                prestatement.setInt(6, selectedAppId);
                insertRecord(time);
                resetValues();
                openViewApp();
            }
        } catch (Exception e) {
            status.setText("Database Error in Updating Record");
            e.printStackTrace();
        }
    }

    private boolean validateFields() {
        if (cbSelectDoctor.getSelectionModel().getSelectedItem() == null) {
            status.setText("Please Select A Doctor");
            return false;
        }
        if (cbSpecialization.getSelectionModel().getSelectedItem() == null) {
            status.setText("Please Select A Specilization");
            return false;
        }
        if (txtDate.getValue() == null) {
            status.setText("Please Select A Date");
            return false;
        }
        if (updateCheck != null && !updateCheck.equals("")) {
            if (!getDoctorAvailabilityForUpdate()) {
                status.setText("Doctor is not available at selected Date/Time slot");
                return false;
            }
        } else {
            if (!getDoctorAvailability()) {
                status.setText("Doctor is not available at selected Date/Time slot");
                return false;
            }
        }
        return true;
    }

    private void insertPreStatementData(int appID) throws SQLException {
        String time = getTime();
        prestatement.setString(1, cbSelectDoctor.getSelectionModel().getSelectedItem().toString());
        prestatement.setDate(2, java.sql.Date.valueOf(txtDate.getValue()));
        prestatement.setInt(3, appID);
        prestatement.setTime(4, java.sql.Time.valueOf(time));
        prestatement.setTime(5, java.sql.Time.valueOf(time));
        prestatement.setTime(6, java.sql.Time.valueOf(time));
        prestatement.setTime(7, java.sql.Time.valueOf(time));
        prestatement.setTime(8, java.sql.Time.valueOf(time));
        prestatement.setTime(9, java.sql.Time.valueOf(time));
        resultSet = prestatement.executeQuery();
    }

    private boolean getDoctorAvailability() {
        try {
            String query = "SELECT * FROM appointment WHERE doctorName = ? AND DATE = ? AND appointmentId != ? AND CASE " +
                    "    WHEN appointment.TIME <= ? THEN  (appointment.TIME <= ? AND appointment.TIME + INTERVAL 45 MINUTE >= ?) " +
                    "    WHEN appointment.TIME >= ? THEN  (appointment.TIME >= ? AND appointment.TIME - INTERVAL 45 MINUTE <= ?) " +
                    "    END";
            prestatement = Connect.prepareStatement(query);
            insertPreStatementData(-1);
            while (resultSet.next()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean getDoctorAvailabilityForUpdate() {
        try {
            String query = "SELECT * FROM appointment WHERE doctorName = ? AND DATE = ? AND appointmentId != ? AND CASE " +
                    "    WHEN appointment.TIME <= ? THEN  (appointment.TIME <= ? AND appointment.TIME + INTERVAL 45 MINUTE >= ?) " +
                    "    WHEN appointment.TIME >= ? THEN  (appointment.TIME >= ? AND appointment.TIME - INTERVAL 45 MINUTE <= ?) " +
                    "    END";
            prestatement = Connect.prepareStatement(query);
            insertPreStatementData(selectedAppId);
            while (resultSet.next()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private void resetValues() {
        cbSelectDoctor.getItems().clear();
        cbSpecialization.getSelectionModel().selectFirst();
        txtDate.setValue(null);
        spinnerHH.getValueFactory().setValue(0);
        spinnerMM.getValueFactory().setValue(1);
        status.setText("");
    }

    private void setReservedTable() {
        TableColumn<Appointment, Integer> srNo = new TableColumn<>("Sr.#");
        TableColumn<Appointment, String> date = new TableColumn<>("Date");
        TableColumn<Appointment, Time> sTime = new TableColumn<>("Start Time");
        TableColumn<Appointment, Time> eTime = new TableColumn<>("End Time");

        srNo.setCellValueFactory(new PropertyValueFactory<>("appCount"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        sTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        eTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        AppointmentTable.getColumns().addAll(srNo, date, sTime, eTime);
    }

    private void setPrestatement() throws SQLException {
        prestatement = Connect.prepareStatement("SELECT appointment.date, appointment.TIME, appointment.TIME + INTERVAL 45 MINUTE AS endTime FROM appointment WHERE doctorName = ? AND DATE = ?");
        prestatement.setString(1, cbSelectDoctor.getSelectionModel().getSelectedItem().toString());
        prestatement.setDate(2, java.sql.Date.valueOf(txtDate.getValue()));
        resultSet = prestatement.executeQuery();
    }

    private void setDetails(int count) throws SQLException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Appointment app = new Appointment();
        app.setAppCount(count);
        app.setDate(formatter.format(resultSet.getDate(1)));
        app.setTime(resultSet.getTime(2));
        app.setEndTime(resultSet.getTime(3));
        DataList.add(app);
    }

    private void fillTable() {
        try {
            setPrestatement();
            int count = 1;
            while (resultSet.next()) {
                setDetails(count);
                count++;
            }
        } catch (Exception e1) {
            System.out.println("Error while fetching data from Appointment!");
            e1.printStackTrace();
        }
    }

    public void showReservedSlots() {
        setReservedTable();
        fillTable();

        TableVB.getChildren().add(AppointmentTable);
        TableVB.setMinWidth(250);
        CenterRes.getChildren().addAll(TableVB);

        Layout.setTop(Menu.getMenu(Layout));
        Layout.setCenter(CenterRes);
    }

    private boolean validateFieldsForCheckReserved() {
        if (cbSelectDoctor.getSelectionModel().getSelectedItem() == null) {
            status.setText("Please Select A Doctor");
            cbSelectDoctor.requestFocus();
            return false;
        }
        if (txtDate.getValue() == null) {
            status.setText("Please Select A Date");
            txtDate.requestFocus();
            return false;
        }
        return true;
    }


}
