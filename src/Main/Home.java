package Main;

import Education.EduPlatform;
import LiveStock.AddProducts;
import LiveStock.Market;
import Medical.AddAppointment;
import Medical.Appointment;
import Medical.Medical_Platform;
import UserManagement.ContactTypeGui;
import UserManagement.Login;
import UserManagement.Registration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Home {
    private static final Connection Connect = Database.getConnection();
    private static ResultSet resultSet = Database.getResultSet();

    private final ObservableList<Appointment> DataList = FXCollections.observableArrayList();
    private final TableView<Appointment> AppointmentTable = new TableView<>(DataList);

    private final Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    private static BorderPane Layout;

    private final VBox Center = new VBox(20);
    private final HBox tableVBox = new HBox();
    private final HBox RowOne = new HBox(20);
    private final HBox RowTwo = new HBox(20);
    private final HBox RowThree = new HBox(20);
    private final HBox RowFour = new HBox(20);

    private final Button InventoryBtn = new Button("Add Inventory");
    private final Button EditUser = new Button("Edit Profile");
    private final Button LogoutBtn = new Button("Logout");
    private final Button MarketBtn = new Button("Market");
    private final Button UserAddBtn = new Button("Add User");
    private final Button MedicalPlatformbtn = new Button("Medical Platform");
    private final Button EduPlatformbtn = new Button("Eduplatform");

    private void setButtonLooks(Button button, String image, String tooltip) {
        button.getStyleClass().addAll("HomeBtn", "LightGreen");
        button.setTooltip(new Tooltip(tooltip));
        if(!image.equals("")) {
            Image APImg = new Image(getClass().getResourceAsStream(image));
            ImageView APIV = new ImageView(APImg);
            APIV.setFitWidth(50);
            APIV.setFitHeight(50);
            button.setGraphic(APIV);
        }
    }

    private void setInventoryBtn() {
        setButtonLooks(InventoryBtn, "../Images/AddProduct.png", "Add Products");
        InventoryBtn.setOnAction(event -> {
            AddProducts ap = new AddProducts();
            ap.AddProduct(Layout);
        });
    }

    private void setEditUser() {
        setButtonLooks(EditUser, "../Images/AddUser.png", "Edit User");
        EditUser.setOnAction(event -> {
            try {
                new Registration(Layout);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void setLogoutBtn() {
        setButtonLooks(LogoutBtn, "../Images/Logout.png", "Logout");
        LogoutBtn.setOnAction(e -> {
            Menu.setUName(null);
            Menu.setIsLogin(false);
            Menu.setIsDoctor(false);
            Login.login();
        });
    }

    private void setMarketBtn() {
        setButtonLooks(MarketBtn, "../Images/AddProduct.png", "Market");
        Market mr = new Market();
        MarketBtn.setOnAction(event -> mr.Market(Layout));
    }

    private void setMedicalPlatformbtn() {
        setButtonLooks(MedicalPlatformbtn, "", "Medical Platform");
        MedicalPlatformbtn.setOnAction(e -> {
            try {
                new Medical_Platform(Layout);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    private void setEduPlatformbtn() {
        setButtonLooks(EduPlatformbtn, "", "Education Platform");
        EduPlatformbtn.setOnAction(e -> {
            try {
                new EduPlatform(Layout);
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        });
    }

    private void setUserAddBtn() {
        UserAddBtn.getStyleClass().addAll("HomeBtn", "LightGreen");
        UserAddBtn.setOnAction(event -> {
            try {
                new ContactTypeGui(Layout);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void setButtons() {
        setInventoryBtn();
        setEditUser();
        setLogoutBtn();
        setMarketBtn();
        setMedicalPlatformbtn();
        setEduPlatformbtn();
        setUserAddBtn();
    }

    private void setLayout() {
        RowOne.getChildren().addAll(InventoryBtn, MarketBtn);
        RowTwo.getChildren().addAll(EditUser, UserAddBtn);
        RowThree.getChildren().addAll(MedicalPlatformbtn, EduPlatformbtn);
        RowFour.getChildren().addAll(LogoutBtn);

        RowOne.setAlignment(Pos.CENTER);
        RowTwo.setAlignment(Pos.CENTER);
        RowThree.setAlignment(Pos.CENTER);
        RowFour.setAlignment(Pos.CENTER);
        tableVBox.setAlignment(Pos.CENTER);
        tableVBox.getChildren().add(AppointmentTable);
        Center.getChildren().addAll(RowOne, RowTwo, RowThree, RowFour, tableVBox);

        Center.getStyleClass().addAll("HomeVBox");
        Center.setMaxWidth(screenBounds.getWidth() * 0.95);
        Center.setMaxHeight(screenBounds.getHeight() * 0.75);
    }

    public void Homes(BorderPane layout) {
        Layout = layout;
        setButtons();
        setLayout();
        showReservedSlots();

        if(!Menu.getIsDoctor()) {
            tableVBox.setVisible(false);
        }

        Layout.setTop(Menu.getMenu(Layout));
        Layout.setCenter(Center);
    }

    private void setPrestatement() throws SQLException {
        String query = "SELECT appointment.date, appointment.TIME, appointment.TIME + INTERVAL 45 MINUTE AS endTime, users.firstName, users.lastName FROM appointment LEFT JOIN users ON users.userName = appointment.patientId WHERE doctorName = ? ORDER BY DATE ASC";
        PreparedStatement prestatement = Connect.prepareStatement(query);
        prestatement.setString(1, Menu.getUName());
        resultSet = prestatement.executeQuery();
    }

    private int setDetails(Appointment app, int count) throws ParseException, SQLException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        app.setDate(formatter.format(resultSet.getDate(1)));
        app.setTime(resultSet.getTime(2));
        app.setDateTime(formatter.format(resultSet.getDate(1)), resultSet.getTime(2));
        app.setEndTime(resultSet.getTime(3));
        String patientName = resultSet.getString(4) + " " + resultSet.getString(5);
        app.setPatientName(patientName);
        app.setPassed();
        if(!app.getPassed()) {
            DataList.add(app);
            count++;
        }
        return count;
    }

    public void showReservedSlots() {
        AddAppointment.setReservedTable(AppointmentTable);
        try {
            setPrestatement();
            int count = 1;
            while (resultSet.next() && count < 4) {
                Appointment app = new Appointment();
                app.setAppCount(count);
                count = setDetails(app, count);
            }
        } catch (Exception e1) {
            System.out.println("Error while fetching data from Appointment!");
            e1.printStackTrace();
        }
    }
}