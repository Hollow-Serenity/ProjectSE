package Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
    }
}
