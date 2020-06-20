package Medical;

import Main.Database;
import Main.Menu;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Medical_Platform {
    private Pane pane = new Pane();
    private static BorderPane Layout;

    private static final Connection Connect = Database.getConnection();
    private static final PreparedStatement prestatement = Database.getPrestatement();
    private static final ResultSet resultSet = Database.getResultSet();

    private ListView<String> afflictionList = new ListView();

    private final Label afflictions = new Label("Your afflictions:");
    private final Label patientUNameTXT = new Label("Check Patient's Afflictions");

    private final TextField patientUName = new TextField();

    private final Button appointmentdBtn = new Button("Appointments");
    private final Button patientAfflictionsBtn = new Button("Get Afflictions");
    private final Button conditionsBtn = new Button("Diagnose");

    private void setLayout() {
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
    }

    private void setFinalLayout() {
        patientUNameTXT.setLayoutX(350);
        patientUNameTXT.setLayoutY(20);
        patientUName.setLayoutX(350);
        patientUName.setLayoutY(50);

        pane.setMaxWidth(600);
        pane.setMaxHeight(400);
        pane.getChildren().addAll(afflictionList, appointmentdBtn, conditionsBtn, afflictions, patientUNameTXT, patientUName, patientAfflictionsBtn);

        Layout.setTop(Menu.getMenu(Layout));
        Layout.setCenter(pane);
    }

    private void setDoctorVisibility() {
        if (!Menu.getIsDoctor()) {
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

        pane.getStyleClass().add("hbox");
    }

    public Medical_Platform(BorderPane layout) {
        Layout = layout;

        Condition.getConditions(Menu.getUName(), afflictionList);
        setDoctorVisibility();
        setStyle();
        setLayout();
        setFinalLayout();

        appointmentdBtn.setOnAction(event -> new ViewAppointment(Layout));
        conditionsBtn.setOnAction(event -> new Condition(Layout));
        patientAfflictionsBtn.setOnAction(event -> {
            afflictions.setText("Your patient's afflictions:");
            Condition.getConditions(patientUName.getText(), afflictionList);
        });
    }
}
