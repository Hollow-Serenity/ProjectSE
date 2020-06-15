package Main;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

public class Condition {
    private static final Database db = new Database();

    private static final Label conditionTXT = new Label("Please specify the condition");
    private static final Label patientIDTXT = new Label("Please enter your patient's username");

    private static final TextField conditionTF = new TextField();
    private static final TextField patientIDTF = new TextField();

    private static final Button confirmAdd = new Button("Add");
    private static final Button confirmRemove = new Button("Remove");

    public Condition() {
        VBox Center = new VBox(20);
        HBox hBox = new HBox(40);

        conditionTF.setPromptText("your patient's condition");
        patientIDTF.setPromptText("your patient's username");

        hBox.getChildren().addAll(confirmAdd, confirmRemove);
        Center.getChildren().addAll(conditionTXT, conditionTF, patientIDTXT, patientIDTF, hBox);

        Center.getStyleClass().add("hbox");
        hBox.getStyleClass().add("hbox");

        Center.setMaxWidth(400);
        Center.setMaxHeight(400);
        hBox.setMaxSize(400,20);

        Menu m = new Menu();
        BorderPane layout = Login.getLayout();
        layout.setTop(m.Menu());
        layout.setCenter(Center);

        confirmAdd.setOnAction(e -> addCondition());
        confirmRemove.setOnAction(e -> removeCondition());
    }

    public void addCondition() {
        try {
            db.prestatement = db.Connect.prepareStatement("INSERT INTO `condition` VALUES (?,?)");
            db.prestatement.setString(1, conditionTF.getText());
            db.prestatement.setString(2, patientIDTF.getText());
            db.prestatement.executeUpdate();
            try {
                new Medical_Platform();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        catch (SQLException e1) {
            System.out.println("Error while fetching data");
        }
    }

    public void removeCondition() {
        try{
            db.prestatement = db.Connect.prepareStatement("DELETE FROM `condition` WHERE conditionId = ? AND userId = ?");
            db.prestatement.setString(1, conditionTF.getText());
            db.prestatement.setString(2, patientIDTF.getText());
            db.prestatement.execute();
            try {
                new Medical_Platform();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        catch (SQLException e1) {
            System.out.println("Error while fetching data");
        }
    }
}
