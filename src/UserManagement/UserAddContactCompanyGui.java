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

public class UserAddContactCompanyGui {
    private final HBox hBoxFirstName = new HBox(20);
    private final HBox hBoxPhoneNumber = new HBox(20);
    private final HBox Buttons = new HBox(20);
    private final VBox vBox = new VBox(20);

    private final Label addedContactLabel = new Label();
    private final Label companyNameLabel = new Label("Company name");
    private final Label phoneNumberLabel = new Label("Phone number");

    private final TextField companyNameTextField = new TextField();
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

    private void setColumns() {
        TableColumn firstNameColumn = new TableColumn("Company name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        TableColumn phoneNumberColumn = new TableColumn("Phone number");
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        tableView.getColumns().addAll(firstNameColumn, phoneNumberColumn);
    }

    private void addContactButtonAction() {
        try {
            Company company = new Company(companyNameTextField.getText(), phoneNumberTextField.getText());
            companyNameTextField.clear();
            phoneNumberTextField.clear();
            if(!tableView.getItems().isEmpty()) {
                tableView.getItems().clear();
            }
            companies(tableView, company);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        addedContactLabel.setText("Successfully added contact");
    }

    private void deleteContactButtonAction() {
        Object selectedItem = tableView.getSelectionModel().getSelectedItem();
        Company company = (Company)selectedItem;
        tableView.getItems().remove(selectedItem);
        try {
            ManageContact manageContact = new ManageContact();
            manageContact.deleteContact(company.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setLayout() {
        setColumns();
        Buttons.getChildren().addAll(addContactButton, deleteContactButton);
        Buttons.setAlignment(Pos. CENTER_LEFT);

        hBoxPhoneNumber.getChildren().addAll(phoneNumberLabel, phoneNumberTextField);
        hBoxPhoneNumber.setAlignment(Pos.CENTER_LEFT);

        hBoxFirstName.getChildren().addAll(companyNameLabel, companyNameTextField);
        hBoxFirstName.setAlignment(Pos.CENTER_LEFT);

        vBox.getChildren().addAll(hBoxFirstName, hBoxPhoneNumber, Buttons, addedContactLabel, tableView);
        vBox.setPadding(new Insets(40, 40, 40, 40));
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setMinSize(800, 800);
    }

    public UserAddContactCompanyGui() throws Exception {
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
