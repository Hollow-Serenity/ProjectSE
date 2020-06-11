package Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
<<<<<<< HEAD
import javafx.scene.control.*;
=======
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
>>>>>>> development
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

<<<<<<< HEAD
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Medical_Platform {
    private Pane layout = new Pane();

    private Database db = new Database();

    private ListView<String> afflictionList = new ListView();

    private Label afflictions = new Label("Your afflictions:");
    private Label patientUNameTXT = new Label("Check Patient's Afflictions");

    private TextField patientUName = new TextField();

    private Button appointmentdBtn = new Button("Appointments");
    private Button patientAfflictionsBtn = new Button("Get Afflictions");
    private Button conditionsBtn = new Button("Diagnose");

    private void setLayout() {
        patientUNameTXT.setLayoutX(350);
        patientUNameTXT.setLayoutY(20);

        patientUName.setLayoutX(350);
        patientUName.setLayoutY(50);

        patientAfflictionsBtn.setLayoutX(350);
        patientAfflictionsBtn.setLayoutY(90);

        appointmentdBtn.setLayoutX(100);
        appointmentdBtn.setLayoutY(225);

        conditionsBtn.setLayoutX(100);
        conditionsBtn.setLayoutY(25);

        afflictionList.setLayoutX(350);
        afflictionList.setLayoutY(175);

        afflictionList.setMaxHeight(200);
        afflictionList.setMaxWidth(200);

        afflictions.setLayoutX(350);
        afflictions.setLayoutY(150);

        layout.getChildren().addAll(afflictionList, appointmentdBtn, conditionsBtn, afflictions, patientUNameTXT, patientUName, patientAfflictionsBtn);
        layout.setMaxWidth(600);
        layout.setMaxHeight(400);

        if(!Login.getIsDoctor()) {
            patientUName.setVisible(false);
            patientUNameTXT.setVisible(false);
            conditionsBtn.setVisible(false);
            patientAfflictionsBtn.setVisible(false);
        }
    }

    private void setStyle() {
        appointmentdBtn.getStyleClass().addAll("MedicalBtn", "LightGreen");
        appointmentdBtn.setTooltip(new Tooltip("Make Appointment"));

        conditionsBtn.getStyleClass().addAll("MedicalBtn", "LightGreen");
        conditionsBtn.setTooltip(new Tooltip("Assign a condition to a patient"));

        patientUName.setPromptText("Patient's username");

        layout.getStyleClass().add("hbox");
    }

    private void setAfflictions(String UName) {
        afflictionList.getItems().clear();
        try {
            db.prestatement = db.Connect.prepareStatement("SELECT * FROM `condition` WHERE userId = ?");
            db.prestatement.setString(1, UName);
            db.resultSet = db.prestatement.executeQuery();
            while (db.resultSet.next()) {
                String condition = db.resultSet.getString("conditionId");
                afflictionList.getItems().addAll(condition);
            }
        } catch (Exception e) {
            System.out.println("Error while fetching data");
            e.printStackTrace();
        }
    }

    public Medical_Platform() throws Exception{
        setStyle();
        setLayout();
        setAfflictions(Login.getUName());

        Menu m = new Menu();
        Login.getLayout().setTop(m.Menu());
        Login.getLayout().setCenter(layout);

        ViewAppointment viewApp = new ViewAppointment();
        appointmentdBtn.setOnAction(event -> viewApp.ViewAppointment());

        conditionsBtn.setOnAction(event -> {
            try {
                new Condition();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        patientAfflictionsBtn.setOnAction(event -> {
            afflictions.setText("Your patient's afflictions:");
            setAfflictions(patientUName.getText());
        });
=======
public class Medical_Platform {
    public Stage stage;
    public Medical_Platform() throws Exception{
        Pane layout = new Pane();
        Label makeAppointment_lbl = new Label("Press 'select' to make an appointment with a doctor.");
        TableView appointmentList = new TableView();
        Label yourAppointments = new Label("Your appointments:");
        Button appointmentdBtn = new Button("Appointment");

        appointmentdBtn.getStyleClass().addAll("HomeBtn", "LightGreen");
        Image appImg = new Image(getClass().getResourceAsStream("../Images/AddUser.png"));
        ImageView appIV = new ImageView(appImg);
        appIV.setFitWidth(50);
        appIV.setFitHeight(50);
        appointmentdBtn.setGraphic(appIV);
        appointmentdBtn.setTooltip(new Tooltip("Make Appointment"));
//        AddAppointment addApp = new AddAppointment();
        ViewAppointment viewApp = new ViewAppointment();
        appointmentdBtn.setOnAction(event -> viewApp.ViewAppointment());

        makeAppointment_lbl.setLayoutX(14);
        makeAppointment_lbl.setLayoutY(183);
        appointmentdBtn.setLayoutX(45);
        appointmentdBtn.setLayoutY(214);
        appointmentList.setLayoutX(311);
        appointmentList.setLayoutY(130);
        appointmentList.setMaxHeight(200);
        appointmentList.setMaxWidth(200);
        yourAppointments.setLayoutX(311);
        yourAppointments.setLayoutY(100);
        layout.getChildren().addAll(makeAppointment_lbl, appointmentList, appointmentdBtn, yourAppointments);
        layout.setMaxWidth(600);
        layout.setMaxHeight(400);
        layout.getStyleClass().add("hbox");
        Menu m = new Menu();
        Login.getLayout().setTop(m.Menu());
        Login.getLayout().setCenter(layout);
>>>>>>> development
    }
}
