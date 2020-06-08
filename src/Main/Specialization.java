package Main;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

public class Specialization {
    private static final Database db = new Database();
    private static final Label addSpecializationTXT = new Label("Please specify your specialization");
    private static final ChoiceBox<String> specializationBox = addSpecializationChoiceBox();
    private static final Button confirmAdd = new Button("Add");
    private static final Button confirmRemove = new Button("Remove");

    public static ChoiceBox<String> addSpecializationChoiceBox() {
        ChoiceBox<String> specializations = new ChoiceBox<>();
        try {
            db.prestatement = db.Connect.prepareStatement("select * from specialization");
            db.resultSet = db.prestatement.executeQuery();
            while (db.resultSet.next()) {
                specializations.getItems().add(db.resultSet.getString("name"));
            }
            specializations.getSelectionModel().selectFirst();
        }
        catch (SQLException e1) {
            System.out.println("Error while fetching data");
        }
        return specializations;
    }

    public Specialization() throws Exception {
        VBox Center = new VBox(20);
        HBox hBox = new HBox(40);

        hBox.getChildren().addAll(confirmAdd, confirmRemove);
        Center.getChildren().addAll(addSpecializationTXT, specializationBox, hBox);

        Center.getStyleClass().add("hbox");
        hBox.getStyleClass().add("hbox");

        Center.setMaxWidth(400);
        Center.setMaxHeight(400);
        hBox.setMaxSize(400,20);

        Menu m = new Menu();
        Login.getLayout().setTop(m.Menu());
        Login.getLayout().setCenter(Center);

        confirmAdd.setOnAction(e -> addSpecialization());
        confirmRemove.setOnAction(e -> removeSpecialization());
    }

    public void addSpecialization() {
        try {
            db.prestatement = db.Connect.prepareStatement("INSERT INTO user_specialization VALUES (?,?)");
            db.prestatement.setString(1, specializationBox.getValue());
            db.prestatement.setString(2, Login.getUName());
            db.prestatement.executeUpdate();
            Home H = new Home();
            H.Homes();
        }
        catch (SQLException e1) {
            System.out.println("Error while fetching data");
        }
    }

    public void removeSpecialization() {
        try{
            db.prestatement = db.Connect.prepareStatement("DELETE FROM user_specialization WHERE specializationID = ? AND userID = ?");
            db.prestatement.setString(1, specializationBox.getValue());
            db.prestatement.setString(2, Login.getUName());
            db.prestatement.execute();
            Home H = new Home();
            H.Homes();
        }
        catch (SQLException e1) {
            System.out.println("Error while fetching data");
        }
    }
}
