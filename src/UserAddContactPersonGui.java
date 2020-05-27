import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAddContactPersonGui extends Application {
    Label addedContactLabel = new Label();

    private void populate(TableView tableView) throws SQLException {
        DbUtil.dbConnect();

        PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement("SELECT idContact FROM user_contacts WHERE userName = ?");
        preparedStatement.setString(1, User.getUserName());
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            PreparedStatement preparedStatement1 = DbUtil.getConnection().prepareStatement("SELECT * FROM contacts WHERE idContact = ? AND businessCheck = 0");
            preparedStatement1.setInt(1, resultSet.getInt("idContact"));
            ResultSet resultSet1 = preparedStatement1.executeQuery();

            while(resultSet1.next()) {
                Person person1 = new Person(resultSet1.getString("contactFirstName"), resultSet1.getString("contactLastName"), resultSet1.getString("phoneNumber"));
                person1.setId(resultSet1.getInt("idContact"));
                tableView.getItems().add(person1);
            }
        }
    }
    private void people(TableView tableView, Person person) throws SQLException {
        User.addPerson(person);
        populate(tableView);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Label firstNameLabel = new Label("First name");
        TextField firstNameTextField = new TextField();
        Label lastNameLabel = new Label("Last name");
        TextField lastNameTextField = new TextField();
        Label phoneNumberLabel = new Label("Phone number");
        TextField phoneNumberTextField = new TextField();
        Button addContactButton = new Button("Add contact");

        TableView tableView = new TableView();
        TableColumn firstNameColumn = new TableColumn("First name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn lastNameColumn = new TableColumn("Last name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn phoneNumberColumn = new TableColumn("Phone number");
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        tableView.getColumns().addAll(firstNameColumn, lastNameColumn, phoneNumberColumn);

        if(tableView.getItems().isEmpty()) {
            populate(tableView);
        }

        addContactButton.setOnAction(actionEvent -> {
            try {
                Person person = new Person(firstNameTextField.getText(), lastNameTextField.getText(), phoneNumberTextField.getText());

                if(!tableView.getItems().isEmpty()) {
                    tableView.getItems().clear();
                }

                people(tableView, person);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            addedContactLabel.setText("Successfully added contact");
        });

        Button deleteContactButton = new Button("Delete");
        deleteContactButton.setOnAction(actionEvent -> {
            Object selectedItem = tableView.getSelectionModel().getSelectedItem();
            Person person = (Person)selectedItem;
            tableView.getItems().remove(selectedItem);

            try {
                ManageContact manageContact = new ManageContact();
                manageContact.deleteContact(person.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        HBox hBoxFirstName = new HBox(20);
        hBoxFirstName.getChildren().addAll(firstNameLabel, firstNameTextField);
        hBoxFirstName.setAlignment(Pos.CENTER_LEFT);
        HBox hBoxLastName = new HBox(20);
        hBoxLastName.getChildren().addAll(lastNameLabel, lastNameTextField);
        hBoxLastName.setAlignment(Pos.CENTER_LEFT);
        HBox hBoxPhoneNumber = new HBox(20);
        hBoxPhoneNumber.getChildren().addAll(phoneNumberLabel, phoneNumberTextField);
        hBoxPhoneNumber.setAlignment(Pos.CENTER_LEFT);

        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(hBoxFirstName, hBoxLastName, hBoxPhoneNumber, addContactButton, addedContactLabel, tableView, deleteContactButton);
        vBox.setPadding(new Insets(40, 40, 40, 40));
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setMinSize(800, 800);

        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.setMinWidth(800);
        stage.setMinHeight(800);
        stage.centerOnScreen();
        stage.show();
    }
}
