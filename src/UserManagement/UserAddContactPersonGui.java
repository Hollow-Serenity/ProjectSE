package UserManagement;

import Main.DbUtil;
import Main.Login;
import Main.Menu;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAddContactPersonGui {
    private final HBox hBoxFirstName = new HBox(20);
    private final HBox hBoxLastName = new HBox(20);
    private final HBox hBoxPhoneNumber = new HBox(20);
    private final HBox Buttons = new HBox(20);
    private final VBox vBox = new VBox(20);

    private final Label addedContactLabel = new Label();
    private final Label firstNameLabel = new Label("First name");
    private final Label phoneNumberLabel = new Label("Phone number");
    private final Label lastNameLabel = new Label("Last name");

    private final TextField firstNameTextField = new TextField();
    private final TextField lastNameTextField = new TextField();
    private final TextField phoneNumberTextField = new TextField();

    private final Button addContactButton = new Button("Add contact");
    private final Button deleteContactButton = new Button("Delete");

    private final TableView tableView = new TableView();

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

    private void setColumns() {
        TableColumn firstNameColumn = new TableColumn("First name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn lastNameColumn = new TableColumn("Last name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn phoneNumberColumn = new TableColumn("Phone number");
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        tableView.getColumns().addAll(firstNameColumn, lastNameColumn, phoneNumberColumn);
    }

    private void addContactButtonAction() {
        try {
            Person person = new Person(firstNameTextField.getText(), lastNameTextField.getText(), phoneNumberTextField.getText());
            firstNameTextField.clear();
            lastNameTextField.clear();
            phoneNumberTextField.clear();
            if(!tableView.getItems().isEmpty()) {
                tableView.getItems().clear();
            }
            people(tableView, person);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        addedContactLabel.setText("Successfully added contact");
    }

    private void deleteContactButtonAction() {
        Object selectedItem = tableView.getSelectionModel().getSelectedItem();
        Person person = (Person)selectedItem;
        tableView.getItems().remove(selectedItem);
        try {
            ManageContact manageContact = new ManageContact();
            manageContact.deleteContact(person.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setLayout() {
        setColumns();
        hBoxFirstName.getChildren().addAll(firstNameLabel, firstNameTextField);
        hBoxFirstName.setAlignment(Pos.CENTER_LEFT);
        hBoxLastName.getChildren().addAll(lastNameLabel, lastNameTextField);
        hBoxLastName.setAlignment(Pos.CENTER_LEFT);
        hBoxPhoneNumber.getChildren().addAll(phoneNumberLabel, phoneNumberTextField);
        hBoxPhoneNumber.setAlignment(Pos.CENTER_LEFT);

        Buttons.getChildren().addAll(addContactButton, deleteContactButton);
        Buttons.setAlignment(Pos.CENTER_LEFT);

        vBox.getChildren().addAll(hBoxFirstName, hBoxLastName, hBoxPhoneNumber, Buttons, addedContactLabel, tableView);
        vBox.setPadding(new Insets(40, 40, 40, 40));
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setMinSize(800, 800);
    }

    public UserAddContactPersonGui() throws Exception {
        setLayout();
        if(tableView.getItems().isEmpty()) {
            populate(tableView);
        }

        addContactButton.setOnAction(actionEvent -> addContactButtonAction());
        deleteContactButton.setOnAction(actionEvent -> deleteContactButtonAction());

        Login.getLayout().setTop(Menu.getMenu());
        Login.getLayout().setCenter(vBox);
    }
}

