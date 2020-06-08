package Main;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

public class Specialization {
    private static final Database db = new Database();
    private static final Label addSpecializationTXT = new Label("Please specify you specialization");
    private static final Label removeSpecializationTXT = new Label("Please specify the specialization you'd like to remove");
    private static final ChoiceBox<String> addSpecializationBox = addSpecializationChoiceBox();
    private static final ChoiceBox<String> removeSpecializationBox = addSpecializationChoiceBox();
    private static final Button confirmAdd = new Button("Add specialization");
    private static final Button confirmRemove = new Button("Remove specialization");

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
        VBox Center = new VBox();
        Center.getChildren().addAll(addSpecializationTXT, addSpecializationBox, confirmAdd, removeSpecializationTXT, removeSpecializationBox, confirmRemove);
        Center.getStyleClass().add("hbox");
        Center.setMaxWidth(400);
        Center.setMaxHeight(400);
        Center.setSpacing(10);
        Menu m = new Menu();
        Login.getLayout().setTop(m.Menu());
        Login.getLayout().setCenter(Center);

        confirmAdd.setOnAction(e -> addSpecialization());
        confirmRemove.setOnAction(e -> removeSpecialization());
    }

    public void addSpecialization() {
        try {
            db.prestatement = db.Connect.prepareStatement("INSERT INTO user_specialization VALUES (?,?)");
            db.prestatement.setString(1, addSpecializationBox.getValue());
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
            db.prestatement.setString(1, removeSpecializationBox.getValue());
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
