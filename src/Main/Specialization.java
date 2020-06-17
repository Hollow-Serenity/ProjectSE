package Main;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Specialization {
    private static Connection Connect = Database.getConnection();
    private static PreparedStatement prestatement = Database.getPrestatement();
    private static ResultSet resultSet = Database.getResultSet();

    private static final Label addSpecializationTXT = new Label("Please specify your specialization");
    private static final ChoiceBox<String> specializationBox = addSpecializationChoiceBox();
    private static final Button confirmAdd = new Button("Add");
    private static final Button confirmRemove = new Button("Remove");

    public static ChoiceBox<String> addSpecializationChoiceBox() {
        ChoiceBox<String> specializations = new ChoiceBox<>();
        try {
            prestatement = Connect.prepareStatement("select * from specialization");
            resultSet = prestatement.executeQuery();
            while (resultSet.next()) {
                specializations.getItems().add(resultSet.getString("name"));
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
            prestatement = Connect.prepareStatement("INSERT INTO user_specialization VALUES (?,?)");
            prestatement.setString(1, specializationBox.getValue());
            prestatement.setString(2, Login.getUName());
            prestatement.executeUpdate();
            Home H = new Home();
            H.Homes();
        }
        catch (SQLException e1) {
            System.out.println("Error while fetching data");
        }
    }

    public void removeSpecialization() {
        try{
            prestatement = Connect.prepareStatement("DELETE FROM user_specialization WHERE specializationID = ? AND userID = ?");
            prestatement.setString(1, specializationBox.getValue());
            prestatement.setString(2, Login.getUName());
            prestatement.execute();
            Home H = new Home();
            H.Homes();
        }
        catch (SQLException e1) {
            System.out.println("Error while fetching data");
        }
    }
}
