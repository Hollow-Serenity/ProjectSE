package Main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAddContactCompanyGui {
    private Label addedContactLabel = new Label();

    private void populate(TableView tableView) throws SQLException {
        DbUtil.dbConnect();

        PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement("SELECT idContact FROM user_contacts WHERE userName = ?");
        preparedStatement.setString(1, User.getUserName());
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            PreparedStatement preparedStatement1 = DbUtil.getConnection().prepareStatement("SELECT * FROM contacts WHERE idContact = ? AND businessCheck = 1");
            preparedStatement1.setInt(1, resultSet.getInt("idContact"));
            ResultSet resultSet1 = preparedStatement1.executeQuery();

            while(resultSet1.next()) {
                Company company1 = new Company(resultSet1.getString("contactFirstName"), resultSet1.getString("phoneNumber"));
                company1.setId(resultSet1.getInt("idContact"));
                tableView.getItems().add(company1);
            }
        }
    }
    private void companies(TableView tableView, Company company) throws SQLException {
        User.addCompany(company);
        populate(tableView);
    }

    public UserAddContactCompanyGui() throws Exception {
        Label companyNameLabel = new Label("Company name");
        TextField companyNameTextField = new TextField();
        Label phoneNumberLabel = new Label("Phone number");
        TextField phoneNumberTextField = new TextField();
        Button addContactButton = new Button("Add contact");

        TableView tableView = new TableView();
        TableColumn firstNameColumn = new TableColumn("Company name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        TableColumn phoneNumberColumn = new TableColumn("Phone number");
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        tableView.getColumns().addAll(firstNameColumn, phoneNumberColumn);

        if(tableView.getItems().isEmpty()) {
            populate(tableView);
        }

        addContactButton.setOnAction(actionEvent -> {
            try {
                Company company = new Company(companyNameTextField.getText(), phoneNumberTextField.getText());

                if(!tableView.getItems().isEmpty()) {
                    tableView.getItems().clear();
                }

                companies(tableView, company);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            addedContactLabel.setText("Successfully added contact");
        });

        Button deleteContactButton = new Button("Delete");
        deleteContactButton.setOnAction(actionEvent -> {
            Object selectedItem = tableView.getSelectionModel().getSelectedItem();
            Company company = (Company)selectedItem;
            tableView.getItems().remove(selectedItem);

            try {
                ManageContact manageContact = new ManageContact();
                manageContact.deleteContact(company.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        HBox hBoxFirstName = new HBox(20);
        hBoxFirstName.getChildren().addAll(companyNameLabel, companyNameTextField);
        hBoxFirstName.setAlignment(Pos.CENTER_LEFT);
        HBox hBoxPhoneNumber = new HBox(20);
        hBoxPhoneNumber.getChildren().addAll(phoneNumberLabel, phoneNumberTextField);
        hBoxPhoneNumber.setAlignment(Pos.CENTER_LEFT);

        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(hBoxFirstName, hBoxPhoneNumber, addContactButton, addedContactLabel, tableView, deleteContactButton);
        vBox.setPadding(new Insets(40, 40, 40, 40));
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setMinSize(800, 800);

        Menu m = new Menu();
        Login.Layout.setTop(m.Menu());
        Login.Layout.setCenter(vBox);
    }
}
