package Medical;

import Main.Database;
import Main.Menu;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Condition {
    private static final Connection Connect = Database.getConnection();
    private static PreparedStatement prestatement = Database.getPrestatement();
    private static ResultSet resultSet = Database.getResultSet();

    private static BorderPane Layout;

    private final VBox Center = new VBox(20);
    private final HBox hBox = new HBox(40);

    private static final Label conditionTXT = new Label("Please specify the condition");
    private static final Label patientIDTXT = new Label("Please enter your patient's username");

    private static final TextField conditionTF = new TextField();
    private static final TextField patientIDTF = new TextField();

    private static final Button confirmAdd = new Button("Add");
    private static final Button confirmRemove = new Button("Remove");

    private void setLayout() {
        conditionTF.setPromptText("your patient's condition");
        patientIDTF.setPromptText("your patient's username");

        hBox.getChildren().addAll(confirmAdd, confirmRemove);
        hBox.getStyleClass().add("hbox");
        hBox.setMaxSize(400,20);

        Center.getChildren().addAll(conditionTXT, conditionTF, patientIDTXT, patientIDTF, hBox);
        Center.getStyleClass().add("hbox");
        Center.setMaxWidth(400);
        Center.setMaxHeight(400);

        Layout.setTop(Menu.getMenu(Layout));
        Layout.setCenter(Center);
    }

    private void returnToMedPlat() {
        try {
            new Medical_Platform(Layout);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Condition(BorderPane layout) {
        Layout = layout;
        setLayout();

        confirmAdd.setOnAction(e -> {
            if(addCondition()) {
                returnToMedPlat();
            }
        });

        confirmRemove.setOnAction(e -> {
            if(removeCondition()) {
                returnToMedPlat();
            }
        });
    }

    public static boolean addCondition() {
        try {
            prestatement = Connect.prepareStatement("INSERT INTO `condition` VALUES (?,?)");
            prestatement.setString(1, conditionTF.getText());
            prestatement.setString(2, patientIDTF.getText());
            prestatement.executeUpdate();
            return true;
        }
        catch (SQLException e1) {
            System.out.println("Error while fetching data");
            return false;
        }
    }

    public static boolean removeCondition() {
        try{
            prestatement = Connect.prepareStatement("DELETE FROM `condition` WHERE conditionId = ? AND userId = ?");
            prestatement.setString(1, conditionTF.getText());
            prestatement.setString(2, patientIDTF.getText());
            prestatement.execute();
            return true;
        }
        catch (SQLException e1) {
            System.out.println("Error while fetching data");
            return false;
        }
    }

    public static void getConditions(String UName, ListView<String> conditionsList) {
        conditionsList.getItems().clear();
        try {
            prestatement = Connect.prepareStatement("SELECT * FROM `condition` WHERE userId = ?");
            prestatement.setString(1, UName);
            resultSet = prestatement.executeQuery();
            while (resultSet.next()) {
                String condition = resultSet.getString("conditionId");
                conditionsList.getItems().addAll(condition);
            }
        } catch (Exception e) {
            System.out.println("Error while fetching data");
            e.printStackTrace();
        }
    }

    //for testing purposes
    public static void setTF() {
        conditionTF.setText("testcondition");
        patientIDTF.setText("UNameUpdate");
    }
}
