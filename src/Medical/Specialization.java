package Medical;

import Main.Database;
import Main.Home;
import UserManagement.Login;
import Main.Menu;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Specialization {
    private static final Connection Connect = Database.getConnection();
    private static PreparedStatement prestatement = Database.getPrestatement();
    private static ResultSet resultSet = Database.getResultSet();

    private static BorderPane Layout;

    private VBox Center = new VBox(20);
    private HBox hBox = new HBox(40);

    private static final Label addSpecializationTXT = new Label("Please specify your specialization");
    private static final ChoiceBox<String> specializationBox = addSpecializationChoiceBox();
    private static final Button confirmAdd = new Button("Add");
    private static final Button confirmRemove = new Button("Remove");

    private void setLayout() {
        hBox.getChildren().addAll(confirmAdd, confirmRemove);
        Center.getChildren().addAll(addSpecializationTXT, specializationBox, hBox);

        Center.getStyleClass().add("hbox");
        hBox.getStyleClass().add("hbox");

        Center.setMaxWidth(400);
        Center.setMaxHeight(400);
        hBox.setMaxSize(400,20);

        Layout.setTop(Menu.getMenu(Layout));
        Layout.setCenter(Center);
    }

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

    public Specialization(BorderPane layout) throws Exception {
        Layout = layout;
        setLayout();

        confirmAdd.setOnAction(e -> {
            if(addSpecialization(Menu.getUName())) {
                try {
                    returnHome();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        confirmRemove.setOnAction(e -> {
            if(removeSpecialization(Menu.getUName())) {
                try {
                    returnHome();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }

    private void returnHome() throws SQLException {
        Home H = new Home();
        H.Homes(Layout);
    }

    public static Boolean addSpecialization(String UName) {
        try {
            prestatement = Connect.prepareStatement("INSERT INTO user_specialization VALUES (?,?)");
            prestatement.setString(1, specializationBox.getValue());
            prestatement.setString(2, UName);
            prestatement.executeUpdate();
            return true;
        }
        catch (SQLException e1) {
            System.out.println("Error while fetching data");
            return false;
        }
    }

    public static boolean removeSpecialization(String UName) {
        try{
            prestatement = Connect.prepareStatement("DELETE FROM user_specialization WHERE specializationID = ? AND userID = ?");
            prestatement.setString(1, specializationBox.getValue());
            prestatement.setString(2, UName);
            prestatement.execute();
            return true;
        }
        catch (SQLException e1) {
            System.out.println("Error while fetching data");
            return false;
        }
    }

    //for testing purposes
    public static void setSpecializationBox() {
        specializationBox.setValue("Cardiology");
    }

    public static String getSpecialization(String UName) {
        String ans = "";
        try {
            prestatement = Connect.prepareStatement("SELECT specializationID FROM user_specialization WHERE userID = ?");
            prestatement.setString(1, UName);
            resultSet = prestatement.executeQuery();
            while (resultSet.next()) {
                ans = resultSet.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }
}
