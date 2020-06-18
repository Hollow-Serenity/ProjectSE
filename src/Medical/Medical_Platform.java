package Medical;

import Main.Database;
import Main.Menu;
import Main.Login;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Medical_Platform {
    private Pane layout = new Pane();

    private static Connection Connect = Database.getConnection();
    private static PreparedStatement prestatement = Database.getPrestatement();
    private static ResultSet resultSet = Database.getResultSet();

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

        if (!Login.getIsDoctor()) {
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
            prestatement = Connect.prepareStatement("SELECT * FROM `condition` WHERE userId = ?");
            prestatement.setString(1, UName);
            resultSet = prestatement.executeQuery();
            while (resultSet.next()) {
                String condition = resultSet.getString("conditionId");
                afflictionList.getItems().addAll(condition);
            }
        } catch (Exception e) {
            System.out.println("Error while fetching data");
            e.printStackTrace();
        }
    }

    public Medical_Platform() throws Exception {
        setStyle();
        setLayout();
        setAfflictions(Login.getUName());

        Login.getLayout().setTop(Menu.getMenu());
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

    }
}
